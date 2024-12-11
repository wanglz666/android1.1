package com.example.myapplication.module.recyclerview.multiList

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.base.utils.extend.infoToast
import com.example.myapplication.databinding.ActivitySimpleMultiListBinding
import kotlin.random.Random

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：使用BRVAH创建复杂业务多类型列表
 */
class BRVAHMultiListActivity : BaseActivity() {

    private lateinit var binding: ActivitySimpleMultiListBinding
    private lateinit var mAdapter: BRVAHMultiListAdapter
    private var mData: MutableList<DataEntity>? = null
    private var mItemData: MutableList<DataEntity>? = null

    override fun initData(savedInstanceState: Bundle?) {
        if (mData == null) {
            mData = arrayListOf()

            repeat(10) {
                val int = Random.nextInt(1, 4)
                val dataEntity = DataEntity("Style-Title", "Style-Content", int)
                mItemData = arrayListOf()
                repeat(3) {
                    val itemDataEntity = DataEntity("Item-Style-Title", "Item-Style-Content", int)
                    mItemData!!.add(itemDataEntity)
                }
                dataEntity.itemList = mItemData
                mData!!.add(dataEntity)
            }
        }
        mAdapter = BRVAHMultiListAdapter()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = mAdapter
        mAdapter.setList(mData)
        mAdapter.setOnItemClickListener { adapter, _, position ->
            val dataEntity = adapter.getItem(position) as DataEntity
            (dataEntity.title + "+类型 " + dataEntity.type).infoToast()
        }
    }

    override fun bindView(): View? {
        binding = ActivitySimpleMultiListBinding.inflate(layoutInflater)
        return binding.root
    }
}