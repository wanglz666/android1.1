package com.example.myapplication.base.component

import android.app.Application
import com.example.myapplication.base.component.toast.BaseToast

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // 初始化 BaseToast 框架
        BaseToast.init(this);
    }
}