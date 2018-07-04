package y2k.dash.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import y2k.dash.data.Dashlet
import y2k.dash.utils.RequestQueueSingleton

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val requestQueue = RequestQueueSingleton.getInstance(application).requestQueue
    private val _dashlets = MutableLiveData<List<Dashlet>>()
    val dashlets: LiveData<List<Dashlet>>
        get() = _dashlets

    init {
        var list = ArrayList<Dashlet>()
        list.add(Dashlet(title = "A", message = "alpha"))
        list.add(Dashlet(title = "B", message = "bravo"))
        list.add(Dashlet(title = "C", message = "charlie"))
        list.add(Dashlet(title = "D", message = "delta"))
        list.add(Dashlet(title = "E", message = "echo"))
        list.add(Dashlet(title = "F", message = "foxtrot"))
        list.add(Dashlet(title = "G", message = "golf"))
        list.add(Dashlet(title = "H", message = "hotel"))
        list.add(Dashlet(title = "I", message = "india"))
        list.add(Dashlet(title = "J", message = "juliett"))
        list.add(Dashlet(title = "K", message = "kilo"))
        list.add(Dashlet(title = "L", message = "lima"))
        list.add(Dashlet(title = "M", message = "mike"))
        list.add(Dashlet(title = "N", message = "november"))
        list.add(Dashlet(title = "O", message = "oscar"))
        list.add(Dashlet(title = "P", message = "papa"))
        list.add(Dashlet(title = "Q", message = "quebec"))
        list.add(Dashlet(title = "R", message = "romeo"))
        list.add(Dashlet(title = "S", message = "sierra"))
        list.add(Dashlet(title = "T", message = "tango"))
        list.add(Dashlet(title = "U", message = "uniform"))
        list.add(Dashlet(title = "V", message = "victor"))
        list.add(Dashlet(title = "W", message = "whiskey"))
        list.add(Dashlet(title = "X", message = "xray"))
        list.add(Dashlet(title = "Y", message = "yankee"))
        list.add(Dashlet(title = "Z", message = "zulu"))
        _dashlets.value = list
    }

    private fun updateDashlet(dashlet: Dashlet) {
        val dashletList = _dashlets.value as ArrayList<Dashlet>
        // TODO: Error handling
        val index = dashletList.indexOf(dashlet)
        val updateRequest = JsonObjectRequest(
                dashlet.url,
                null,
                Response.Listener<JSONObject> { response ->
                    _dashlets.value?.get(index)?.title = response.getString("title")
                    _dashlets.value?.get(index)?.message = response.getString("message")
                    _dashlets.value = _dashlets.value
                },
                Response.ErrorListener {
                    _dashlets.value?.get(index)?.message = "Update failed"
                    _dashlets.value = _dashlets.value
                }
        )

        requestQueue.add(updateRequest)
    }

    fun addDashlet(dashlet: Dashlet) {
        val dashletList = _dashlets.value as ArrayList<Dashlet>
        dashletList.add(0, dashlet)
        _dashlets.value = dashletList
        updateDashlet(dashlet)
    }
}
