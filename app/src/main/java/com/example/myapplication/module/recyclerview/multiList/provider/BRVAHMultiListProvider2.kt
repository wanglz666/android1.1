package com.example.myapplication.module.recyclerview.multiList.provider

import android.view.View
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
class BRVAHMultiListProvider2 : BaseItemProvider<DataEntity>() {
    override val itemViewType: Int
        get() = ItemTypeConst.STYLE2
    override val layoutId: Int
        get() = R.layout.item_style2

    override fun convert(helper: BaseViewHolder, item: DataEntity) {



    }

    override fun onClick(helper: BaseViewHolder, view: View, data: DataEntity, position: Int) {
        data.title.infoToast()

    }


}