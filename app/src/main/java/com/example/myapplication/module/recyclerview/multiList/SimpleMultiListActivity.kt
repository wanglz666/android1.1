package com.example.myapplication.module.recyclerview.multiList

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.databinding.ActivitySimpleMultiListBinding
import com.example.myapplication.module.recyclerview.multiList.adapter.SimpleMultiListAdapter
import kotlin.random.Random

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：不使用BRVAH创建简单的多类型列表
 */
class SimpleMultiListActivity : BaseActivity(){

    private lateinit var binding: ActivitySimpleMultiListBinding
    private lateinit var mAdapter: SimpleMultiListAdapter
    private var mData: MutableList<DataEntity>? = null
    override fun initData(savedInstanceState: Bundle?) {

        if (mData == null) {
            mData = arrayListOf()
            repeat(10) {
                val int = Random.nextInt(1, 4)
                val dataEntity = DataEntity("Style-Title", "Style-Content", int)
                mData!!.add(dataEntity)
            }
        }

        mAdapter = SimpleMultiListAdapter(this, mData!!)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = mAdapter
    }

    override fun bindView(): View? {
        binding = ActivitySimpleMultiListBinding.inflate(layoutInflater)
        return binding.root
    }
}