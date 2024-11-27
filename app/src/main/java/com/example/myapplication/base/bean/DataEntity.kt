package com.example.myapplication.base.bean

import java.io.Serializable

data class DataEntity(
    var title: String,
    var content: String,
    var level: Int
) : Serializable {

    var imgRes: Int? = 0
    var url: String? = ""
    var type: Int? = 0
}
