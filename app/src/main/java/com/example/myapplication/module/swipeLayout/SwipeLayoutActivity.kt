package com.example.myapplication.module.swipeLayout

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.databinding.ActivitySimpleMultiListBinding

/**
 * 滑动菜单
 * https://github.com/aitsuki/SwipeMenuRecyclerView/tree/master
 */
class SwipeLayoutActivity : BaseActivity() {

    private lateinit var binding: ActivitySimpleMultiListBinding

    override fun initData(savedInstanceState: Bundle?) {

        val mData = arrayListOf<String>()

        repeat(10) {
            mData.add("TITLE $it")
        }

        val mAdapter = SwipeLayoutAdapter()
        mAdapter.setList(mData)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter
    }

    override fun bindView(): View? {
        binding = ActivitySimpleMultiListBinding.inflate(layoutInflater)
        return binding.root
    }
}