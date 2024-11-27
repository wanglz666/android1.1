package com.example.myapplication.base.api

import com.example.myapplication.module.login.model.LoginResponse
import com.example.myapplication.base.bean.HttpResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * WanAndroidAPI https://wanandroid.com/blog/show/2
 */
interface WanAndroidAPI {

    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun loginAction(@Field("username") username:String
                    ,@Field("password") password :String)
    : HttpResponse<LoginResponse>

    /**
     * 注册
     */
    @POST("user/register")
    @FormUrlEncoded
    suspend fun registerAction(@Field("username") username:String
                       ,@Field("password") password :String
                       ,@Field("repassword") repassword :String)
            : HttpResponse<LoginResponse>


}