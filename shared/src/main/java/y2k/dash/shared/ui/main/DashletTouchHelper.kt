package y2k.dash.shared.ui.main

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class DashletTouchHelper(private val viewModel: DashletViewModel) : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT,
        0
) {
    private var startingPosition: Int? = null
    private var endPosition: Int? = null

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val adapter = recyclerView.adapter as DashletAdapter
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition

        if (startingPosition == null)
            startingPosition = from

        endPosition = to

        adapter.notifyItemMoved(from, to)
        return true
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG && isCurrentlyActive) {
            val dashletViewHolder = viewHolder as DashletAdapter.ViewHolder
            dashletViewHolder.startMoveAnimation()
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val dashletViewHolder = viewHolder as DashletAdapter.ViewHolder
        dashletViewHolder.stopMoveAnimation()

        if (startingPosition == endPosition) return
        if (startingPosition == null || endPosition == null) return

        viewModel.moveDashlet(startingPosition!!, endPosition!!)
        startingPosition = null
        endPosition = null

        super.clearView(recyclerView, viewHolder)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}
