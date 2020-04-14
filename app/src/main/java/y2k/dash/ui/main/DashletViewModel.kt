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
import java.net.URI

class DashletViewModel(application: Application) : AndroidViewModel(application) {
    private val version = application.packageManager.getPackageInfo(application.packageName, 0).versionName

    private val requestQueue = RequestQueueSingleton.getInstance(application)
    private val dao = DashletDatabase.getInstance(application).dashletDao()

    val dashlets = dao.getAll()

//    init {
//        addSettings()
//    }

    private fun addSettings() {
        viewModelScope.launch {
            dao.dropDashlets()
            dao.insert(Dashlet("setting://version", "App version", version))
            dao.insert(Dashlet("setting://test/1", "Test 1", "1"))
            dao.insert(Dashlet("setting://test/2", "Test 2", "2"))
            dao.insert(Dashlet("setting://test/3", "Test 3", "3"))
            dao.insert(Dashlet("setting://test/4", "Test 4", "4"))
            dao.insert(Dashlet("setting://test/5", "Test 5", "5"))
            dao.insert(Dashlet("setting://test/6", "Test 6", "6"))
            dao.insert(Dashlet("setting://test/7", "Test 7", "7"))
            dao.insert(Dashlet("setting://test/8", "Test 8", "8"))
        }
    }

    private fun refreshDashlet(dashlet: Dashlet) {
        if (URI(dashlet.url).scheme == "setting") return

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
        refreshDashlet(dashlet)
    }

    fun refreshDashlets() {
        dashlets.value?.forEach { dashlet -> refreshDashlet(dashlet) }
    }

    fun setOnRefreshFinishedListener(listener: () -> Unit) {
        requestQueue.setOnFinishedListener { listener() }
    }
}
