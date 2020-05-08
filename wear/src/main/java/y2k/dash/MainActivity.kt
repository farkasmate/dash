package y2k.dash

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import com.google.android.gms.wearable.Wearable
import kotlinx.android.synthetic.main.activity_main.*
import y2k.dash.shared.DashletDataListener

class MainActivity : WearableActivity() {
    private val dataListener: DashletDataListener = DashletDataListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        dataListener.onUpdate { str ->
            text.text = str
        }
    }

    override fun onResume() {
        super.onResume()
        Wearable.getDataClient(this).addListener(dataListener)
    }

    override fun onPause() {
        super.onPause()
        Wearable.getDataClient(this).removeListener(dataListener)
    }
}
