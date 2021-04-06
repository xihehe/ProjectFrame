package com.yumeng.libbaseProject.view.audioRecorder;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;

import com.yumeng.libcommon.utils.MainThreadUtil;
import com.yumeng.libcommon.utils.ThreadUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class AudioRecorder {

    private static final String TAG = AudioRecorder.class.getSimpleName();

    private static final ExecutorService executor = ThreadUtil.newDynamicSingleThreadedExecutor();


    private AudioCodec audioCodec;
    private String filePath;
    private BufferedOutputStream mAudioBos;

    public AudioRecorder() {
    }

    public void startRecording(String filePath) {

        executor.execute(() -> {
            try {
                if (audioCodec != null) {
                    throw new AssertionError("We can only record once at a time.");
                }
                this.filePath = filePath;
                audioCodec = new AudioCodec();
                mAudioBos = new BufferedOutputStream(new FileOutputStream(new File(filePath)), 200 * 1024);
                audioCodec.start(mAudioBos);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void release() {
        if (audioCodec == null) {
            return;
        }
        audioCodec.clearVolumes();
        audioCodec.stop();
        audioCodec = null;
        try {
            if (mAudioBos != null) {
                mAudioBos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mAudioBos != null) {
                try {
                    mAudioBos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    mAudioBos = null;
                }
            }
        }

    }

    public void stopRecording(OnRecordListener onRecordListener) {
        executor.execute(() -> {
            if (audioCodec == null) {
                MainThreadUtil.runOnMain(() -> onRecordListener.onError("MediaRecorder was never initialized successfully!"));
                return;
            }
            List<Double> dilution = dilution(audioCodec.getVolumes());
            release();
            MainThreadUtil.runOnMain(() -> onRecordListener.onSuccess(dilution));
        });
    }

    private List<Double> dilution(List<Integer> volumes) {
        int count = (int) Math.min(Math.round(volumes.size() / 10.0) + 4, 15);
        int width = volumes.size() / count;
        if ((width + 1) * (count - 1) < volumes.size()) {
            width += 1;
        }
        int index = width / 2;
        List<Integer> temps = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if ((i + 1) * width < volumes.size()) {
                temps.add(volumes.get(i * width + index));
            } else {
                int lastCount = volumes.size() - i * width;
                index = lastCount / 2;
                temps.add(volumes.get(i * width + index));
            }
        }
        List<Double> results = new ArrayList<>();
        for (Integer temp : temps) {
            if(temp<20){
                temp=20;
            }
            double value = (double) (temp - 20) / 18;
            results.add(value);
        }
        return results;
    }

    public void cancel() {
        executor.execute(() -> {
            if (!TextUtils.isEmpty(filePath)) {
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                    filePath = null;
                }
            }
            release();
        });
    }

    public String getCurrentFilePath() {
        return filePath;
    }

    public interface OnRecordListener {
        public void onError(String content);

        public void onSuccess(List<Double> results);
    }

    //获取当前音量
    public int getCurrentVolume() {
        if (audioCodec != null)
            return audioCodec.getVolume();
        return 0;
    }
}
