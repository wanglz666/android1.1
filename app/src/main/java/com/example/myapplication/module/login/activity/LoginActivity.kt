package com.example.myapplication.module.login.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.module.login.model.LoginViewModel
import com.example.myapplication.module.main.MainActivity

/**
 * 登录
 */
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

        if (TextUtils.isEmpty(binding.etUsername.text.toString()).not()) {
            viewModel.username.value = binding.etUsername.text.toString()
        }
        if (TextUtils.isEmpty(binding.etPassword.text.toString()).not()) {
            viewModel.password.value = binding.etPassword.text.toString()
        }

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
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtras(Bundle().apply {
                        putSerializable("userInfo", it)
                    })
                    startActivity(intent)
                    finish()
                }
            }
        }
        // 点击注册
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}