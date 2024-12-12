package com.example.myapplication.module.recyclerview.drag

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.base.utils.constant.ConstData
import com.example.myapplication.base.utils.extend.hasData
import com.example.myapplication.base.utils.extend.infoLongToast
import com.example.myapplication.databinding.ActivityDragListBinding
import java.util.Collections

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：列表拖动
 */

class DragListActivity : BaseActivity() {

    private lateinit var binding: ActivityDragListBinding
    private lateinit var mAdapter: DragListAdapter

    private val itemTouchCallback = object : ItemTouchCallback(){
        override fun onMoveListener(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) {

            val fromPosition = viewHolder.bindingAdapterPosition
            val toPosition = target.bindingAdapterPosition

            // 移动数据源中的元素
            Collections.swap(mAdapter.data, fromPosition, toPosition)

            // 通知 adapter 数据已变更
            mAdapter.notifyItemMoved(fromPosition, toPosition)

        }

    }
    override fun initData(savedInstanceState: Bundle?) {

        mAdapter = DragListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter
        mAdapter.setList(ConstData.initMainData())

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.tvClickViewSort.setOnClickListener {
            if (mAdapter.data.hasData()) {
                val list = arrayListOf<String>()
                mAdapter.data.forEach {
                    list.add(it.level.toString())
                }
                list.toString().infoLongToast()
            }
        }
    }

    override fun bindView(): View? {
        binding = ActivityDragListBinding.inflate(layoutInflater)
        return binding.root
    }

}