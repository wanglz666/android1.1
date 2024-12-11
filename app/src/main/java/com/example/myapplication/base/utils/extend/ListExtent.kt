package com.example.myapplication.base.utils.extend

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：
 */
fun List<*>?.hasData(): Boolean {
    return !(this == null || this.isEmpty())
}