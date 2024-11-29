package com.example.myapplication.base.component

import android.view.View

interface IBaseActivity {


    /**
     * 绑定View Binding
     */
    fun bindView(): View?

    /**
     * 绑定布局
     */
    fun bindLayout(): Int?
}