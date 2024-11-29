package com.example.myapplication.base.component

import android.app.Activity

object ActivityManager {


    private var activitys: MutableList<Activity> = ArrayList()

    /**
     * 向List中添加一个活动
     *
     * @param activity 活动
     */
    fun addActivity(activity: Activity) {
        activitys.add(activity)
    }

    /**
     * 从List中移除活动
     *
     * @param activity 活动
     */
    fun removeActivity(activity: Activity) {
        activitys.remove(activity)
    }

    /**
     * 将List中存储的活动全部销毁掉
     */
    fun removeAll() {
        for (activity in activitys) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }
}