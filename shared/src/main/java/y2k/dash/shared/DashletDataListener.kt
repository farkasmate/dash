package y2k.dash.shared

import android.util.Log
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem

class DashletDataListener : DataClient.OnDataChangedListener {
    private var updateCallback: ((String) -> Unit)? = null

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        dataEvents.forEach loop@{ event ->
            if (!event.dataItem.uri.path?.startsWith("/dashlet/")!!) return@loop

            when (event.type) {
                DataEvent.TYPE_CHANGED -> {
                    DataMapItem.fromDataItem(event.dataItem).dataMap.apply {
                        val title = getString(DashletKeys.TITLE.toString())
                        val position = getInt(DashletKeys.POSITION.toString())
                        Log.i("dash wear", "$title@$position")
                        updateCallback?.invoke(title)
                    }
                }
                DataEvent.TYPE_DELETED -> {}
            }
        }
    }

    fun onUpdate(callback: (String) -> Unit) {
        updateCallback = callback
    }
}