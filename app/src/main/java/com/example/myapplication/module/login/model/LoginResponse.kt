package com.example.myapplication.module.login.model

import java.io.Serializable

/**
 * 登录/注册数据类
 */
data class LoginResponse (
    val admin: Boolean,
    val chapterTops: List<*>,
    val coinCount: Long,
    val collectIds: List<*>,
    val email: String?,
    val icon: String?,
    val id: Long,
    val nickname: String?,
    val password: String?,
    val publicName: String?,
    val token: String?,
    val type: Int,
    val username: String?,

):Serializable
