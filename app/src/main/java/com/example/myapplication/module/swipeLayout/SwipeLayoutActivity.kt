package com.example.myapplication.module.swipeLayout

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.base.utils.extend.successToast
import com.example.myapplication.databinding.ActivitySimpleMultiListBinding

/**
 * 滑动菜单
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
        mAdapter.setOnItemChildClickListener { _, view, _ ->
            when (view.id) {
                R.id.left_menu -> {
                    "Left Click".successToast()
                }

                R.id.right_menu -> {
                    "Right1 Click".successToast()
                }

                R.id.right_menu2 -> {
                    "Right2 Click".successToast()
                }
            }
        }
    }

    override fun bindView(): View? {
        binding = ActivitySimpleMultiListBinding.inflate(layoutInflater)
        return binding.root
    }

    class SwipeLayoutAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_swipe_simple_layout){
        init {
            addChildClickViewIds(R.id.left_menu, R.id.right_menu, R.id.right_menu2)
        }
        override fun convert(holder: BaseViewHolder, item: String) {
            holder.apply {
                setText(R.id.tv_content, item)
            }
        }

    }
}