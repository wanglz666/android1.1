package com.example.myapplication.module.dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import com.example.myapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Created by WangLiZhi on 2024/12/15.
 * Desc：底部弹窗
 */
class BaseBottomSheetDialog(context: Context): BottomSheetDialog(context, R.style.BottomSheetDialog), OnClickListener{

    private var dialogBuilder: DialogBuilder? = null
    constructor(context: Context, builder: DialogBuilder) : this(context) {
        this.dialogBuilder = builder
    }
    protected fun layoutId(): Int {
        return R.layout.dialog_bottom_sheet_layout
    }

    init {
        // 放init里初始化，不然两边撑不满
        setContentView(layoutId())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    protected fun initData() {


    }
    protected fun initView() {
        dialogBuilder?.let { build ->

        }

        val tvCancel = findViewById<TextView>(R.id.tv_cancel)
        tvCancel?.setOnClickListener(this)
    }

    fun showBefore() {
        this.show()
    }
    fun dismissBefore() {
        this.dismiss()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_cancel -> {
                dismissBefore()
            }
        }
    }
}