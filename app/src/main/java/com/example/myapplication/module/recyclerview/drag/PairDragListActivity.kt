package com.example.myapplication.module.recyclerview.drag

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.base.utils.extend.infoLongToast
import com.example.myapplication.databinding.ActivityPairDragListBinding
import com.example.myapplication.module.recyclerview.drag.multiDrag.ItemSpanDragCallback
import com.example.myapplication.module.recyclerview.drag.multiDrag.ItemSpanDragHelper
import java.lang.StringBuilder

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：双列表拖动(可以使用一个多类型recyclerview，相当于还是在一个list里拖动)
 */

class PairDragListActivity : BaseActivity() {

    private lateinit var binding: ActivityPairDragListBinding
    private lateinit var mAdapter: MultiDragAdapter

    // 跨 RecyclerView 拖动效果
    private var dragHelper: ItemSpanDragHelper? = null

    private var mData: MutableList<DataEntity>? = null
    private var mItemData: MutableList<DataEntity>? = null

    val ITEM = 1

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper?.onTouch(ev!!) == true ||super.dispatchTouchEvent(ev)
    }
    override fun initData(savedInstanceState: Bundle?) {
        mData = arrayListOf()

        val topDataEntity = DataEntity("Top_Style-Title", "", ITEM)
        val bottomDataEntity = DataEntity("Bottom_Style-Title", "", ITEM)
        mItemData = arrayListOf()
        repeat(10) {
            val itemDataEntity = DataEntity("Child: $it", "", it)
            mItemData!!.add(itemDataEntity)
        }
        topDataEntity.itemList = mItemData
        bottomDataEntity.itemList = mItemData
        mData!!.apply {
            add(topDataEntity)
            add(bottomDataEntity)
        }

        mAdapter = MultiDragAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter
        mAdapter.setList(mData)

        // 拖动控制
        dragHelper = ItemSpanDragHelper(
            ItemSpanDragCallback(
                this,
                mData
            )
        ).attachToRecyclerView(binding.recyclerView)

        binding.tvClickViewSort.setOnClickListener {
            val build = StringBuilder()
            build.append("第一列")
            mData?.get(0)?.itemList?.forEach {
                build.append(it.type)
            }
            build.append("\n\n")
            build.append("第二列")
            mData?.get(1)?.itemList?.forEach {
                build.append(it.type)
            }
            binding.tvViewContent.text = build
        }
    }

    inner class ItemProvider(override val itemViewType: Int, override val layoutId: Int) : BaseItemProvider<DataEntity>() {
        override fun convert(helper: BaseViewHolder, item: DataEntity) {

            val itemAdapter = ItemAdapter()
            val recyclerView = helper.getView<RecyclerView>(R.id.recycler_view)
            recyclerView.layoutManager =  GridLayoutManager(context, 5)
            recyclerView.adapter = itemAdapter
            itemAdapter.onAttachedToRecyclerView(recyclerView)
            itemAdapter.setList(item.itemList)
            itemAdapter.setOnItemLongClickListener { adapter, view, position ->
                if (position >= 0 && position < item.itemList?.size!!) {
                    dragHelper!!.startDrag(
                        adapter.recyclerView.findContainingViewHolder(view)!!,
                        item.itemList!![position]
                    )
                }
                false
            }
        }

        inner class ItemAdapter : BaseQuickAdapter<DataEntity, BaseViewHolder>(R.layout.item_simple_str) {
            override fun convert(holder: BaseViewHolder, item: DataEntity) {
                holder.setText(R.id.txv_title, item.title)
            }

        }
    }
    inner class MultiDragAdapter : BaseProviderMultiAdapter<DataEntity>() {

        init {
            addItemProvider(ItemProvider(ITEM, R.layout.item_child_list))
        }
        override fun getItemType(data: List<DataEntity>, position: Int): Int {
            return data[position].type
        }

    }

    override fun bindView(): View? {
        binding = ActivityPairDragListBinding.inflate(layoutInflater)
        return binding.root
    }

}