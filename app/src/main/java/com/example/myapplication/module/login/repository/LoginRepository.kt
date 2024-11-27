package com.example.myapplication.module.login.repository

import com.example.myapplication.base.api.WanAndroidAPI
import com.example.myapplication.base.http.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by WangLiZhi on 2024/11/27.
 * Desc：
 */
object LoginRepository {

    private val service = ApiClient.instance.instanceRetrofit(WanAndroidAPI::class.java)

    suspend fun login(username: String, password: String) =
        withContext(Dispatchers.IO) {

            service.loginAction(username, password).data
        }

    suspend fun register(username: String, password: String, rePassword: String) =

        withContext(Dispatchers.IO) {

            service.registerAction(username, password, rePassword)
        }

}