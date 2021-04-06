package com.yumeng.libbaseProject.view.audioRecorder;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;


import com.yumeng.libbaseProject.Contants.Constants;
import com.yumeng.libbaseProject.R;
import com.yumeng.libbaseProject.manager.DialogManager;
import com.yumeng.libcommon.rxManager.RxPermissions;
import com.yumeng.libcommon.utils.RxCrashUtils;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 自定义按钮 实现录音等功能
 * Created by Administrator on 2017/11/28.
 */

public class AudioRecorderButton extends AppCompatImageView {
    // 按钮正常状态（默认状态）
    private static final int STATE_NORMAL = 1;
    //正在录音状态
    private static final int STATE_RECORDING = 2;
    //录音取消状态
    private static final int STATE_CANCEL = 3;
    //上滑
    private static final int STATE_SLIPPERY = 4;
    //记录当前状态
    private int mCurrentState = STATE_NORMAL;
    //是否开始录音标志
    private boolean isRecording = false;
    //判断在Button上滑动距离，以判断 是否取消
    private static final int DISTANCE_Y_CANCEL = 100;
    //完成录制
    public static final int MSG_AUDIO_COMPLETE = 1234;

    //关闭语音栏
    public static final int MSG_AUDIO_CLOSE = 0x112;
    //对话框管理工具类
    private DialogManager mDialogManager;
    //录音管理工具类
//    private AudioManager mAudioManager;
    private AudioTouchCallback audioTouchCallback = null;
    //记录录音时间
    private float mTime;
    // 是否触发longClick
    private boolean mReady;
    //录音准备
    public static final int MSG_AUDIO_PREPARED = 0x110;
    //音量发生改变
    public static final int MSG_VOICE_CHANGED = 0x111;
    //取消提示对话框
    public static final int MSG_DIALOG_DIMISS = 0x112;
    private RxPermissions rxPermissions;
    private boolean isRequest = false;
    private View anchor;

    private float maxTime = 60F;   //最大时常

    public AudioRecorder getAudioRecorder() {
        return audioRecorder;
    }

    public void setAudioRecorder(AudioRecorder audioRecorder) {
        this.audioRecorder = audioRecorder;
    }

    public void setAnchor(View anchor) {
        this.anchor = anchor;
        mDialogManager.setAnchor(anchor);
    }

    private AudioRecorder audioRecorder;

    public DialogManager getmDialogManager() {
        return mDialogManager;
    }

