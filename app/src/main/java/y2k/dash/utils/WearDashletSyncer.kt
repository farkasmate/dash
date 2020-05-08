package y2k.dash.utils

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.PutDataRequest
import com.google.android.gms.wearable.Wearable
import y2k.dash.data.Dashlet
import y2k.dash.shared.DashletKeys

class WearDashletSyncer constructor(private val context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: WearDashletSyncer? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: WearDashletSyncer(context)
        }
    }

    fun sync(dashlets: List<Dashlet>) {
        dashlets.forEachIndexed { index, dashlet ->
            val putDataReq: PutDataRequest = PutDataMapRequest.create("/dashlet/$index").run {
                dataMap.putString(DashletKeys.URL.toString(), dashlet.url)
                dataMap.putString(DashletKeys.TITLE.toString(), dashlet.title)
                dataMap.putString(DashletKeys.MESSAGE.toString(), dashlet.message)
                dataMap.putInt(DashletKeys.POSITION.toString(), dashlet.position)
                setUrgent()
                asPutDataRequest()
            }

            // FIXME
            val t = Wearable.getDataClient(context).putDataItem(putDataReq)
            t.addOnCanceledListener { Log.i("dash data", "canceled") }
            t.addOnCompleteListener { Log.i("dash data", "complete") }
            t.addOnFailureListener { Log.i("dash data", "failure") }
            t.addOnSuccessListener { Log.i("dash data", "success") }
        }
    }
}