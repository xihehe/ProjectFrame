package com.yumeng.libcommonview.view.recordWaveView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.yumeng.libcommonview.R;


public class RecordWaveView extends View {
    private LimitQueue<Short> datas = new LimitQueue<>(21);
    private short max = 300;
    private float mHeight;
    private float space = 1f;
    private Paint mWavePaint;
    private int mWaveColor = Color.WHITE;
    private int mBaseColor = Color.WHITE;
    private float waveStrokeWidth = 4f;
    private int invalidateTime = 1000 / 100;
    private boolean isMaxConstant = false;
    private int y = 1;
    private boolean isStart = false;

    public RecordWaveView(Context context) {
        this(context, null);
    }

    public RecordWaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecordWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.WaveView, defStyle, 0);
        mWaveColor = a.getColor(
                R.styleable.WaveView_waveColor,
                mWaveColor);
        mBaseColor = a.getColor(R.styleable.WaveView_baseColor, mBaseColor);
        waveStrokeWidth = a.getDimension(
                R.styleable.WaveView_waveStokeWidth,
                waveStrokeWidth);
        max = (short) a.getInt(R.styleable.WaveView_maxValue, max);
        invalidateTime = a.getInt(R.styleable.WaveView_invalidateTime, invalidateTime);
        space = a.getDimension(R.styleable.WaveView_space, space);
        a.recycle();
        initPainters();
    }

    private void initPainters() {
        mWavePaint = new Paint();
        mWavePaint.setColor(mWaveColor);// 画笔为color
        mWavePaint.setStrokeWidth(waveStrokeWidth);// 设置画笔粗细
        mWavePaint.setAntiAlias(true);
        mWavePaint.setFilterBitmap(true);
        mWavePaint.setStrokeCap(Paint.Cap.ROUND);
        mWavePaint.setStyle(Paint.Style.FILL);

    }

    public short getMax() {
        return max;
    }

    public void setMax(short max) {
        this.max = max;
    }

    public float getSpace() {
        return space;
    }

    public void setSpace(float space) {
        this.space = space;
    }

    public int getmWaveColor() {
        return mWaveColor;
    }

    public void setmWaveColor(int mWaveColor) {
        this.mWaveColor = mWaveColor;
        mWavePaint.setColor(mWaveColor);
    }


    public float getWaveStrokeWidth() {
        return waveStrokeWidth;
    }

    public void setWaveStrokeWidth(float waveStrokeWidth) {
        this.waveStrokeWidth = waveStrokeWidth;
        invalidateNow();
    }

    public int getInvalidateTime() {
        return invalidateTime;
    }

    public void setInvalidateTime(int invalidateTime) {
        this.invalidateTime = invalidateTime;
    }

    public boolean isMaxConstant() {
        return isMaxConstant;
    }

    public void setMaxConstant(boolean maxConstant) {
        isMaxConstant = maxConstant;
    }

    /**
     * 如果改变相应配置  需要刷新相应的paint设置
     */
    public void invalidateNow() {
        initPainters();
        invalidate();
    }

    /**
     * 第一次点击调用此方法
     */
    public void setNormalData() {
        getLayoutParams().width = (int) ((21 - 1) * space + waveStrokeWidth);
        if (datas.size() < 21) {
            datas.clear();
            for (int i = 0; i < 21; i++) {
                datas.add((short) 10);
            }
        }
        y = 1;
        isStart = true;
        invalidate();
    }

    public void dismiss() {
        isStart = false;
    }


    public void setVolume(int volume) {
        if(volume<20){
            volume=20;
        }
        int size = datas.size();
        if (size == 21) {
            double value = (double) (volume - 20) / 18;
            short v = (short) (value * 240 + 10);
            datas.offer(v);
            y++;
        }
    }

    public void clear() {
        datas.clear();
        invalidateNow();
    }

    public void stop() {
        isStart = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawWave(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mHeight = h;
    }


    private void drawWave(Canvas mCanvas) {
        mCanvas.translate(0, mHeight / 2);
        int i = 0;
        for (Short data : datas) {
            float x;
            float value = waveStrokeWidth / 2;
            if (i == 0) {
                x = value;
            } else {
                x = (i) * space + value;
            }

            float y = (float) data / max * mHeight / 2;
            mCanvas.drawLine(x, -y, x, y, mWavePaint);
            i++;
        }
        if (isStart) {
            postInvalidateDelayed(10);
        }
    }


}
