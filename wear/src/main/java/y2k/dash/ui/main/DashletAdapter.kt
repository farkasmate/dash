package y2k.dash.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fullscreen_dashlet.view.*
import y2k.dash.R
import y2k.dash.shared.data.Dashlet

class DashletAdapter : RecyclerView.Adapter<DashletAdapter.ViewHolder>() {
    private var dashlets: List<Dashlet> = ArrayList()

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dashletView = LayoutInflater.from(parent.context).inflate(R.layout.fullscreen_dashlet, parent, false)
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
