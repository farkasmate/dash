package y2k.dash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import y2k.dash.ui.main.MainFragment
import y2k.dash.utils.WearDashletSyncer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

}
