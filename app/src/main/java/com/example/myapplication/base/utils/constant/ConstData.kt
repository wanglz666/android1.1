package com.example.myapplication.base.utils.constant

import com.example.myapplication.base.bean.DataEntity

/**
 * 基础常量
 */
object ConstData {

    const val BASE_URL = "https://www.wanandroid.com/"


    const val CURRENT_ACTIVITY = "CurrentActivity"



    fun initMainData() : List<DataEntity>{
        val dataList = arrayListOf<DataEntity>().apply {
            add(
                DataEntity(
                    "ExpandableListView",
                    "ExpandableListView实现二级可展开列表",
                    ItemType.ITEM_EXPAND_LISTVIEW_TYPE
                )
            )
            add(
                DataEntity(
                    "RecyclerView",
                    "RecyclerView.Adapter实现多类型列表",
                    ItemType.ITEM_SIMPLE_MULTI_TYPE
                )
            )
            add(
                DataEntity(
                    "RecyclerView",
                    "BRVAH-BaseMultiItemQuickAdapter快速使用简单的多类型",
                    ItemType.ITEM_BRVAH_SIMPLE_MULTI_TYPE
                )
            )
            add(
                DataEntity(
                    "RecyclerView",
                    "BRVAH-BaseProviderMultiAdapter实现多类型并且业务复杂的列表",
                    ItemType.ITEM_BRVAH_MULTI_TYPE
                )
            )
            add(
                DataEntity(
                    "RecyclerView",
                    "BRVAH-RecyclerView实现列表拖动",
                    ItemType.ITEM_BRVAH_DRAG_TYPE
                )
            )
            add(
                DataEntity(
                    "RecyclerView",
                    "BRVAH-RecyclerViews实现双列表互相拖动",
                    ItemType.ITEM_BRVAH_DOUBLE_DRAG_TYPE
                )
            )
            add(
                DataEntity(
                    "RecyclerView",
                    "BRVAH-BaseSectionQuickAdapter快速实现带头部的 Adapter",
                    6
                )
            )
            add(
                DataEntity(
                    "RecyclerView",
                    "BRVAH-BaseNodeAdapter类似节点树功能的Adapter，具有展开、收起节点的功能",
                    7
                )
            )
        }

        dataList.forEachIndexed { index, dataEntity ->
            dataEntity.level = index+1
        }

        return dataList
    }
}