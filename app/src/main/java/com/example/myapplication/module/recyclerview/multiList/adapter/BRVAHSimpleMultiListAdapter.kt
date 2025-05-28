package com.example.myapplication.module.recyclerview.multiList.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.module.recyclerview.common.ItemTypeConst

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：
 */
class BRVAHSimpleMultiListAdapter : BaseMultiItemQuickAdapter<DataEntity, BaseViewHolder>() {

    init {
        // 绑定 layout 对应的 type
        addItemType(ItemTypeConst.STYLE1, R.layout.item_style1)
        addItemType(ItemTypeConst.STYLE2, R.layout.item_style2)
        addItemType(ItemTypeConst.STYLE3, R.layout.item_style3)
    }

    override fun convert(holder: BaseViewHolder, item: DataEntity) {
        when (holder.itemViewType) {
            ItemTypeConst.STYLE1 -> {
                holder.setText(R.id.tv_style1, "加载文本："+item.title)
            }
            ItemTypeConst.STYLE2 -> {
                holder.setText(R.id.tv_style2, "加载文本："+item.title)
            }
            ItemTypeConst.STYLE3 -> {
                holder.setText(R.id.tv_style3, "加载文本："+item.title)
            }
        }

    }
}