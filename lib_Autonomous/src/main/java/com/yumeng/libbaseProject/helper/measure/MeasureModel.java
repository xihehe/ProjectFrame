package com.yumeng.libbaseProject.helper.measure;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class MeasureModel implements Serializable {

    private int width;
    private int height;
    private String frameUrl;
    private int status;//1成功 2失败
    @Expose(serialize = false, deserialize = false)
    private long duration;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFrameUrl() {
        return frameUrl;
    }

    public void setFrameUrl(String frameUrl) {
        this.frameUrl = frameUrl;
    }


    public boolean isScale() {
        return (float)height / width > 3;
    }

}
