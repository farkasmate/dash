package y2k.dash.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import y2k.dash.data.Dashlet

class MainViewModel : ViewModel() {
    private val _dashlets = MutableLiveData<List<Dashlet>>()
    val dashlets: LiveData<List<Dashlet>>
        get() = _dashlets

    init {
        var list = ArrayList<Dashlet>()
        list.add(Dashlet(title = "asdf", message = "recece"))
        list.add(Dashlet(title = "lorem", message = "ipsum"))
        _dashlets.value = list
    }
}
