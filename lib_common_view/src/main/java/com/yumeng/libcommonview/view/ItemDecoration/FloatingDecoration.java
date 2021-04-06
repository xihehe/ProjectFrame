package com.yumeng.libcommonview.view.ItemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yumeng.libcommon.helper.DensityHelper;

/**
 * Created by Chauncey on 2018/5/11 11:42.
 * 可以附在recycler 上的title
 * 效果类似于  联系人 的  字母分组
 */
public class FloatingDecoration extends RecyclerView.ItemDecoration {

    private DecorationCallback mDecorationCallback;
    //    private Paint.FontMetrics mMetrics;
    private Drawable mDividingLine;
    private TextPaint mTextPaint;
    private Paint mPaint;
    private int mLabelRectHeight;
    private Context mContext;

    private int mTitleMarginStart = 30;
    private int mDividerMarginStart, mDividerMarginEnd;

    private Rect mTextRect = new Rect();

    public FloatingDecoration(Context context, DecorationCallback callback) {
        mContext = context;
        mDecorationCallback = callback;
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(14);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mLabelRectHeight = (int) DensityHelper.dip2px(context, 24F);

        mPaint = new Paint();
        mPaint.setColor(Color.LTGRAY);

//        mMetrics = mTextPaint.getFontMetrics();
    }

    private Paint.FontMetrics getMetrics() {
        return mTextPaint.getFontMetrics();
    }

    private Rect getTextRect(String text) {
        mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);
        return mTextRect;
    }

    public void setDividingLine(Drawable line) {
        mDividingLine = line;
    }

    public void setDividerMarginStart(int dividerMarginStart) {
        mDividerMarginStart = dividerMarginStart;
    }

    public void setDividerMarginEnd(int dividerMarginEnd) {
        mDividerMarginEnd = dividerMarginEnd;
    }

    public void enableFakeBoldText(boolean b) {
        mTextPaint.setFakeBoldText(b);
    }

    public void setLabelTextSize(int textSize) {
        mTextPaint.setTextSize(textSize);
    }

    public void setLabelTextAlign(Paint.Align align) {
        mTextPaint.setTextAlign(align);
    }

    public void setLabelTextColor(@ColorInt int color) {
        mTextPaint.setColor(color);
    }

    public void setLabelTextColorRes(@ColorRes int color) {
        mTextPaint.setColor(ContextCompat.getColor(mContext, color));
    }

    public void setLabelTextPaint(@NonNull TextPaint textPaint) {
        mTextPaint = textPaint;
//        mMetrics = textPaint.getFontMetrics();
    }

    public void setFloatingBarHeight(int height) {
        mLabelRectHeight = height;
    }

    public void setFloatingBackgroundColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    public void setFloatingBarBackgroundColorRes(@ColorRes int color) {
        mPaint.setColor(ContextCompat.getColor(mContext, color));
    }

    public void setTitleMarginStart(int titleMarginStart) {
        mTitleMarginStart = titleMarginStart;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        if (pos < 0) {
            return;
        }
        String groupLabel = mDecorationCallback.getGroupLabel(pos);
        if (groupLabel == null) return;
        if (isFirstInGroup(pos))
            outRect.top = mLabelRectHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        if (mDividingLine != null) {
            drawDividingLine(c, parent, left, right, childCount);
        }
        drawLabelText(c, parent, left, right, childCount);

    }

    private void drawDividingLine(Canvas canvas, RecyclerView parent, int left, int right, int childCount) {
        for (int i = 0; i < childCount; i++) {

            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
            int top = view.getBottom() + lp.bottomMargin;
            int bottom = top + mDividingLine.getIntrinsicHeight();
            mDividingLine.setBounds(left + mDividerMarginStart, top, right - mDividerMarginEnd, bottom);
            mDividingLine.draw(canvas);
        }
    }

    private void drawLabelText(Canvas canvas, RecyclerView parent, int left, int right, int childCount) {
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String groupLabel = mDecorationCallback.getGroupLabel(position);
            if (groupLabel == null) return;

            if (isFirstInGroup(position)) {
                int bottom = view.getTop();
                int top = bottom - mLabelRectHeight;
                if (mDividingLine == null) {
                    canvas.drawRect(left, top, right, bottom, mPaint);
                } else {
                    canvas.drawRect(left, top + mDividingLine.getIntrinsicHeight(), right, bottom, mPaint);
                }
                final Rect rect = getTextRect(groupLabel);
                canvas.drawText(groupLabel, left + mTitleMarginStart, bottom - (float) ((mLabelRectHeight - rect.bottom + rect.top) / 2), mTextPaint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager == null || !(layoutManager instanceof LinearLayoutManager)) {
            return;
        }
        int position = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        if (position < 0) {
            return;
        }
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        String label = mDecorationCallback.getGroupLabel(position);
        if (label == null) return;
        final int count = parent.getChildCount();
        final Rect rect = getTextRect(label);
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            if (isLastInGroup(position, state)) {
                int bottom = child.getBottom();
                if (bottom < mLabelRectHeight) {
                    canvas.drawRect(left, 0, right, bottom, mPaint);
//                    if (mDividingLine != null) {
//                        mDividingLine.setBounds(left, bottom, right, bottom + mDividingLine.getIntrinsicHeight());
//                        mDividingLine.draw(canvas);
//                    }
                    canvas.drawText(label, mTitleMarginStart, (float) (mLabelRectHeight / 2 + (rect.bottom - rect.top) / 2 - (mLabelRectHeight - bottom)), mTextPaint);
                    return;
                }
            }
        }
        canvas.drawRect(left, 0, right, mLabelRectHeight, mPaint);
//        if (mDividingLine != null) {
//            mDividingLine.setBounds(left, mLabelRectHeight, right, mLabelRectHeight + mDividingLine.getIntrinsicHeight());
//            mDividingLine.draw(canvas);
//        }
//        int baseLineY = (int) (getTextRect(label).centerY() - getMetrics().top / 2 - getMetrics().bottom / 2);//基线中间点的y轴计算公式
        canvas.drawText(label, left + mTitleMarginStart, (float) ((mLabelRectHeight + rect.bottom - rect.top) / 2), mTextPaint);
    }

    private boolean isFirstInGroup(int pos) {
        if (pos < 0) {
            return false;
        } else if (pos == 0) {
            return true;
        } else {
            String prevLabel = mDecorationCallback.getGroupLabel(pos - 1);
            String label = mDecorationCallback.getGroupLabel(pos);

            return prevLabel != null && !prevLabel.equals(label);
        }
    }

    private boolean isLastInGroup(int pos, RecyclerView.State state) {

        if (pos < 0) {
            return false;
        } else if (pos + 1 >= state.getItemCount()) {
            return true;
        } else {
            String label = mDecorationCallback.getGroupLabel(pos);
            String nextLabel = mDecorationCallback.getGroupLabel(pos + 1);

            return !label.equals(nextLabel);
        }
    }

    private double getLabelRectHeight() {
        return Math.ceil(getMetrics().descent - getMetrics().ascent);
    }

    public interface DecorationCallback {
        String getGroupLabel(int position);
    }
}
