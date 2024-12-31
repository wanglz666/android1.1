package com.example.myapplication.module.recyclerview.drag.multiDrag;


import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.base.bean.DataEntity;
import com.example.myapplication.base.utils.DeepCopyUtil;
import com.example.myapplication.base.utils.constant.GlobalConst;

import java.util.Collections;
import java.util.List;

/**
 * 跨 {@link RecyclerView} 拖动回调控制
 */
public class ItemSpanDragCallback implements ItemSpanDragHelper.OnItemDragCallBack {

    private final Context context;
    // 数据集
    private final List<DataEntity> multiItemEntities;

    public ItemSpanDragCallback(Context context, List<DataEntity> multiItemEntities) {
        this.context = context;
        this.multiItemEntities = multiItemEntities;
    }

    @Override
    public void onDragStart(@NonNull RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.setBackgroundResource(R.drawable.bg_full_ffffff_frame_999999_width1_dash3);
    }

    @Override
    public void onMoving(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder fromViewHolder, int recyclerViewFromPosition, int fromPosition, @NonNull RecyclerView.ViewHolder toViewHolder, int toPosition) {
        BaseQuickAdapter<DataEntity, BaseViewHolder> adapter = (BaseQuickAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(adapter.getData(), i, i + 1);
                }
            } else if (fromPosition > toPosition) {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(adapter.getData(), i, i - 1);
                }
            }

            adapter.notifyItemMoved(fromPosition, toPosition);

            // 更新数据集
            multiItemEntities.get(recyclerViewFromPosition).setItemList(adapter.getData());
        }
    }

    @Override
    public boolean onSpanDragIntercepter(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder fromViewHolder) {
        BaseQuickAdapter<DataEntity, BaseViewHolder> adapter = (BaseQuickAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            DataEntity item = adapter.getData().get(fromViewHolder.getLayoutPosition());
            Log.d(GlobalConst.PUBLIC_TAG, "onSpanDragIntercepter: "+item.getTitle());
        }

        return false;
    }


    @Override
    public void onMoved(@NonNull RecyclerView fromRecyclerView, @NonNull RecyclerView.ViewHolder fromViewHolder, @NonNull RecyclerView toRecyclerView, @NonNull RecyclerView.ViewHolder toViewHolder, int recyclerViewFromPosition, int recyclerViewToPosition, int itemFromPosition, int itemToPosition) {
        BaseQuickAdapter<DataEntity, BaseViewHolder> fromAdapter = (BaseQuickAdapter) fromRecyclerView.getAdapter();
        BaseQuickAdapter<DataEntity, BaseViewHolder> toAdapter = (BaseQuickAdapter) toRecyclerView.getAdapter();

        if (itemToPosition != ItemSpanDragHelper.NONE) {
            if (fromAdapter != null && toAdapter != null) {
                DataEntity item = fromAdapter.getData().get(itemFromPosition);
                if (item != null) {
                    toAdapter.addData(itemToPosition, DeepCopyUtil.deepCopy(item));
                    fromAdapter.remove(item);
                }
            }
        } else {// 没有指定加载位置，直接添加到列表的最后
            if (fromAdapter != null && toAdapter != null) {
                DataEntity item = fromAdapter.getData().get(itemFromPosition);
                if (item != null) {
                    toAdapter.addData(DeepCopyUtil.deepCopy(item));
                    fromAdapter.remove(item);
                }
            }
        }

        // 更新数据集
        if (fromAdapter != null) {
            multiItemEntities.get(recyclerViewFromPosition).setItemList(fromAdapter.getData());
        }
        if (toAdapter != null) {
            multiItemEntities.get(recyclerViewToPosition).setItemList(toAdapter.getData());
        }
    }

    @Override
    public void onMoving(@NonNull RecyclerView.ViewHolder fromViewHolder, float x, float y, boolean canMove) {

    }

    @Override
    public void clearView(@NonNull RecyclerView.ViewHolder viewHolder, DataEntity itemData) {
        Log.d(GlobalConst.PUBLIC_TAG, "clearView: "+itemData.getTitle());
    }

    @Override
    public void onDragEnd(@NonNull RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.setBackgroundResource(android.R.color.transparent);
    }

    /**
     * 获取数据集
     */
    public List<DataEntity> getData() {
        return multiItemEntities;
    }
}