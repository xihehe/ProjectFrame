package com.yumeng.libcommonview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.yumeng.libcommon.helper.DensityHelper;


/**
 * Created by Chauncey on 2018/2/1 10:30.
 * 带点的imageview
 */
public class DotMenuView extends AppCompatImageView {
    private Paint mRectPaint;
    private boolean mDotVisible;
    private int mMarginTop = 0;
    private int mMarginEnd = 0;
    private int mDotColor = Color.RED;

    public DotMenuView(Context context) {
        this(context, null);
    }

    public DotMenuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setDotVisible(boolean dotVisible) {
        this.mDotVisible = dotVisible;
        invalidate();
    }

    public void setMarginEnd(int marginEnd) {
        this.mMarginEnd = marginEnd;
        invalidate();
    }

    public void setMarginTop(int marginTop) {
        this.mMarginTop = marginTop;
        invalidate();
    }

    public void setDotColor(int dotColor) {
        if (mDotColor == dotColor) {
            return;
        }
        mDotColor = dotColor;
        mRectPaint.setColor(mDotColor);
        invalidate();
    }

    private void init() {
//        ((ViewGroup) getParent()).setClipChildren(false);
        int padding = (int) DensityHelper.dip2px(getContext(), 12);
        int size = (int) DensityHelper.dip2px(getContext(), 48);
        setLayoutParams(new ViewGroup.LayoutParams(size, size));
        setPadding(padding, padding, padding, padding);
        mRectPaint = new Paint();
        mRectPaint.setColor(mDotColor);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDotVisible) {
            int radius = (int) DensityHelper.dip2px(getContext(), 4);

            final int width = getWidth();
            canvas.drawCircle(width - radius - mMarginEnd, radius + mMarginTop, radius, mRectPaint);
        }
    }
}
