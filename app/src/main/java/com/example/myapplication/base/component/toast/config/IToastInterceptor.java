package com.example.myapplication.base.component.toast.config;


import com.example.myapplication.base.component.toast.ToastParams;

/**
 *    time   : 2019/05/19
 *    desc   : Toast 拦截器接口
 */
public interface IToastInterceptor {

    /**
     * 根据显示的文本决定是否拦截该 Toast
     */
    boolean intercept(ToastParams params);
}