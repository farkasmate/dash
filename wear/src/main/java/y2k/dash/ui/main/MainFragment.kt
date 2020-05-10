package y2k.dash.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import kotlinx.android.synthetic.main.main_fragment.*
import y2k.dash.R
import y2k.dash.shared.DashletDataListener
import y2k.dash.shared.DashletViewModel

class MainFragment(private val dataListener: DashletDataListener) : Fragment() {
    companion object {
        fun newInstance(dataListener: DashletDataListener) = MainFragment(dataListener)
    }

    private val viewModel by activityViewModels<DashletViewModel>()
    private var viewAdapter: DashletAdapter = DashletAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.dashlets.observe(viewLifecycleOwner, Observer { dashlets ->
            viewAdapter.setDashlets(dashlets)
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = viewAdapter

        swipe.setOnRefreshListener { viewModel.refreshDashlets() }
        viewModel.setOnRefreshFinishedListener { swipe.isRefreshing = false }

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        dataListener.onUpdate { dashlet ->
            // FIXME: Insert/Update?
            viewModel.addDashlet(dashlet)
        }
    }
}
