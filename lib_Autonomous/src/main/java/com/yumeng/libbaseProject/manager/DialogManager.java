package com.yumeng.libbaseProject.manager;

import android.content.Context;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.yumeng.libbaseProject.R;
import com.yumeng.libbaseProject.helper.measure.MeasureHelper;
import com.yumeng.libcommon.utils.ViewUtils;
import com.yumeng.libcommonview.theme.Theme;
import com.yumeng.libcommonview.view.CommonPopupWindow;
import com.yumeng.libcommonview.view.recordWaveView.RecordWaveView;


public class DialogManager {
    private CommonPopupWindow window;
    private Context mContext;
    private RecordWaveView recordWaveView;
    private Chronometer time;
    private TextView tvTips;
    private View anchor;
    private LinearLayout llBg;
    public DialogManager(Context context) {
        mContext = context;
    }

    public void setAnchor(View anchor) {
        this.anchor = anchor;
        window = new CommonPopupWindow(mContext, R.layout.dialog_audio, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {

            @Override
            protected void initView() {
                View view = getContentView();
                recordWaveView = ViewUtils.findViewsById(view, R.id.recordWaveView);
                time = ViewUtils.findViewsById(view, R.id.time);
                tvTips = ViewUtils.findViewsById(view, R.id.tips);
                llBg = ViewUtils.findViewsById(view, R.id.llBg);
            }

            @Override
            protected void initEvent() {

            }
        };
    }

    public void showRecordingDialog() {
        recordWaveView.setNormalData();
        setBasicColor();
        int windowsPos[] = MeasureHelper.INSTANCE.calculatePopWindowPos(anchor,window.getContentView());
        window.showAtLocation(anchor, Gravity.NO_GRAVITY, windowsPos[0], windowsPos[1]);
        time.setBase(SystemClock.elapsedRealtime());
        time.start();
    }

    public void dimissDialog() {
        window.getPopupWindow().dismiss();
        recordWaveView.stop();

    }

    public void setVolume(int volume) {
//        vlVoice.setVolume(volume);
        recordWaveView.setVolume(volume);
    }

    public void stopTime() {
        time.stop();
    }

    public Chronometer getTime() {
        return time;
    }

    public void updateTips(String tips) {
        tvTips.setText(tips);
    }

    public void setBgSelector(boolean selector){
        llBg.setSelected(selector);
        if(selector){
            recordWaveView.setmWaveColor(mContext.getResources().getColor(R.color.white));
            time.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            setBasicColor();
        }
    }

    private void setBasicColor() {

        time.setTextColor(Theme.Companion.getThemeColor());
        recordWaveView.setmWaveColor(Theme.Companion.getThemeColor());
//        switch (Theme.Companion.getThemePosition()){
//            case 1:
//                time.setTextColor(mContext.getResources().getColor(R.color.basic_theme_colors_blue));
//                recordWaveView.setmWaveColor(mContext.getResources().getColor(R.color.basic_theme_colors_blue));
//                break;
//            default:
//                time.setTextColor(mContext.getResources().getColor(R.color.basic_theme_colors));
//                recordWaveView.setmWaveColor(mContext.getResources().getColor(R.color.basic_theme_colors));
//                break;
//        }
    }
}