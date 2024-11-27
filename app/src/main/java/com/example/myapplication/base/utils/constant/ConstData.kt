package com.example.myapplication.base.utils.constant

import com.example.myapplication.base.bean.DataEntity

/**
 * 基础常量
 */
object ConstData {

    const val BASE_URL = "https://www.wanandroid.com/"


    const val CURRENT_ACTIVITY = "CurrentActivity"



    fun initMainData() : List<DataEntity>{

        return arrayListOf<DataEntity>().apply {
            add(DataEntity("Item-Title-1","ExpandList二级列表",1))
            add(DataEntity("Item-Title-2","RecyclerView列表拖动",2))
            add(DataEntity("Item-Title-3","RecyclerViews双列表拖动",3))
            add(DataEntity("Item-Title-4","BaseMultiItemQuickAdapter快速使用简单的多类型",4))
            add(DataEntity("Item-Title-5","BaseProviderMultiAdapter多类型复杂列表",5))
            add(DataEntity("Item-Title-6","BaseSectionQuickAdapter快速实现带头部的 Adapter",6))
            add(DataEntity("Item-Title-7","BaseNodeAdapter类似节点树功能的Adapter，具有展开、收起节点的功能",7))
        }
    }
}