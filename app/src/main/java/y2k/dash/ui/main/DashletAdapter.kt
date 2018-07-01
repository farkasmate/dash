package y2k.dash.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_dashlet.view.*
import y2k.dash.R
import y2k.dash.data.Dashlet

class DashletAdapter : RecyclerView.Adapter<DashletAdapter.ViewHolder>() {
    private var dashlets: List<Dashlet> = ArrayList()

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.titleTextView
        val messageTextView: TextView = view.messageTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dashletView = LayoutInflater.from(parent.context).inflate(R.layout.main_dashlet, parent, false)
        return ViewHolder(dashletView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = dashlets[position].title
        holder.messageTextView.text = dashlets[position].message

        val maxOpacity = 200
        val gradedColor = Color.argb(maxOpacity*position/dashlets.size, 1, 1, 1)
        holder.view.setBackgroundColor(gradedColor)
    }

    override fun getItemCount() = dashlets.size

    fun setDashlets(dashlets: List<Dashlet>) {
        this.dashlets = dashlets
    }
}
