package y2k.dash

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.wearable.Wearable
import y2k.dash.shared.DashletDataListener

class MainActivity : FragmentActivity() {
    private val dataListener: DashletDataListener = DashletDataListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance(dataListener))
                    .commitNow()
        }

        // Enables Always-on
        //setAmbientEnabled()
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
