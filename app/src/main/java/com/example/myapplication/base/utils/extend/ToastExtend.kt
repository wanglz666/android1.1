package com.example.myapplication.base.utils.extend

import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.base.component.toast.BaseToast
import com.example.myapplication.base.component.toast.ToastParams
import com.example.myapplication.base.component.toast.style.CustomToastStyle

fun String.toast(content: String) {
    BaseToast.show(content)
}

fun String.infoToast() {
    val params =  ToastParams()
    params.text = this
    params.style = CustomToastStyle(R.layout.toast_info)
    BaseToast.show(params)
}
fun String.infoLongToast() {
    val params =  ToastParams()
    params.text = this
    params.duration = Toast.LENGTH_LONG
    params.style = CustomToastStyle(R.layout.toast_info)
    BaseToast.show(params)
}
fun String.successToast() {
    val params =  ToastParams()
    params.text = this
    params.style = CustomToastStyle(R.layout.toast_success)
    BaseToast.show(params)
}
fun String.errorToast() {
    val params =  ToastParams()
    params.text = this
    params.style = CustomToastStyle(R.layout.toast_error)
    BaseToast.show(params)
}