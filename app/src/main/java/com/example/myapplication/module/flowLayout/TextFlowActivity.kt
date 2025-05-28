package com.example.myapplication.module.flowLayout

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.constant.GlobalConst
import com.example.myapplication.base.utils.extend.infoToast
import com.scwang.smart.refresh.layout.util.SmartUtil.dp2px

/**
 * https://github.com/xiangcman/LayoutManager-FlowLayout
 */
class TextFlowActivity : AppCompatActivity() {


    private var mData: List<ShowItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text_flow)

        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        val flowLayoutManager = FlowLayoutManager()
        recyclerView.addItemDecoration(SpaceItemDecoration(dp2px(10f)))
        recyclerView.layoutManager = flowLayoutManager
        mData = GlobalConst.getItems()
        recyclerView.adapter = FlowAdapter(this, mData)
    }

    class FlowAdapter(private val context: Context, private val list: List<ShowItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return MyHolder(View.inflate(context, R.layout.item_simple_str, null))
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val textView = (holder as MyHolder).text
            textView.setPadding(dp2px(10F),dp2px(5F),dp2px(10F),dp2px(5F))
            textView.background = list[position].color
            textView.text = list[position].des
            holder.itemView.setOnClickListener {
                list[position].des.infoToast()
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text: TextView

            init {
                text = itemView.findViewById<View>(R.id.txv_title) as TextView
            }
        }
    }
}