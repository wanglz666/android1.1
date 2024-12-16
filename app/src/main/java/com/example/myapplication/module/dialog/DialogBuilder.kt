package com.example.myapplication.module.dialog

import java.io.Serializable

/**
 * Created by WangLiZhi on 2024/12/16.
 * Desc：
 */
class DialogBuilder : Serializable {
    public fun build(): DialogBuilder{
        return DialogBuilder()
    }
}