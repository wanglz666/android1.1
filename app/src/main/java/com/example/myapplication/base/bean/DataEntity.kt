package com.example.myapplication.base.bean

import com.chad.library.adapter.base.entity.MultiItemEntity
import java.io.Serializable

data class DataEntity(
    var title: String,
    var content: String,
    var type: Int
) : Serializable, MultiItemEntity {

    var imgRes: Int? = 0
    var url: String? = ""
    var level: Int? = null
    var itemList: MutableList<DataEntity>? = null


    override val itemType: Int = type
}
