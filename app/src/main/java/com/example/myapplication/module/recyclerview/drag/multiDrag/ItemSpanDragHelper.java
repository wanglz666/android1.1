package com.example.myapplication.module.recyclerview.drag.multiDrag;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.base.bean.DataEntity;

/**
 * 跨 {@link RecyclerView} 拖动效果
 */
public class ItemSpanDragHelper {

    public static final int NONE = -1;

    // 被绑定的 RecyclerView
    private RecyclerView recyclerView;
    // 计录上次的拖动坐标
    private float lastTouchX, lastTouchY;
    // 是否正在拖动
    private boolean isDrag;
    // 拖动浮层视图工具类
    private DragFloatViewHelper floatViewHelper;
    // 拖动浮层所在的 ViewHolder
    private RecyclerView.ViewHolder viewHolder;
    private DataEntity itemData;
    // 初始被拖动 Item 的下标
    private int firstPosition;
    // 初次拖动的 Item 所在父 RecyclerView 中的位置
    private int firstRecyclerPosition;
    // 初次拖动的 Item 所依附的 RecyclerView
    private RecyclerView firstRecycler;
    // 记录移到 Position 的上次下标
    private int lastToPosition;
    // 是否是跨 RecyclerView 拖动
    private boolean spanDrag = false;
    // 拖动的各种回调
    private OnItemDragCallBack onItemDragCallBack;

    /**
     * 横向滚动的RecyclerView
     */
    public ItemSpanDragHelper(OnItemDragCallBack onItemDragCallBack) {
        this.onItemDragCallBack = onItemDragCallBack;

        floatViewHelper = new DragFloatViewHelper();
    }

    /**
     * 开始拖拽
     */
    public void startDrag(@NonNull RecyclerView.ViewHolder viewHolder, DataEntity itemData) {
        this.viewHolder = viewHolder;
        this.itemData = itemData;

        // 初始被拖动 Item 的下标
        firstPosition = viewHolder.getLayoutPosition();
        // 开始拖动标志
        isDrag = true;

        // 创建浮动视图
        floatViewHelper.createView(viewHolder.itemView, lastTouchX, lastTouchY);

        // 外部接口回调
        if (onItemDragCallBack != null) {
            onItemDragCallBack.onDragStart(viewHolder);
        }

        int[] itemViewLocation = new int[2];
        viewHolder.itemView.getLocationOnScreen(itemViewLocation);

        lastTouchX = itemViewLocation[0] + viewHolder.itemView.getWidth() / 2f;
        lastTouchY = itemViewLocation[1] + viewHolder.itemView.getHeight() / 2f;

        // 找到当前触摸点下的 recyclerView
        float[] location = getInsideLocation(recyclerView, lastTouchX, lastTouchY);
        View view = recyclerView.findChildViewUnder(location[0], location[1]);
        firstRecyclerPosition = getPositionByChildView(view);
        firstRecycler = findRecyclerView(view);
    }

    /**
     * 必须完整处理 Touch 事件，可以在 Activity 或者外层的 ViewGroup 或可以拦截 Touch 事件的地方回调都可以
     *
     * @return true 表示消耗掉事件
     */
    public boolean onTouch(@NonNull MotionEvent event) {
        lastTouchX = event.getRawX();
        lastTouchY = event.getRawY();

        if (!isDrag) {
            return false;
        }

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            // 拖动时，计算是否需要移动 Item
            boolean canMove = moveIfNecessary(lastTouchX, lastTouchY);

            if (onItemDragCallBack != null) {
                onItemDragCallBack.onMoving(viewHolder, lastTouchX, lastTouchY, canMove);
            }

            floatViewHelper.updateView((int) lastTouchX, (int) lastTouchY);
        } else if (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL || event.getActionMasked() == MotionEvent.ACTION_OUTSIDE) {
            if (spanDrag) {
                // 抬起时，计算是否需要移动 Item
                boolean canMove = upIfNecessary(lastTouchX, lastTouchY);
            }

            stopDrag();
        }

