package com.example.myapplication.module.expandlistview;


import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.base.bean.DataEntity;
import com.example.myapplication.databinding.ActivityExpandListBinding;

import java.util.ArrayList;
import java.util.List;

public class ExpandListActivity extends AppCompatActivity {

    private ActivityExpandListBinding binding;

    private List<List<DataEntity>> mData;

    private final String[] titles = {"第一组", "第二组", "第三组", "第四组", "第五组", "第六组"};

    private ExpandListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpandListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();
        initListeners();
    }

    private void initView() {
        mAdapter = new ExpandListAdapter(this, mData, titles);
        binding.expandListView.setAdapter(mAdapter);
        // 默认展开第一个组
//        binding.expandListView.expandGroup(0);
        // 如果需要展开多个组，可以使用循环
         for (int i = 0; i < mAdapter.getGroupCount(); i++) {
             binding.expandListView.expandGroup(i);
         }
        // 去掉分割线
        binding.expandListView.setDivider(null);
        binding.expandListView.setDividerHeight(0);
    }

    private void initData() {

        mData = new ArrayList<>();
        for (String title : titles) {
            List<DataEntity> childData = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                childData.add(new DataEntity("子项name " + title +" 第 "+i+"个", "子项desc " + title +" 第 "+i+"个", i));
            }
            mData.add(childData);
        }
    }

    private void initListeners() {
        // 一级点击事件
        binding.expandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                // 处理组项点击事件
                Toast.makeText(ExpandListActivity.this, "Clicked group: " + titles[groupPosition], Toast.LENGTH_SHORT).show();
                return false; // 返回 false 表示继续展开/折叠
//                return true;// 返回 true 阻止折叠
            }
        });


        // 二级点击事件
        binding.expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                // 处理子项点击事件
                DataEntity childData = mData.get(groupPosition).get(childPosition);
                Toast.makeText(ExpandListActivity.this, "Clicked child: " + childData.getTitle(), Toast.LENGTH_SHORT).show();
                // 更新子项数据
                updateChildData(groupPosition, childPosition);
                return true; // 返回 true 表示事件被处理
            }
        });

    }

    private void updateChildData(int groupPosition, int childPosition) {
        // 更新子项数据的示例
        DataEntity childData = mData.get(groupPosition).get(childPosition);
        childData.setTitle("Updated Name");

        // 通知适配器数据已更改
        mAdapter.notifyDataSetChanged();
    }

}
