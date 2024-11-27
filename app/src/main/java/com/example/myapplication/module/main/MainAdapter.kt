package com.example.myapplication.module.main

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.base.bean.DataEntity

class MainAdapter(resLayout: Int) : BaseQuickAdapter<DataEntity, BaseViewHolder>(resLayout){


    override fun convert(holder: BaseViewHolder, item: DataEntity) {
        holder.apply {
            setText(R.id.tv_level, item.level.toString())
            setText(R.id.tv_title, item.title)
            setText(R.id.tv_content, item.content)
        }
    }
}