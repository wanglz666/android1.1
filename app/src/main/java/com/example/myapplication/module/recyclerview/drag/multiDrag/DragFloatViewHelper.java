package com.example.myapplication.module.recyclerview.drag.multiDrag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * 拖动浮层视图工具类
 */
public class DragFloatViewHelper {

    // 当前被拖动的 View
    private View currentTouchedView;
    // 窗口管理
    private WindowManager manager;
    // 浮层参数
    private WindowManager.LayoutParams params;
    // 坐标偏移量
    private int offsetX, offsetY;

    /**
     * 创建浮层视图
     *
     * @param coverView 被覆盖的视图，用于计算浮层位置
     */
    public void createView(View coverView, float touchRawX, float touchRawY) {
        this.currentTouchedView = createFloatView(coverView);

        manager = (WindowManager) currentTouchedView.getContext().getSystemService(Context.WINDOW_SERVICE);

        params = new WindowManager.LayoutParams();
        params.token = currentTouchedView.getWindowToken();
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        params.format = PixelFormat.TRANSLUCENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        params.gravity = Gravity.LEFT | Gravity.TOP;

        params.width = (int) (coverView.getWidth());
        params.height = (int) (coverView.getHeight());

        int[] location = new int[2];
        coverView.getLocationOnScreen(location);

        params.x = location[0];
        params.y = location[1];
        manager.addView(currentTouchedView, params);

        offsetX = (int) (touchRawX - location[0]);
        offsetY = (int) (touchRawY - location[1]);
    }

    /**
     * 创建一个浮层视图
     */
    private View createFloatView(View coverView) {
        ImageView floatView = new ImageView(coverView.getContext());
        coverView.destroyDrawingCache();
        coverView.setDrawingCacheEnabled(true);
        Bitmap bitmap = coverView.getDrawingCache();
        if (bitmap != null && !bitmap.isRecycled()) {
            floatView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            floatView.setImageBitmap(bitmap);
        }

        return floatView;
    }

    /**
     * 获取浮层视图
     */
    public View getFloatView() {
        return currentTouchedView;
    }

    /**
     * 更新浮层位置
     */
    public void updateView(int x, int y) {
        if (currentTouchedView != null) {
            params.x = x - offsetX;
            params.y = y - offsetY;
            manager.updateViewLayout(currentTouchedView, params);
        }
    }

    /**
     * 移除浮层
     */
    public void removeView() {
        if (currentTouchedView != null && manager != null) {
            manager.removeView(currentTouchedView);
        }
    }
}