package com.yumeng.libcommonview.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yumeng.libcommonview.R;


/**
 * 底部弹窗
 */
public class BottomDialog extends Dialog {
    private View mView;

    public BottomDialog(Context context) {
        super(context);
    }

    public BottomDialog(View view) {
        super(view.getContext());
        setView(view);
    }

    public void setView(View view) {
        setContentView(view);
        mView = view;
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.BottomDialogTheme);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            mView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            lp.width = getContext().getResources().getDisplayMetrics().widthPixels;
            lp.height = mView.getMeasuredHeight();
            window.setAttributes(lp);
        }
        super.show();
    }
}
