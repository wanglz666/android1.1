package com.example.myapplication.base.utils.view


/**
 * 防止快速点击
 */
class ClickDebounce {

    // 内部类，用于管理单个点击事件的防抖逻辑
    private class OneClickUtil(private val methodName: String) {
        private var lastClickTime: Long = 0
        private var minClickDelayTime: Int = DEFAULT_MIN_CLICK_DELAY_TIME

        fun setMinClickDelayTime(minClickDelayTime: Int) {
            this.minClickDelayTime = minClickDelayTime
        }

        fun getMethodName(): String {
            return methodName
        }

        @Synchronized
        fun check(): Boolean {
            val currentTime = System.currentTimeMillis()
            val delayTime = currentTime - lastClickTime
            return if (delayTime > minClickDelayTime) {
                lastClickTime = currentTime
                false // 有效点击
            } else {
                true // 无效点击
            }
        }

        companion object {
            const val DEFAULT_MIN_CLICK_DELAY_TIME = 500 // 默认点击限制时间ms
        }
    }

    private val utils = mutableListOf<OneClickUtil>()
    private var minClickDelayTime = 500 // 全局默认的点击限制时间

    fun setMinClickDelayTime(minClickDelayTime: Int) {
        this.minClickDelayTime = minClickDelayTime
    }

    /**
     * 防止按钮重复点击
     * @param o Any?
     * @return Boolean
     */
    @Synchronized
    fun check(o: Any?): Boolean {
        val flag = o?.toString() ?: Thread.currentThread().stackTrace[2].methodName

        for (util in utils) {
            if (util.getMethodName() == flag) {
                return util.check()
            }
        }

        // 如果未找到对应的标识，则创建新的 OneClickUtil
        val clickUtil = OneClickUtil(flag)
        if (minClickDelayTime > 0) {
            clickUtil.setMinClickDelayTime(minClickDelayTime)
        }
        utils.add(clickUtil)
        return clickUtil.check()
    }

    /**
     * 防止方法重复调用
     * @return Boolean
     */
    fun check(): Boolean {
        return check(null)
    }
}
