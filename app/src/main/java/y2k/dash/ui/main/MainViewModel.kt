package y2k.dash.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.coroutines.launch
import org.json.JSONObject
import y2k.dash.data.Dashlet
import y2k.dash.data.DashletDatabase
import y2k.dash.utils.RequestQueueSingleton

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val requestQueue = RequestQueueSingleton.getInstance(application).requestQueue
    private val dao = DashletDatabase.getInstance(application).dashletDao()

    val dashlets = dao.getAll()

//    init {
//        viewModelScope.launch { dao.dropDashlets() }
//    }

    private fun updateDashlet(dashlet: Dashlet) {
        val updateRequest = JsonObjectRequest(
                dashlet.url,
                null,
                Response.Listener<JSONObject> { response ->
                    dashlet.title = response.getString("title")
                    dashlet.message = response.getString("message")
                    viewModelScope.launch { dao.update(dashlet) }
                },
                Response.ErrorListener {
                    dashlet.message = "Update failed"
                    viewModelScope.launch { dao.update(dashlet) }
                }
        )

        requestQueue.add(updateRequest)
    }

    fun addDashlet(dashlet: Dashlet) {
        viewModelScope.launch { dao.insert(dashlet) }
        updateDashlet(dashlet)
    }
}
