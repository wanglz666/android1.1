package com.example.myapplication.base.component

import android.app.Application
import android.content.Context
import com.example.myapplication.base.component.toast.BaseToast

class BaseApplication : Application() {
    companion object {
        private lateinit var instance: BaseApplication

        fun getContext(): Context {
            return instance.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        // 初始化 BaseToast 框架
        BaseToast.init(this);
    }
}