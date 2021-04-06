package com.yumeng.libcommonview.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.yumeng.libcommon.helper.DensityHelper;
import com.yumeng.libcommonview.R;

public class OneKeyClearEditText extends AppCompatEditText {
    private Drawable mClearDrawable;
    private boolean mIsClearVisible;
    private int mDrawableWidth, mDrawableHeight;

    public OneKeyClearEditText(Context context) {
        this(context, null);
    }

    public OneKeyClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public OneKeyClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        Drawable drawables[] = getCompoundDrawables();
        mClearDrawable = drawables[2]; // Right Drawable;
        if (mClearDrawable == null) {
            mClearDrawable = ContextCompat.getDrawable(context, R.drawable.ic_clear);
            mClearDrawable.setBounds(0, 0, DensityHelper.dp2px(getContext(), 16), DensityHelper.dp2px(getContext(), 16));
        }
        final Resources.Theme theme = context.getTheme();

        TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.OneKeyClearEditText,
                defStyleAttr, defStyleAttr);

        int rightDrawableColor = a.getColor(R.styleable.OneKeyClearEditText_clearDrawableColor,
                Color.LTGRAY);

        mDrawableWidth = a.getDimensionPixelSize(R.styleable.OneKeyClearEditText_clearDrawableWidth, 0);
        mDrawableHeight = a.getDimensionPixelSize(R.styleable.OneKeyClearEditText_clearDrawableHeight, 0);

        if (mDrawableWidth != 0 && mDrawableHeight != 0) {
            mClearDrawable.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }

        a.recycle();

        // 给mRightDrawable上色
        DrawableCompat.setTint(mClearDrawable, rightDrawableColor);

        // 添加TextChangedListener
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setClearDrawableVisible(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // 第一次隐藏
        setClearDrawableVisible(false);
    }

    public void setClearDrawable(Drawable clearDrawable) {
        mClearDrawable = clearDrawable;
        invalidate();
    }

    public void setDrawableWidth(int drawableWidth) {
        this.mDrawableWidth = drawableWidth;
        invalidate();
    }

    public void setDrawableHeight(int mDrawableHeight) {
        this.mDrawableHeight = mDrawableHeight;
        invalidate();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // error drawable 不显示 && clear drawable 显示 && action up
        if (getError() == null && mIsClearVisible && event.getAction() == MotionEvent.ACTION_UP) {
            performClick();
            float x = event.getX();
            if (x >= getWidth() - getTotalPaddingRight() && x <= getWidth() - getPaddingRight()) {
                clearText();
            }
        }

        return super.onTouchEvent(event);
    }

    public void clearText() {
        setText("");
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (getError() == null) {
            if (focused) {
                if (getText()!=null&&getText().length() > 0) {
                    setClearDrawableVisible(true);
                }
            } else {
                setClearDrawableVisible(false);
            }
        }
    }

    public void setClearDrawableVisible(boolean isVisible) {
        final Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(drawables[0], drawables[1],
                isVisible ? mClearDrawable : null, drawables[3]);

        mIsClearVisible = isVisible;
    }
}
