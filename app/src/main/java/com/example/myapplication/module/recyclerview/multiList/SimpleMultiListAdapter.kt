package com.example.myapplication.module.recyclerview.multiList

import android.content.Context
import android.view.LayoutInflater.*
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.base.utils.extend.infoToast
import com.example.myapplication.databinding.ItemStyle1Binding
import com.example.myapplication.databinding.ItemStyle2Binding
import com.example.myapplication.databinding.ItemStyle3Binding
import com.example.myapplication.module.recyclerview.common.ItemTypeConst

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：
 */
class SimpleMultiListAdapter(val context: Context, val mData: MutableList<DataEntity>) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return mData[position].type
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        when (viewType) {
            ItemTypeConst.STYLE1 -> {
                val style1Binding = ItemStyle1Binding.inflate(from(parent.context), parent, false)
                return Style1ViewHolder(style1Binding)
            }

            ItemTypeConst.STYLE2 -> {
                val style1Binding = ItemStyle2Binding.inflate(from(parent.context), parent, false)
                return Style2ViewHolder(style1Binding)
            }

            ItemTypeConst.STYLE3 -> {
                val style1Binding = ItemStyle3Binding.inflate(from(parent.context), parent, false)
                return Style3ViewHolder(style1Binding)
            }

            else -> {
                val style1Binding = ItemStyle1Binding.inflate(from(parent.context), parent, false)
                return Style1ViewHolder(style1Binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        when (holder) {
            is Style1ViewHolder -> {
                val style1Binding = ItemStyle1Binding.bind(holder.itemView)
                style1Binding.tvStyle1.setOnClickListener {
                    style1Binding.tvStyle1.text.toString().infoToast()
                }
            }
            is Style2ViewHolder -> {
                val style2Binding = ItemStyle2Binding.bind(holder.itemView)
                style2Binding.tvStyle2.setOnClickListener {
                    style2Binding.tvStyle2.text.toString().infoToast()
                }
            }
            is Style3ViewHolder -> {
                val style3Binding = ItemStyle3Binding.bind(holder.itemView)
                style3Binding.tvStyle3.setOnClickListener {
                    style3Binding.tvStyle3.text.toString().infoToast()
                }
            }
        }
    }
    class Style1ViewHolder(view: ViewBinding): BaseViewHolder(view.root)
    class Style2ViewHolder(view: ViewBinding): BaseViewHolder(view.root)
    class Style3ViewHolder(view: ViewBinding): BaseViewHolder(view.root)
}