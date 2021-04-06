package com.yumeng.libcommonview.view.index;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.yumeng.libcommonview.R;

public class WordsNavigation extends View {

    /*绘制的列表导航字母*/
    private String words[] = {"#","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    /*字母画笔*/
    private Paint wordsPaint;
    /*字母背景画笔*/
    private Paint bgPaint;
    /*每一个字母的宽度*/
    private int itemWidth;
    /*每一个字母的高度*/
    private int itemHeight;
    /*手指按下的字母索引*/
    private int touchIndex = 0;
    /*手指按下的字母改变接口*/
    private onWordsChangeListener listener;

    public WordsNavigation(Context context) {
        super(context);
        init();
    }

    public WordsNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化画笔
     */
    private void init() {
        wordsPaint = new Paint();
        wordsPaint.setColor(Color.parseColor("#54d0ac"));
        wordsPaint.setAntiAlias(true);
        wordsPaint.setTextSize(30);
        wordsPaint.setTypeface(Typeface.DEFAULT);
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.parseColor("#54d0ac"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth = getMeasuredWidth();
        //使得边距好看一些
        int height = getMeasuredHeight() - 10;
        itemHeight = height / 30;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++) {
            //判断是不是我们按下的当前字母
            if (touchIndex == i) {
//                //绘制文字圆形背景
                wordsPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
            } else {
                wordsPaint.setColor(Color.GRAY);
            }
            //获取文字的宽高
            Rect rect = new Rect();
            wordsPaint.getTextBounds(words[i], 0, 1, rect);
            int wordWidth = rect.width();
            //绘制字母
            float wordX = itemWidth / 2 - wordWidth / 2;
            float wordY = itemWidth / 2 + i * itemHeight;
//            canvas.drawCircle(itemWidth / 2, wordY, 23, bgPaint);

//            Paint.FontMetricsInt fm = wordsPaint.getFontMetricsInt();
//            float wordX = itemWidth / 2 - wordsPaint.measureText(words[i]) / 2;
//            float y = (itemHeight / 2 - fm.descent + (fm.bottom - fm.top) / 2)*i;
            canvas.drawText(words[i], wordX, wordY, wordsPaint);
        }
    }
    private BubbleIndexBarState barState= BubbleIndexBarState.HIDDEN_BUBBLE;

    /**
     * 当手指触摸按下的时候改变字母背景颜色
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                //获得我们按下的是那个索引(字母)
                int index = (int) (y / itemHeight);
                if (index != touchIndex)
                    touchIndex = index;
                //防止数组越界
                if (listener != null && 0 <= touchIndex && touchIndex <= words.length - 1) {
                    //回调按下的字母
                    listener.wordsChange(words[touchIndex]);
                    barState=BubbleIndexBarState.VISIBLE_BUBBLE;
                    if (onTouchIndexListener != null) {
                        onTouchIndexListener.onIndexBar(itemWidth / 2 + touchIndex * itemHeight, words[touchIndex], barState);
                    }
                }

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起,不做任何操作
                barState=BubbleIndexBarState.HIDDEN_BUBBLE;
                if (onTouchIndexListener != null) {
                    onTouchIndexListener.onIndexBar(0,"", barState);
                }
                break;
        }
        return true;
    }

    /*设置当前按下的是那个字母*/
    public void setTouchIndex(String word) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                touchIndex = i;
                invalidate();
                return;
            }
        }
    }

    /*手指按下了哪个字母的回调接口*/
    public interface onWordsChangeListener {
        void wordsChange(String words);
    }

    /*设置手指按下字母改变监听*/
    public void setOnWordsChangeListener(onWordsChangeListener listener) {
        this.listener = listener;
    }


    private OnTouchIndexListener onTouchIndexListener;

    public void setOnTouchIndexListener(OnTouchIndexListener onTouchIndexListener) {
        this.onTouchIndexListener = onTouchIndexListener;
    }

    public interface OnTouchIndexListener {
        void onIndexBar(float y, String index, BubbleIndexBarState indexBarState);
    }
}

