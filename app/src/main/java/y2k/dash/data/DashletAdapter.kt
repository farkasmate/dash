package y2k.dash.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import y2k.dash.R

class DashletAdapter : RecyclerView.Adapter<DashletAdapter.ViewHolder>() {
    private var dashlets: List<Dashlet> = ArrayList()

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dashletView = LayoutInflater.from(parent.context).inflate(R.layout.main_dashlet, parent, false)
        return ViewHolder(dashletView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = dashlets[position].title
        holder.messageTextView.text = dashlets[position].message
    }

    override fun getItemCount() = dashlets.size

    fun setDashlets(dashlets: List<Dashlet>) {
        this.dashlets = dashlets
    }
}
