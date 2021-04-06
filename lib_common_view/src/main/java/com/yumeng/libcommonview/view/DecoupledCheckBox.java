package com.yumeng.libcommonview.view;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.checkbox.MaterialCheckBox;

/**
 * Created by Chauncey on 2018/1/29 13:28.
 * 点击和选中解耦的CheckBox
 */

public class DecoupledCheckBox extends MaterialCheckBox {

    //若是点击事件，不会触发toggle
    private boolean isClick;

    public DecoupledCheckBox(Context context) {
        super(context);
    }

    public DecoupledCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DecoupledCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {
        if (!isClick) {
            super.toggle();
        }
    }

    @Override
    public boolean performClick() {
        isClick = true;
        boolean b = super.performClick();
        isClick = false;
        return b;
    }
}
