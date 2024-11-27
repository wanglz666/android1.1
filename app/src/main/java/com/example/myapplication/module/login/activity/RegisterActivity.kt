package com.example.myapplication.module.login.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.module.login.model.LoginViewModel
import com.example.myapplication.module.main.MainActivity

/**
 * 注册
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 获取binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
        // 确认输入密码
        binding.etRepassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.rePassword.value = s.toString()
            }

        })
        // 点击登录
        binding.btnRegister.setOnClickListener {

            viewModel.register().observe(this){
                it?.let {
                    if (TextUtils.isEmpty(it.errorMsg).not()) {
                        Toast.makeText(
                            this,
                            it.errorMsg,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtras(Bundle().apply {
                            putSerializable("userInfo", it.data)
                        })
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}