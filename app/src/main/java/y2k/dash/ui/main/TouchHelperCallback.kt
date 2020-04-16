package y2k.dash.ui.main

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class TouchHelperCallback(private val viewModel: DashletViewModel) : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.RIGHT
) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val adapter = recyclerView.adapter as DashletAdapter
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition

        // FIXME: This stops the dragging immediately
        adapter.notifyItemMoved(from, to)
        viewModel.swapDashlets(from, to)
        Log.i("Moved", "$from to $to")
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    // Never dismiss.
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float = Float.MAX_VALUE
    override fun getSwipeVelocityThreshold(defaultValue: Float): Float = Float.MAX_VALUE
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float = Float.MAX_VALUE
}
