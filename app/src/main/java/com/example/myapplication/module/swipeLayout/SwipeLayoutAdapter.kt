package com.example.myapplication.module.swipeLayout

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.base.bean.DataEntity

/**
 * Created by WangLiZhi on 2024/12/31.
 * Desc：
 */
class SwipeLayoutAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_swipe_simple_layout){
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.apply {
            setText(R.id.tv_content, item)
        }
    }

}