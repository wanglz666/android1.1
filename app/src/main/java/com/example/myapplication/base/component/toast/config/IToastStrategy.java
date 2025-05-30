package com.example.myapplication.base.component.toast.config;

import android.app.Application;

import com.example.myapplication.base.component.toast.ToastParams;

/**
 *    time   : 2019/05/19
 *    desc   : Toast 处理策略
 */
public interface IToastStrategy {

    /**
     * 注册策略
     */
    void registerStrategy(Application application);

    /**
     * 创建 Toast
     */
    IToast createToast(ToastParams params);

    /**
     * 显示 Toast
     */
    void showToast(ToastParams params);

    /**
     * 取消 Toast
     */
    void cancelToast();
}