package y2k.dash.shared

import android.util.Log
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import y2k.dash.shared.data.Dashlet

class DashletDataListener : DataClient.OnDataChangedListener {
    private var updateCallback: ((Dashlet) -> Unit)? = null

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        dataEvents.forEach loop@{ event ->
            if (!event.dataItem.uri.path?.startsWith("/dashlet/")!!) return@loop

            when (event.type) {
                DataEvent.TYPE_CHANGED -> {
                    DataMapItem.fromDataItem(event.dataItem).dataMap.apply {
                        val url = getString(DashletKeys.URL.toString())
                        val title = getString(DashletKeys.TITLE.toString())
                        val message = getString(DashletKeys.MESSAGE.toString())
                        val position = getInt(DashletKeys.POSITION.toString())
                        Log.i("dash wear", "$title@$position")
                        updateCallback?.invoke(Dashlet(url, title, message, position))
                    }
                }
                DataEvent.TYPE_DELETED -> {}
            }
        }
    }

    fun onUpdate(callback: (Dashlet) -> Unit) {
        updateCallback = callback
    }
}