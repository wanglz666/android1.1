package com.example.myapplication.module.login.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.module.login.repository.LoginRepository
import kotlinx.coroutines.launch

/**
 * Created by WangLiZhi on 2024/11/27.
 * Desc：
 */
class LoginViewModel : ViewModel() {

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()


    fun login(): LiveData<LoginResponse>{
        val resultResponse = MutableLiveData<LoginResponse>()
        viewModelScope.launch {
            resultResponse.postValue(LoginRepository.login(username.value.toString(), password.value.toString()))
        }
        return resultResponse
    }

}