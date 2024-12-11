package com.example.myapplication.module.recyclerview.multiList

import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.base.utils.extend.infoToast
import com.example.myapplication.module.recyclerview.common.ItemTypeConst


/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：
 */
class BRVAHMultiListProvider1 : BaseItemProvider<DataEntity>() {
    override val itemViewType: Int
        get() = ItemTypeConst.STYLE1
    override val layoutId: Int
        get() = R.layout.item_style1

    /*
     * （可选）
     * 重写返回自己的 ViewHolder。
     * 默认返回 BaseViewHolder()
     */
    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return super.onCreateViewHolder(parent,ItemTypeConst.STYLE1)
    }
    override fun convert(helper: BaseViewHolder, item: DataEntity) {


        helper.setText(R.id.tv_style1, item.title)

    }

    override fun onClick(helper: BaseViewHolder, view: View, data: DataEntity, position: Int) {
        data.title.infoToast()

    }



}