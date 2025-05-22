package com.example.myapplication.module.textTest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

public class AlignTopImageSpan extends ImageSpan {

    /** * A constant indicating that the top of this span should be aligned * with the top of the surrounding text. */
    public static final int ALIGN_TOP = 2;

    public AlignTopImageSpan(Drawable drawable, int verticalAlignment) {
        super(drawable, verticalAlignment);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();

        int transY = bottom - b.getBounds().bottom;
        if (mVerticalAlignment == ALIGN_BASELINE) {
            transY -= paint.getFontMetricsInt().descent;
        } else if (mVerticalAlignment == ALIGN_TOP) {
            int textLength = text.length();
            for (int i = 0; i < textLength; i++) {
                // 如果是图片，则做向上偏移处理达到居上对齐的效果
                if (Character.isBmpCodePoint(text.charAt(i))) {
                    transY -= paint.getFontMetricsInt().descent * 2;
                    break;
                }
            }
        }

        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}