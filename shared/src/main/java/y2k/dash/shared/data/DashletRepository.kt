package y2k.dash.shared.data

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import y2k.dash.shared.utils.RequestQueueSingleton
import java.net.URI

class DashletRepository(private val dao: DashletDao, private val requestQueue: RequestQueueSingleton, private val viewModelScope: CoroutineScope) {
    fun getAll() = dao.getAll()

    private suspend fun update(vararg dashlets: Dashlet) = dao.update(*dashlets)

    fun moveDashlet(from: Int, to: Int) = viewModelScope.launch { dao.moveDashlet(from, to) }
    fun dropDashlets() = viewModelScope.launch { dao.dropDashlets() }

    fun insert(dashlet: Dashlet) {
        viewModelScope.launch {
            dashlet.position = dao.getNextPosition()
            dao.insert(dashlet)
        }
        refreshDashlet(dashlet)
    }

    fun refreshDashlet(dashlet: Dashlet) {
        if (URI(dashlet.url).scheme == "setting") return

        val updateRequest = JsonObjectRequest(
                dashlet.url,
                null,
                Response.Listener<JSONObject> { response ->
                    dashlet.title = response.getString("title")
                    dashlet.message = response.getString("message")
                    viewModelScope.launch { update(dashlet) }
                },
                Response.ErrorListener {
                    dashlet.message = "Update failed"
                    Log.e("y2k.dash", "refreshDashlet failed: $it")
                    viewModelScope.launch { update(dashlet) }
                }
        )

        requestQueue.add(updateRequest)
    }
}