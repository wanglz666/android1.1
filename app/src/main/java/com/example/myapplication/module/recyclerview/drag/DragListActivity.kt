package com.example.myapplication.module.recyclerview.drag

import android.os.Bundle
import android.view.View
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.databinding.ActivityDragListBinding

/**
 * Created by WangLiZhi on 2024/12/11.
 * Desc：
 *
 */

class DragListActivity : BaseActivity() {

    private lateinit var binding: ActivityDragListBinding

    override fun initData(savedInstanceState: Bundle?) {

        /*
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
MyAdapter adapter = new MyAdapter(R.layout.item_layout, itemList);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(adapter);

ItemTouchHelper.Callback callback = new ItemTouchCallback(adapter);
ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
itemTouchHelper.attachToRecyclerView(recyclerView);

         */
    }

    override fun bindView(): View? {
        binding = ActivityDragListBinding.inflate(layoutInflater)
        return binding.root
    }

}