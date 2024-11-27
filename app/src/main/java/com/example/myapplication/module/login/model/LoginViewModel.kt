package com.example.myapplication.module.login.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.bean.HttpResponse
import com.example.myapplication.module.login.repository.LoginRepository
import kotlinx.coroutines.launch

/**
 * Created by WangLiZhi on 2024/11/27.
 * Desc：登录注册ViewModel
 */
class LoginViewModel : ViewModel() {

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var rePassword = MutableLiveData<String>()

    /**
     * 登录
     */
    fun login(): LiveData<LoginResponse>{
        val resultResponse = MutableLiveData<LoginResponse>()
        viewModelScope.launch {
            resultResponse.postValue(LoginRepository.login(username.value.toString(), password.value.toString()))
        }
        return resultResponse
    }
    /**
     * 注册
     */
    fun register(): LiveData<HttpResponse<LoginResponse>>{
        val resultResponse = MutableLiveData<HttpResponse<LoginResponse>>()
        viewModelScope.launch {
            resultResponse.postValue(LoginRepository.register(username.value.toString(), password.value.toString(),rePassword.value.toString()))
        }
        return resultResponse
    }

}