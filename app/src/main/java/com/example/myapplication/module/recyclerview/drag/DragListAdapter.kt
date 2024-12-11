package com.example.myapplication.module.recyclerview.drag

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.base.bean.DataEntity

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：
 */
class DragListAdapter: BaseQuickAdapter<DataEntity, BaseViewHolder>(R.layout.item_simple_layout) {
    override fun convert(holder: BaseViewHolder, item: DataEntity) {
        holder.apply {
            setText(R.id.tv_level, item.level.toString())
            setText(R.id.tv_title, item.title)
            setText(R.id.tv_content, item.content)
        }
    }
}