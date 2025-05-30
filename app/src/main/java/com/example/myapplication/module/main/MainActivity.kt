package com.example.myapplication.module.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.bean.DataEntity
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.base.constant.GlobalConst
import com.example.myapplication.base.constant.ItemType
import com.example.myapplication.base.utils.extend.successToast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.module.dialog.BaseBottomSheetDialog
import com.example.myapplication.module.expandText.ExpandTextActivity
import com.example.myapplication.module.login.model.LoginResponse
import com.example.myapplication.module.expandlistview.ExpandListActivity
import com.example.myapplication.module.recyclerview.drag.DragListActivity
import com.example.myapplication.module.recyclerview.drag.PairDragListActivity
import com.example.myapplication.module.flowLayout.TextFlowActivity
import com.example.myapplication.module.recyclerview.multiList.BRVAHMultiListActivity
import com.example.myapplication.module.recyclerview.multiList.BRVAHSimpleMultiListActivity
import com.example.myapplication.module.recyclerview.multiList.SimpleMultiListActivity
import com.example.myapplication.module.springBack.SpringBackActivity
import com.example.myapplication.module.swipeLayout.SwipeLayoutActivity
import com.example.myapplication.module.textTest.FollowTagActivity
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.tapadoo.alerter.Alerter

/**
 * 首页功能列表
 */
class MainActivity : BaseActivity(), OnRefreshLoadMoreListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: MainAdapter

    companion object{
        const val MAIN_DATA_KEY = "main_value"
    }

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
        mAdapter.setList(GlobalConst.initMainData())

//        binding.refreshLayout.autoRefresh()
//        binding.refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//取消内容不满一页时开启上拉加载功能
        binding.refreshLayout.setEnableRefresh(false)
        binding.refreshLayout.setEnableLoadMore(false)
        binding.refreshLayout.setOnRefreshListener(this)
        binding.refreshLayout.setOnLoadMoreListener(this)

        mAdapter.setOnItemClickListener { adapter, _, pos ->
            val item = adapter.getItem(pos) as DataEntity
            val intent = Intent()
            val bundle = Bundle()
            bundle.putString(MAIN_DATA_KEY, item.title)
            intent.putExtras(bundle)
            when (item.type) {
                ItemType.ITEM_EXPAND_LISTVIEW_TYPE -> {
                    intent.setClass(this, ExpandListActivity::class.java)
                }

                ItemType.ITEM_SIMPLE_MULTI_TYPE -> {
                    intent.setClass(this, SimpleMultiListActivity::class.java)
                }

                ItemType.ITEM_BRVAH_SIMPLE_MULTI_TYPE -> {
                    intent.setClass(this, BRVAHSimpleMultiListActivity::class.java)
                }

                ItemType.ITEM_BRVAH_MULTI_TYPE -> {
                    intent.setClass(this, BRVAHMultiListActivity::class.java)
                }

                ItemType.ITEM_BRVAH_DRAG_TYPE -> {
                    intent.setClass(this, DragListActivity::class.java)
                }

                ItemType.ITEM_BRVAH_PAIR_DRAG_TYPE -> {
                    intent.setClass(this, PairDragListActivity::class.java)
                }

                ItemType.ITEM_DIALOG_SLIDE_TYPE -> {
                    Alerter.create(this)
                        .setTitle("Alert Title")
                        .setText("Alert text...")
                        .setBackgroundColorRes(R.color.colorAccent) // or setBackgroundColorInt(Color.CYAN)
                        .show()
//                    val dialog = BaseBottomSheetDialog(mActivity)
//                    dialog.show()
//                    val dialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
//                    dialog.setContentView(R.layout.dialog_bottom_sheet_layout)
//                    dialog.show()
                }

                ItemType.ITEM_FLOW_TEXT_TYPE -> {
                    intent.setClass(this, TextFlowActivity::class.java)
                }

                ItemType.ITEM_SWIPE_LAYOUT_TYPE -> {
                    intent.setClass(this, SwipeLayoutActivity::class.java)
                }

                ItemType.ITEM_SPRING_BACK_TYPE -> {
                    intent.setClass(this, SpringBackActivity::class.java)
                }

                ItemType.ITEM_FOLLOW_TAG_TYPE -> {
                    intent.setClass(this, FollowTagActivity::class.java)
                }

                ItemType.ITEM_EXPAND_TEXT_TYPE -> {
                    intent.setClass(this, ExpandTextActivity::class.java)
                }
            }

            intent.component?.className?.let {
                startActivity(intent)
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

        this.getString(R.string.refresh_success).successToast()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore()

        this.getString(R.string.load_success).successToast()
    }

}

