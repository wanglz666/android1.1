package com.example.myapplication.base.utils.constant

import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.module.flowLayout.ShowItem


/**
 * 基础常量
 */
object GlobalConst {

    const val BASE_URL = "https://www.wanandroid.com/"


    const val CURRENT_ACTIVITY = "CurrentActivity"

    const val PUBLIC_TAG = "PublicTag"



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
                    "BRVAH-BaseNodeAdapter类似节点树功能的Adapter，具有展开、收起节点的功能",
                    ItemType.ITEM_BRVAH_NODE_TYPE
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
                    ItemType.ITEM_BRVAH_PAIR_DRAG_TYPE
                )
            )
            add(
                DataEntity(
                    "RecyclerView",
                    "RecyclerView实现二级列表带吸顶效果",
                    ItemType.ITEM_CEILING_TYPE
                )
            )
            add(
                DataEntity(
                    "RecyclerView",
                    "RecyclerView实现首页二级列表带吸顶效果",
                    ItemType.ITEM_MAIN_CEILING_TYPE
                )
            )
            add(
                DataEntity(
                    "Dialog",
                    "实现可以滑动关闭的弹窗",
                    ItemType.ITEM_DIALOG_SLIDE_TYPE
                )
            )
            add(
                DataEntity(
                    "Text流式布局",
                    "Text流式布局Text流式布局",
                    ItemType.ITEM_FLOW_TEXT_TYPE
                )
            )
            add(
                DataEntity(
                    "列表菜单滑动",
                    "类似QQ左划",
                    ItemType.ITEM_SWIPE_LAYOUT_TYPE
                )
            )
            add(
                DataEntity(
                    "仿ios回弹",
                    "仿ios回弹动画",
                    ItemType.ITEM_SPRING_BACK_TYPE
                )
            )

            add(
                DataEntity(
                    "实现跟随标签",
                    "单行或多行，标签前可显示...",
                    ItemType.ITEM_FOLLOW_TAG_TYPE
                )
            )

        }

        dataList.forEachIndexed { index, dataEntity ->
            dataEntity.level = index+1
        }

        return dataList
    }

    fun getItems(): List<ShowItem> {
        val showItems: MutableList<ShowItem> = ArrayList<ShowItem>()
        showItems.add(ShowItem("1.C"))
        showItems.add(ShowItem("2.Java"))
        showItems.add(ShowItem("3.Objective-C"))
        showItems.add(ShowItem("4.C++"))
        showItems.add(ShowItem("5.PHP"))
        showItems.add(ShowItem("6.C#"))
        showItems.add(ShowItem("7.(Visual) Basic"))
        showItems.add(ShowItem("8.Python"))
        showItems.add(ShowItem("9.Perl"))
        showItems.add(ShowItem("10.JavaScript"))
        showItems.add(ShowItem("11.Ruby"));
        showItems.add(ShowItem("12.Visual Basic .NET"));
        showItems.add(ShowItem("13.Transact-SQL"));
        showItems.add(ShowItem("14.Lisp"));
        showItems.add(ShowItem("15.Pascal"));
        showItems.add(ShowItem("16.Bash"));
        showItems.add(ShowItem("17.PL/SQL"));
        showItems.add(ShowItem("18.Delphi/Object Pascal"));
        showItems.add(ShowItem("19.Ada"));
        showItems.add(ShowItem("20.MATLAB"));
        showItems.add(ShowItem("1.C"));
        showItems.add(ShowItem("2.Java"));
        showItems.add(ShowItem("3.Objective-C"));
        showItems.add(ShowItem("4.C++"));
        showItems.add(ShowItem("5.PHP"));
        showItems.add(ShowItem("6.C#"));
        showItems.add(ShowItem("7.(Visual) Basic"));
        showItems.add(ShowItem("8.Python"));
        showItems.add(ShowItem("9.Perl"));
        showItems.add(ShowItem("10.JavaScript"));
        showItems.add(ShowItem("11.Ruby"));
        showItems.add(ShowItem("12.Visual Basic .NET"));
        showItems.add(ShowItem("13.Transact-SQL"));
        showItems.add(ShowItem("14.Lisp"));
        showItems.add(ShowItem("15.Pascal"));
        showItems.add(ShowItem("16.Bash"));
        showItems.add(ShowItem("17.PL/SQL"));
        showItems.add(ShowItem("18.Delphi/Object Pascal"));
        showItems.add(ShowItem("19.Ada"));
        showItems.add(ShowItem("20.MATLAB"));
        showItems.add(ShowItem("1.C"));
        showItems.add(ShowItem("2.Java"));
        showItems.add(ShowItem("3.Objective-C"));
        showItems.add(ShowItem("4.C++"));
        showItems.add(ShowItem("5.PHP"));
        showItems.add(ShowItem("6.C#"));
        showItems.add(ShowItem("7.(Visual) Basic"));
        showItems.add(ShowItem("8.Python"));
        showItems.add(ShowItem("9.Perl"));
        showItems.add(ShowItem("10.JavaScript"));
        showItems.add(ShowItem("11.Ruby"));
        showItems.add(ShowItem("12.Visual Basic .NET"));
        showItems.add(ShowItem("13.Transact-SQL"));
        showItems.add(ShowItem("14.Lisp"));
        showItems.add(ShowItem("15.Pascal"));
        showItems.add(ShowItem("16.Bash"));
        showItems.add(ShowItem("17.PL/SQL"));
        showItems.add(ShowItem("18.Delphi/Object Pascal"));
        showItems.add(ShowItem("19.Ada"));
        showItems.add(ShowItem("20.MATLAB"));
        return showItems
    }
}