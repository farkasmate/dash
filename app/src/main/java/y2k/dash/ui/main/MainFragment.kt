package y2k.dash.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_fragment.*
import y2k.dash.R
import y2k.dash.data.Dashlet

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by activityViewModels<DashletViewModel>()
    private var viewAdapter: DashletAdapter = DashletAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.dashlets.observe(viewLifecycleOwner, Observer {
            it?.run {
                viewAdapter.setDashlets(it)
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = viewAdapter

        handleIntent()
    }

    private fun handleIntent() {
        val intentData = activity?.intent?.data
        if (intentData != null) {
            val url = intentData.toString().replace("dashlet://", "https://")
            viewModel.addDashlet(Dashlet(url = url, title = "new dashlet", message = url))
            activity?.intent?.data = null
        }
    }
}
