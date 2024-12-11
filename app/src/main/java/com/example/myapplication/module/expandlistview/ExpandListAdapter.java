package com.example.myapplication.module.expandlistview;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.bean.DataEntity;

import java.util.List;

public class ExpandListAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private final List<List<DataEntity>> mData;
    private final String[] titles;



    public ExpandListAdapter(Context context, List<List<DataEntity>> data, String[] titles) {
        this.mContext = context;
        this.mData = data;
        this.titles = titles;
    }


    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(groupPosition).size();
    }

    @Override
    public List<DataEntity> getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    @Override
    public DataEntity getChild(int groupPosition, int childPosition) {
        return mData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
//        return true; // 表示每个子项和组项都有固定的 ID
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if (convertView == null) {
            convertView = ((Activity) mContext).getLayoutInflater().inflate(R.layout.item_group_expand_list, parent, false);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.txvTitle.setText(titles[groupPosition]);

        //region 折叠箭头
        if (mData.get(groupPosition).size() > 1) {
            if (isExpanded) {
                holder.imgArrow.setImageResource(R.drawable.arrow_up); // 展开时的图标
            } else {
                holder.imgArrow.setImageResource(R.drawable.arrow_down); // 折叠时的图标
            }
        } else {
            holder.imgArrow.setVisibility(View.GONE);
        }
        //endregion
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null) {
            convertView = ((Activity) mContext).getLayoutInflater().inflate(R.layout.item_child_expand_list, parent, false);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        holder.txvName.setText(mData.get(groupPosition).get(childPosition).getTitle());
        holder.txvDesc.setText(mData.get(groupPosition).get(childPosition).getContent());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // 例如，某个条件下不允许选择某些子项
//        return childPosition != 2; // 例如，禁止选择第二个子项
        return true;// 可以响应点击
    }


    // ViewHolder classes
    static class GroupViewHolder {
        ImageView imgArrow;
        TextView txvTitle;

        GroupViewHolder(View view) {
            imgArrow = view.findViewById(R.id.img_arrow);
            txvTitle = view.findViewById(R.id.txv_title);
        }
    }

     static class ChildViewHolder {
        TextView txvName;
        TextView txvDesc;

        ChildViewHolder(View view) {
            txvName = view.findViewById(R.id.txv_name);
            txvDesc = view.findViewById(R.id.txv_desc);
        }
    }
}