    /**
     * @description 获取音量大小的线程
     * @author ldm
     * @time 2016/6/25 9:30
     * @param
     */
    private Runnable mGetVoiceLevelRunnable = new Runnable() {

        public void run() {
            while (isRecording) {//判断正在录音
                try {
                    Thread.sleep(100);
                    mTime += 0.1f;//录音时间计算
                    mHandler.sendEmptyMessage(MSG_VOICE_CHANGED);//每0.1秒发送消息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_AUDIO_PREPARED:
                    //显示对话框
                    mDialogManager.showRecordingDialog();
                    mDialogManager.updateTips(getContext().getString(R.string.finger_swipe_and_cancel_sending));
                    isRecording = true;
                    if (audioTouchCallback != null)
                        audioTouchCallback.onTouchCallback(MSG_AUDIO_PREPARED);
                    // 开启一个线程计算录音时间
                    new Thread(mGetVoiceLevelRunnable).start();
                    break;
                case MSG_VOICE_CHANGED:
                    //更新声音
                    mDialogManager.setVolume(audioRecorder.getCurrentVolume());
                    if(mTime > maxTime){
                        onActionUp(false);
                        //Log.e("录制介绍","录制结束");
                    }
                    break;
                case MSG_DIALOG_DIMISS:
                    //取消对话框
                    mDialogManager.dimissDialog();
                    if (audioTouchCallback != null) {
                        audioTouchCallback.onTouchCallback(MSG_AUDIO_CLOSE);
                        mDialogManager.stopTime();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public float getmTime() {
        return mTime;
    }

    public void setmTime(float mTime) {
        this.mTime = mTime;
    }

    public void setAudioTouchCallback(AudioTouchCallback audioTouchCallback) {
        this.audioTouchCallback = audioTouchCallback;
    }

    public AudioRecorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        rxPermissions = new RxPermissions((AppCompatActivity) context);
        mDialogManager = new DialogManager(context);
        //录音文件存放地址
        audioRecorder = new AudioRecorder();
        String fileForderPath = Constants.AUDIO_FILE_PATH;
        RxCrashUtils.createOrExistsDir(fileForderPath);
        // 由于这个类是button所以在构造方法中添加监听事件
        setOnLongClickListener(v -> {
            if (isRequest) {
                mReady = true;
                String filePath = fileForderPath + File.separator + UUID.randomUUID().toString() + ".aac";
                audioRecorder.startRecording(filePath);
                mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
            }
            return false;
        });
    }

    public AudioRecorderButton(Context context) {
        this(context, null);
    }

    /**
     * @param
     * @author ldm
     * @description 录音完成后的回调
     * @time 2016/6/25 11:18
     */
    public interface AudioFinishRecorderCallBack {
        void onFinish(float seconds, String filePath, List<Double> results, boolean noSend);
    }

    private AudioFinishRecorderCallBack finishRecorderCallBack;

    public void setFinishRecorderCallBack(AudioFinishRecorderCallBack listener) {
        finishRecorderCallBack = listener;
    }

    /**
     * @param
     * @description 处理Button的OnTouchEvent事件
     * @author ldm
     * @time 2016/6/25 9:35
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取TouchEvent状态
        int action = event.getAction();
        // 获得x轴坐标
        int x = (int) event.getX();
        // 获得y轴坐标
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN://手指按下
                rxPermissions.request(Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(aBoolean -> {
                            if (aBoolean) {
                                if (VoipUtils.INSTANCE.isCalling()) {
                                    return;
                                }
                                isRequest = true;
                                changeState(STATE_RECORDING);

                            } else
                                isRequest = false;
                        });
                break;
            case MotionEvent.ACTION_MOVE://手指移动
                if (isRecording) {
                    //根据x,y的坐标判断是否需要取消
                    if (wantToCancle(x, y)) {
                        changeState(STATE_SLIPPERY);
                    } else {
                        changeState(STATE_RECORDING);
                    }
                }

                break;
            case MotionEvent.ACTION_UP://手指放开
                if (onActionUp(false))
                    return super.onTouchEvent(event);
                break;

        }
        return super.onTouchEvent(event);
    }


    public void endAudio() {
        if (mReady) {
            onActionUp(true);
        }
    }

    private boolean onActionUp(boolean noSend) {
        if (mCurrentState == STATE_SLIPPERY) {
            mCurrentState = STATE_CANCEL;
        }
        if (mDialogManager != null)
            mDialogManager.setBgSelector(false);
        if (!mReady) {
            reset();
            return true;
        }
        if (!isRecording || mTime < 1.0f) {//如果时间少于1s，则提示录音过短
//                    mDialogManager.tooShort();
//                    mAudioManager.cancel();
            audioRecorder.cancel();
            // 延迟显示对话框
            mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1000);
            reset();
        } else if (mCurrentState == STATE_RECORDING) {
            //如果状态为正在录音，则结束录制
            stopRecording(noSend);

        } else if (mCurrentState == STATE_CANCEL) { // 想要取消
            if (audioTouchCallback != null) {
//                VibratorHelper.INSTANCE.createOneShotOnImportant();
                mDialogManager.updateTips(getContext().getString(R.string.release_your_finger_and_cancel_sending));
                audioTouchCallback.onTouchCallback(STATE_CANCEL);

            }
            mDialogManager.stopTime();
            mDialogManager.dimissDialog();
//                    mAudioManager.cancel();
            audioRecorder.cancel();
            reset();
        }
        return false;
    }

    public void stopRecording(final boolean noSend) {
        Log.e("stopRecording",noSend+"");
        if (finishRecorderCallBack != null) {
            if (audioTouchCallback != null) {
                audioTouchCallback.onTouchCallback(MSG_AUDIO_COMPLETE);
            }
            audioRecorder.stopRecording(new AudioRecorder.OnRecordListener() {
                @Override
                public void onError(String content) {
                    reset();
                }

                @Override
                public void onSuccess(List<Double> results) {
                    finishRecorderCallBack.onFinish(mTime, audioRecorder.getCurrentFilePath(), results, noSend);
                    mDialogManager.stopTime();
                    reset();
                }
            });
            mDialogManager.dimissDialog();
        }
    }

    /**
     * 恢复状态及标志位
     */
    public void reset() {
        isRecording = false;
        mTime = 0;
        mReady = false;
        changeState(STATE_NORMAL);
    }

    private boolean wantToCancle(int x, int y) {
        // 超过按钮的宽度
//        if (x < 0 || x > getWidth()) {
//            return true;
//        }
        // 超过按钮的高度
        return y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL;

    }


    /**
     * @param
     * @description 根据状态改变Button显示
     * @author ldm
     * @time 2016/6/25 9:36
     */
    private void changeState(int state) {
        if (mCurrentState != state) {
            mCurrentState = state;
            switch (state) {
                case STATE_NORMAL:
                    break;

                case STATE_RECORDING:
                    if (isRecording) {
                        mDialogManager.updateTips(getContext().getString(R.string.finger_swipe_and_cancel_sending));
                        mDialogManager.setBgSelector(false);
                        if (audioTouchCallback != null) {
                            audioTouchCallback.onTouchCallback(STATE_RECORDING);
                        }
                    }
                    break;
                case STATE_SLIPPERY:
                    if (isRecording) {

                        mDialogManager.setBgSelector(true);
                        mDialogManager.updateTips(getContext().getString(R.string.release_your_finger_and_cancel_sending));
                        if (audioTouchCallback != null) {
                            audioTouchCallback.onTouchCallback(STATE_SLIPPERY);
                        }
                    }
                    break;
            }
        }
    }
}
