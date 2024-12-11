package com.example.myapplication.module.recyclerview.drag

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.base.utils.callback.ItemTouchCallback
import com.example.myapplication.base.utils.constant.ConstData
import com.example.myapplication.base.utils.extend.hasData
import com.example.myapplication.base.utils.extend.infoLongToast
import com.example.myapplication.base.utils.extend.infoToast
import com.example.myapplication.databinding.ActivityDragListBinding

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：
 *
 */

class DragListActivity : BaseActivity() {

    private lateinit var binding: ActivityDragListBinding
    private lateinit var mAdapter: DragListAdapter

    override fun initData(savedInstanceState: Bundle?) {

        mAdapter = DragListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter
        mAdapter.setList(ConstData.initMainData())
        val itemTouchCallback = ItemTouchCallback(mAdapter)
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