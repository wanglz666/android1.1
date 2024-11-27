package com.example.myapplication.module.login.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.module.login.model.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 获取binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取viewModel
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]


        // 输入用户名
        binding.etUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.username.value = s.toString()
            }

        })
        // 输入密码
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.password.value = s.toString()
            }

        })
        // 点击登录
        binding.btnLogin.setOnClickListener {

            viewModel.login().observe(this){
                it?.let {
                    Toast.makeText(this, "登录成功：用户名：${it.username}, admin：${it.admin}}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}