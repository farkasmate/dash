package y2k.dash.shared.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dashlet.view.*
import y2k.dash.shared.R
import y2k.dash.shared.data.Dashlet

class DashletAdapter : RecyclerView.Adapter<DashletAdapter.ViewHolder>() {
    private var dashlets: List<Dashlet> = ArrayList()

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun startMoveAnimation() { view.alpha = 0.75f }
        fun stopMoveAnimation() { view.alpha = 1.0f }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dashletView = LayoutInflater.from(parent.context).inflate(R.layout.dashlet, parent, false)
        return ViewHolder(dashletView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.titleTextView.text = dashlets[position].title
        holder.view.messageTextView.text = dashlets[position].message
    }

    override fun getItemCount() = dashlets.size

    fun setDashlets(dashlets: List<Dashlet>) {
        this.dashlets = dashlets
        notifyDataSetChanged()
    }
}
