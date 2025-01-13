package com.example.myapplication.base.bean

import java.io.Serializable

/**
 * Created by WangLiZhi on 2024/12/16.
 * Desc：
 */
class ParamsBuilder : Serializable {

    companion object{
        @JvmStatic
        fun build(): ParamsBuilder {
            return ParamsBuilder()
        }
    }
}