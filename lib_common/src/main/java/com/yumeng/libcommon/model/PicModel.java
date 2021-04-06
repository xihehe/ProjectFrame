package com.yumeng.libcommon.model;

import java.io.Serializable;

//这个类仅用于FileUtils 里面 获取图片的宽高数据的存储
public class PicModel implements Serializable {

    private int width;
    private int height;
    private String frameUrl;

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
}
