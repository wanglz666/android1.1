package com.example.myapplication.module.recyclerview.multiList.provider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.base.utils.extend.infoToast
import com.example.myapplication.databinding.ItemStyle1Binding
import com.example.myapplication.module.recyclerview.common.ItemTypeConst


/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：
 */
class BRVAHMultiListProvider1 : BaseItemProvider<DataEntity>() {
    override val itemViewType: Int
        get() = ItemTypeConst.STYLE1
    override val layoutId: Int
        get() = R.layout.item_style1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemStyle1Binding.inflate(LayoutInflater.from(context))
        return ProviderViewHolder(binding)
    }
    override fun convert(helper: BaseViewHolder, item: DataEntity) {
        val binding = ItemStyle1Binding.bind(helper.itemView)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = ProviderAdapter(item.itemList!!)

    }

    override fun onClick(helper: BaseViewHolder, view: View, data: DataEntity, position: Int) {
        data.title.infoToast()

    }

    class ProviderViewHolder(binding: ViewBinding): BaseViewHolder(binding.root)

    class ProviderAdapter(mData: MutableList<DataEntity>) : BaseQuickAdapter<DataEntity, BaseViewHolder>(R.layout.item_simple_image, mData) {
        override fun convert(holder: BaseViewHolder, item: DataEntity) {

            holder.setText(R.id.tv_title, item.title)
        }

    }

}