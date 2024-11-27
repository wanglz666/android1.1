package com.example.myapplication.base.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by WangLiZhi on 2024/11/27.
 * Desc：
 */
object RxSchedulersHelper {
    fun <T> defaultSchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> allIO(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
        }
    }
}