package com.example.myapplication.module.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bundle = intent.extras

        Log.d(TAG, "${this.javaClass.simpleName}  欢迎你： ${bundle?.getString("username")}")
    }
}

