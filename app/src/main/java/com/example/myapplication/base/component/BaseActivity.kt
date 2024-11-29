package com.example.myapplication.base.component

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.base.utils.constant.ConstData
import com.example.myapplication.base.utils.extend.errorToast

abstract class BaseActivity : AppCompatActivity() , IBaseActivity{


    protected lateinit var mActivity: BaseActivity

    protected lateinit var TAG: String

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
        Log.e(ConstData.CURRENT_ACTIVITY, ConstData.CURRENT_ACTIVITY + " = " + TAG)


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