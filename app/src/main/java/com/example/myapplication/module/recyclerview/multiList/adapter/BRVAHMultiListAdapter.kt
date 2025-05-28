package com.example.myapplication.module.recyclerview.multiList.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.module.recyclerview.multiList.provider.BRVAHMultiListProvider1
import com.example.myapplication.module.recyclerview.multiList.provider.BRVAHMultiListProvider2
import com.example.myapplication.module.recyclerview.multiList.provider.BRVAHMultiListProvider3

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