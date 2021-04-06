package com.yumeng.libbaseProject.dataSupport;

import android.text.TextUtils;

import org.litepal.crud.LitePalSupport;

public class RoomSettingRecords extends LitePalSupport {

    private String roomId;

    private String topFlag;//是否置顶

    private String shieldFlag;//是否静音

    public boolean isTop() {
        return !TextUtils.isEmpty(topFlag) && topFlag.equals("true");
    }

    public boolean isShield() {
        return !TextUtils.isEmpty(shieldFlag) && shieldFlag.equals("true");
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(String topFlag) {
        this.topFlag = topFlag;
    }

    public String getShieldFlag() {
        return shieldFlag;
    }

    public void setShieldFlag(String shieldFlag) {
        this.shieldFlag = shieldFlag;
    }


    public void replaceSave() {
        saveOrUpdate("roomId=?", roomId);
    }
}
