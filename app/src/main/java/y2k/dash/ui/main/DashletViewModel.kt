package y2k.dash.ui.main

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import y2k.dash.data.Dashlet
import y2k.dash.data.DashletDatabase
import y2k.dash.data.DashletRepository
import y2k.dash.utils.RequestQueueSingleton

// FIXME: Remove Application reference from ViewModel
class DashletViewModel(application: Application) : AndroidViewModel(application) {
    private val version = application.packageManager.getPackageInfo(application.packageName, 0).versionName

    private val requestQueue = RequestQueueSingleton.getInstance(application)
    private val repo = DashletRepository(DashletDatabase.getInstance(application).dashletDao(), requestQueue, viewModelScope)

    val dashlets = repo.getAll()

//    init {
//        addSettings()
//    }

    private fun addSettings() {
        repo.dropDashlets()
        repo.insert(Dashlet("setting://version", "App version", version))
        repo.insert(Dashlet("setting://test/1", "Test 1", "1"))
        repo.insert(Dashlet("setting://test/2", "Test 2", "2"))
        repo.insert(Dashlet("setting://test/3", "Test 3", "3"))
        repo.insert(Dashlet("setting://test/4", "Test 4", "4"))
        repo.insert(Dashlet("setting://test/5", "Test 5", "5"))
        repo.insert(Dashlet("setting://test/6", "Test 6", "6"))
        repo.insert(Dashlet("setting://test/7", "Test 7", "7"))
        repo.insert(Dashlet("setting://test/8", "Test 8", "8"))
    }

    fun addDashlet(dashlet: Dashlet) = repo.insert(dashlet)
    fun moveDashlet(from: Int, to: Int) = repo.moveDashlet(from, to)

    fun refreshDashlets() {
        if (dashlets.value.isNullOrEmpty()) {
            requestQueue.onFinished()
            return
        }

        dashlets.value?.forEach { dashlet -> repo.refreshDashlet(dashlet) }
    }
    fun cancelRefresh() = requestQueue.cancelAll()

    fun setOnRefreshFinishedListener(listener: () -> Unit) = requestQueue.setOnFinishedListener { listener() }
}
