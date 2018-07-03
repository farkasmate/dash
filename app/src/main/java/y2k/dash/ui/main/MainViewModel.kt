package y2k.dash.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import y2k.dash.data.Dashlet
import y2k.dash.utils.RequestQueueSingleton

class MainViewModel(application: Application) : AndroidViewModel(application) {
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

        val testRequest = JsonObjectRequest(
                "https://gist.githubusercontent.com/farkasmate/386fbd6d9328713e525b9742244c1695/raw/e69c0319c7a1b80956879c62220fc89bae4169f1/dash_test_json_01.json",
                null,
                Response.Listener<JSONObject> { response ->
                    _dashlets.value?.get(0)?.message = response.getString("message")
                    _dashlets.value = _dashlets.value
                },
                Response.ErrorListener {
                    _dashlets.value?.get(0)?.message = "Failed"
                    _dashlets.value = _dashlets.value
                }
        )

        RequestQueueSingleton.getInstance(application).addToRequestQueue(testRequest)
    }
}
