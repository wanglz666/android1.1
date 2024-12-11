package com.example.myapplication.module.recyclerview.multiList

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.example.myapplication.base.bean.DataEntity

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：UI和业务分离
 */
class BRVAHMultiListAdapter : BaseProviderMultiAdapter<DataEntity>() {

    init {
        addItemProvider(BRVAHMultiListProvider1())
        addItemProvider(BRVAHMultiListProvider2())
        addItemProvider(BRVAHMultiListProvider3())
    }
    override fun getItemType(data: List<DataEntity>, position: Int): Int {
        return data[position].type
    }
}