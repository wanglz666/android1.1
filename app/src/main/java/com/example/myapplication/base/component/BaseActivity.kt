package com.example.myapplication.base.component

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.base.utils.view.ClickDebounce
import com.example.myapplication.base.constant.GlobalConst
import com.example.myapplication.base.utils.extend.errorToast

abstract class BaseActivity : AppCompatActivity() , IBaseActivity{


    protected lateinit var mActivity: BaseActivity

    /**
     * 当前activity名称
     */
    protected lateinit var TAG: String

    /**
     * 防止快速点击
     */
    protected val clickDebounce = ClickDebounce()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (bindView() != null) {
            setContentView(bindView())
        }else if (bindLayout() != null) {
            setContentView(bindLayout()!!)
        } else {
            "目标页面未绑定View".errorToast()
            return
        }

        initData(savedInstanceState)

        mActivity = this

        // activity添加管理
        ActivityManager.addActivity(mActivity)

        TAG = mActivity.javaClass.simpleName
        Log.e(GlobalConst.CURRENT_ACTIVITY, GlobalConst.CURRENT_ACTIVITY + " = " + TAG)


//        initToolBar();


    }


    abstract fun initData(savedInstanceState: Bundle?)


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(mActivity)
    }

    //
    override fun bindLayout(): Int? {
        return null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }


}