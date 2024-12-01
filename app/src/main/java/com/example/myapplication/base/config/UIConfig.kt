//package com.example.myapplication.base.config
//
//import android.content.Context
//
///**
// * Created by WangLiZhi on 2024/12/1.
// * Desc：
// */
//class UIConfig {
//
//    fun smartRefreshLayout() {
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator() {
//            fun createRefreshHeader(context: Context, layout: RefreshLayout): RefreshHeader? {
//                val header = MaterialHeader(context)
//                header.setProgressBackgroundColorSchemeColor(
//                    SkinManager.getResourceManager().getColor(R.color.skin_loading_bg)
//                )
//                header.setColorSchemeColors(
//                    SkinManager.getResourceManager().getColor(R.color.skin_main_color)
//                )
//                return header
//            }
//        })
//        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator() {
//            fun createRefreshFooter(context: Context, layout: RefreshLayout): RefreshFooter? {
//                return ClassicsFooter(context)
//            }
//        })
//    }
//}