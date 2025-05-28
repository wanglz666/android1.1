package com.example.myapplication.base.utils.data

import java.io.*

object DeepCopyUtil {

    /**
     * 深拷贝方法，基于 Serializable
     * @param obj 要拷贝的对象，必须实现 Serializable 接口
     * @return 深拷贝后的对象，若拷贝失败则返回 null
     */
    @JvmStatic
    fun <T : Serializable> deepCopy(obj: T?): T? {
        if (obj == null) return null
        return try {
            // 序列化
            val byteArrayOutputStream = ByteArrayOutputStream()
            ObjectOutputStream(byteArrayOutputStream).use { it.writeObject(obj) }

            // 反序列化
            val byteArrayInputStream = ByteArrayInputStream(byteArrayOutputStream.toByteArray())
            ObjectInputStream(byteArrayInputStream).use { it.readObject() as T }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
