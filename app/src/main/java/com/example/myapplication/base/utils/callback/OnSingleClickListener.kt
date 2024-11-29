package com.example.myapplication.base.utils.callback

import android.view.View

/**
 * 保证同一按钮在1秒内只会响应一次点击事件
 */
abstract class OnSingleClickListener : View.OnClickListener {

    private var lastClickTime: Long = 0

    abstract fun onSingleClick(view: View?)

    override fun onClick(view: View) {
        val curClickTime = System.currentTimeMillis()
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = curClickTime
            onSingleClick(view)
        }
    }

    companion object {
        //两次点击按钮之间的间隔，目前为1000ms
        private const val MIN_CLICK_DELAY_TIME = 1000
    }
}