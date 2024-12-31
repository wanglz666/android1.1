package com.example.myapplication.base.http

import com.example.myapplication.base.utils.constant.GlobalConst
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private object Holder{
        val INSTANCE = ApiClient()
    }


    companion object {
        val instance = Holder.INSTANCE
    }


    fun <T> instanceRetrofit(apiInterface: Class<T>) : T {

        val mOkHttpClient = OkHttpClient().newBuilder()
            //读取超时时间
            .readTimeout(10000, TimeUnit.SECONDS)
            //连接超时时间
            .connectTimeout(10000, TimeUnit.SECONDS)
            //写出超时时间
            .writeTimeout(10000, TimeUnit.SECONDS)
            .build()



        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(GlobalConst.BASE_URL)
            //请求
            .client(mOkHttpClient)
            //响应
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rxjava来处理
            .addConverterFactory(GsonConverterFactory.create())
            .build()//Gson解析
        return retrofit.create(apiInterface)
    }
}