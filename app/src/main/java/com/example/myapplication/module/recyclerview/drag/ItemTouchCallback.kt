package com.example.myapplication.module.recyclerview.drag

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class ItemTouchCallback : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val swipeFlags = 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // 调用外部的 onMoveListener 逻辑
        onMoveListener(recyclerView, viewHolder, target)
        return true
    }

    /**
     * 抽象方法，由外部重写实现拖拽逻辑
     */
    abstract fun onMoveListener(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    )

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // 可在子类中实现滑动删除逻辑
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true // 支持长按拖动
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false // 不支持滑动删除
    }
}