        return true;
    }

    /**
     * 当用户抬起的时候，计算是否需要移动 Item
     *
     * @param touchX float event.getRawX()
     * @param touchY float event.getRawY()
     */
    private boolean upIfNecessary(float touchX, float touchY) {
        boolean result = true;

        // 找到当前触摸点下的内部 recyclerView
        float[] location = getInsideLocation(recyclerView, touchX, touchY);
        View view = recyclerView.findChildViewUnder(location[0], location[1]);
        int recyclerPosition = getPositionByChildView(view);
        RecyclerView insideRecyclerView = findRecyclerView(view);

        // 没有找到所属位置或者目标 RecyclerView，则不继续
        if (recyclerPosition == NONE || insideRecyclerView == null) {
            return false;
        }

        // 找到当前触摸点下的 itemView
        location = getInsideLocation(insideRecyclerView, touchX, touchY);
        float itemX = location[0];
        float itemY = location[1];
        View itemView = insideRecyclerView.findChildViewUnder(itemX, itemY);
        int toPosition = getTargetItemPosition(itemView, firstRecyclerPosition, recyclerPosition);

        if (!isPositionEqual(firstRecyclerPosition, recyclerPosition)) {// 不是同一个 Position，则需要处理跨 RecyclerView 拖动处理
            if (onItemDragCallBack != null) {
                onItemDragCallBack.onMoved(firstRecycler, viewHolder, insideRecyclerView, insideRecyclerView.findViewHolderForAdapterPosition(toPosition), firstRecyclerPosition, recyclerPosition, firstPosition, toPosition);
            }
        }

        return result;
    }

    /**
     * 当用户拖动的时候，计算是否需要移动 Item
     *
     * @param touchX float event.getRawX()
     * @param touchY float event.getRawY()
     */
    private boolean moveIfNecessary(float touchX, float touchY) {
        boolean result = true;

        // 找到当前触摸点下的内部 recyclerView
        float[] location = getInsideLocation(recyclerView, touchX, touchY);
        View view = recyclerView.findChildViewUnder(location[0], location[1]);
        int recyclerPosition = getPositionByChildView(view);
        RecyclerView insideRecyclerView = findRecyclerView(view);

        // 没有找到所属位置或者目标 RecyclerView，则不继续
        if (recyclerPosition == NONE || insideRecyclerView == null) {
            return false;
        }

        // 找到当前触摸点下的 itemView
        location = getInsideLocation(insideRecyclerView, touchX, touchY);
        float itemX = location[0];
        float itemY = location[1];
        View itemView = insideRecyclerView.findChildViewUnder(itemX, itemY);
        int toPosition = getTargetItemPosition(itemView, firstRecyclerPosition, recyclerPosition);

        if (isPositionEqual(firstRecyclerPosition, recyclerPosition)) {// 当前触摸点和初始触摸点所在父 Position 是否是同一个
            boolean itemNeedChange = isItemNeedChange(itemView, firstPosition, toPosition, itemX);
            if (itemNeedChange) {
                if (onItemDragCallBack != null) {
                    onItemDragCallBack.onMoving(insideRecyclerView, viewHolder, firstRecyclerPosition, firstPosition, insideRecyclerView.findViewHolderForAdapterPosition(toPosition), toPosition);
                }

                firstPosition = toPosition;
                lastToPosition = toPosition;
            }
        } else {// 不是同一个 Position，则需要处理跨 RecyclerView 拖动处理
            this.spanDrag = true;

            boolean isSpanDragIntercepter;
            if (firstRecycler != null) {
                isSpanDragIntercepter = onItemDragCallBack.onSpanDragIntercepter(firstRecycler, viewHolder);
            } else {
                isSpanDragIntercepter = false;
            }

            if (isSpanDragIntercepter) {
                stopDrag();
            }
        }

        return result;
    }

    /**
     * 两个 Item 是否需要横向移动
     */
    private boolean isItemNeedChange(View itemView, int firstPosition, int toPosition, float itemX) {
        if (itemView == null || firstPosition == NONE || toPosition == NONE) {
            return false;
        }

        if (firstPosition == toPosition) {
            return false;
        }

        if (lastToPosition != toPosition) {
            // 当 Touch 位置在目标视图宽度的边界值时对两个 Item 进行 Move 的处理，默认 0.5 即超过一半高度时即可允许两个 Item 移动
            int moveLimit = (int) (itemView.getLeft() + itemView.getWidth() * 0.5);
            if (firstPosition > toPosition) {
                return itemX < moveLimit;
            } else {
                return itemX > moveLimit;
            }
        } else {
            return false;
        }
    }

    /**
     * 获取需要 Move 的目标 Position，即 toPosition
     *
     * @return NONE 找不到
     */
    private int getTargetItemPosition(View itemTargetView, int lastRecyclerPosition, int curRecyclerPosition) {
        int itemPosition = getPositionByChildView(itemTargetView);

        if (itemPosition == NONE) {
            return itemPosition;
        }

        if ((itemPosition != firstPosition || lastRecyclerPosition != curRecyclerPosition)) {
            return itemPosition;
        }

        return NONE;
    }

    /**
     * 当前触摸点和初始触摸点所在的 Position 是同一个
     */
    private boolean isPositionEqual(int firstRecyclerPosition, int recyclerPosition) {
        return firstRecyclerPosition == recyclerPosition;
    }

    /**
     * 从 view 中获取需要操作的 RecyclerView
     *
     * @param view View
     */
    private RecyclerView findRecyclerView(View view) {
        if (view == null) {
            return null;
        }

        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;

            RecyclerView recyclerView;
            for (int i = 0, count = viewGroup.getChildCount(); i < count; i++) {
                recyclerView = findRecyclerView(viewGroup.getChildAt(i));
                if (recyclerView != null) {
                    return recyclerView;
                }
            }
        }

        return null;
    }

    /**
     * 查找当前 view 在 RecyclerView 中的位置没有返回 NONE
     */
    private int getPositionByChildView(View itemView) {
        if (itemView == null) {
            return NONE;
        }

        try {
            return ((RecyclerView.LayoutParams) itemView.getLayoutParams()).getBindingAdapterPosition();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return NONE;
    }

    /**
     * 获取当前点击的位置在 RecyclerView 内部的坐标 (Y 坐标范围 0+padding 到 height-padding)
     */
    private float[] getInsideLocation(RecyclerView recyclerView, float touchRawX, float touchRawY) {
        float[] result = new float[2];
        int[] location = new int[2];
        recyclerView.getLocationOnScreen(location);
        result[0] = touchRawX - location[0];
        result[1] = touchRawY - location[1];

        int minY = recyclerView.getPaddingTop();
        int maxY = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        result[1] = Math.min(Math.max(result[1], minY), maxY);

        return result;
    }

    /**
     * 拖动结束
     */
    private void stopDrag() {
        if (onItemDragCallBack != null) {
            onItemDragCallBack.clearView(viewHolder,itemData);
        }

        if (isDrag) {
            if (onItemDragCallBack != null) {
                onItemDragCallBack.onDragEnd(viewHolder);
            }

            firstRecyclerPosition = NONE;
            lastToPosition = NONE;

            floatViewHelper.removeView();
        }

        this.isDrag = false;
        this.spanDrag = false;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 绑定最外层的 {@link RecyclerView}
     */
    public ItemSpanDragHelper attachToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

        return this;
    }

    public interface OnItemDragCallBack {

        /**
         * 开始拖动
         */
        void onDragStart(@NonNull RecyclerView.ViewHolder viewHolder);

        /**
         * 正在拖动
         */
        void onMoving(@NonNull RecyclerView.ViewHolder fromViewHolder, float x, float y, boolean canMove);

        /**
         * 单 {@link RecyclerView} 拖动控制回调
         */
        void onMoving(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder fromViewHolder, int recyclerViewFromPosition, int fromPosition, @NonNull RecyclerView.ViewHolder toViewHolder, int toPosition);

        /**
         * 跨 {@link RecyclerView} 拖动前的拦截
         */
        boolean onSpanDragIntercepter(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder fromViewHolder);

        /**
         * 跨 {@link RecyclerView} 拖动控制回调
         */
        void onMoved(@NonNull RecyclerView fromRecyclerView, @NonNull RecyclerView.ViewHolder fromViewHolder, @NonNull RecyclerView toRecyclerView, @NonNull RecyclerView.ViewHolder toViewHolder, int recyclerViewFromPosition, int recyclerViewToPosition, int itemFromPosition, int itemToPosition);

        /**
         * 拖动结束，还原视图状态
         */
        void clearView(@NonNull RecyclerView.ViewHolder viewHolder, DataEntity itemData);

        /**
         * 拖动结束
         */
        void onDragEnd(@NonNull RecyclerView.ViewHolder viewHolder);
    }
}