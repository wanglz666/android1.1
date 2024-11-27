package com.example.myapplication.base.bean

data class HttpResponse<T>(val data: T, val errorCode: Int, val errorMsg: String)

