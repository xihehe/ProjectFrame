package com.yumeng.libcommonview.view.CurrencyFormat;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import com.yumeng.libcommon.helper.DensityHelper;
import com.yumeng.libcommonview.R;


/**
 * Created by Chauncey on 2018/2/1 16:51.
 * 货币TextView
 */
public class CurrencyFormatTextView extends AppCompatTextView {

    private int mIntegerTextSize;
    private int mFloatTextSize;
    private int mSymbolTextSize;

    private boolean isFormatEnable = true;

    public CurrencyFormatTextView(Context context) {
        this(context, null);
    }

    public CurrencyFormatTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurrencyFormatTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final Resources.Theme theme = context.getTheme();

        TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.CurrencyFormatTextView,
                defStyleAttr, defStyleAttr);
        final int sp12 = (int) DensityHelper.dp2px(context, 12f);
        final int sp16 = (int) DensityHelper.dp2px(context, 16f);
        mSymbolTextSize = a.getDimensionPixelSize(R.styleable.CurrencyFormatTextView_symbolTextSize, sp12);
        mIntegerTextSize = a.getDimensionPixelSize(R.styleable.CurrencyFormatTextView_integerTextSize, sp16);
        mFloatTextSize = a.getDimensionPixelSize(R.styleable.CurrencyFormatTextView_floatTextSize, sp12);
        init();
    }

    private void init() {
        setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        setGravity(Gravity.CENTER);
        setText("0");
    }

    public void setFormatEnable(boolean formatEnable) {
        isFormatEnable = formatEnable;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        CharSequence sequence = isFormatEnable ? new CurrencyFormat(mSymbolTextSize, mIntegerTextSize, mFloatTextSize)
                .formatPrice(text == null ? "" : text.toString()) : text;

        super.setText(sequence, type);

    }

    public void setIntegerTextSize(int integerTextSize) {
        mIntegerTextSize = integerTextSize;
    }

    public void setFloatTextSize(int floatTextSize) {
        mFloatTextSize = floatTextSize;
    }

    public void setSymbolTextSize(int symbolTextSize) {
        mSymbolTextSize = symbolTextSize;
    }
}
