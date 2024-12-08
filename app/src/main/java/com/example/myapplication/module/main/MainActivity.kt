package com.example.myapplication.module.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.myapplication.R
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.base.component.toast.BaseToast
import com.example.myapplication.base.component.toast.ToastParams
import com.example.myapplication.base.component.toast.style.CustomToastStyle
import com.example.myapplication.base.utils.callback.OnSingleClickListener
import com.example.myapplication.base.utils.constant.ConstData
import com.example.myapplication.base.utils.constant.ItemType
import com.example.myapplication.base.utils.extend.successToast
import com.example.myapplication.base.utils.extend.toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.module.login.model.LoginResponse
import com.example.myapplication.module.secondlevel.ExpandListActivity
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * 首页功能列表
 */
class MainActivity : BaseActivity(), OnRefreshLoadMoreListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: MainAdapter

    override fun bindView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun initData(savedInstanceState: Bundle?) {
        initParams()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mAdapter = MainAdapter(R.layout.item_simple_layout)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = mAdapter
        mAdapter.setList(ConstData.initMainData())

//        binding.refreshLayout.autoRefresh()
        binding.refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//取消内容不满一页时开启上拉加载功能
        binding.refreshLayout.setOnRefreshListener(this)
        binding.refreshLayout.setOnLoadMoreListener(this)

        mAdapter.setOnItemClickListener { adapter, _, pos ->
            val item = adapter.getItem(pos) as DataEntity
            when (item.type) {
                ItemType.ITEM_EXPAND_LISTVIEW_TYPE -> {
                    startActivity(Intent(this, ExpandListActivity::class.java))
                }
            }
        }
    }

    private fun initParams() {
        intent.extras?.let {
            if (it.containsKey("userInfo")) {
                val userInfo = it.getSerializable("userInfo") as LoginResponse
                userInfo.let { loginResponse ->
                    "登录成功：用户名：${loginResponse.username}".successToast()
                }
            }
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh()

    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore()
    }
}

