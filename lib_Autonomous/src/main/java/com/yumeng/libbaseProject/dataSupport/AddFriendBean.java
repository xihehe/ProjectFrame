package com.yumeng.libbaseProject.dataSupport;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.yumeng.libbaseProject.type.AddFriendType;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * 添加好友 实体
 */

public class AddFriendBean extends LitePalSupport implements Serializable {

    @SerializedName("id")
    private String idFlag;//用户标识
    private String email;//邮箱
    private String mobile;//手机
    private String name;//用户名
    private String avatar;//头像
    private String sex;//性别
    private String roomId;//房间号
    private String region;//地区
    private String country;//国家
    private boolean delFlag;
    private String requestId;
    private String uid;
    @Column(ignore = true)
    private int source;
    /**
     * 0 // 待处理
     * 1   // 已同意
     */
    private int status;
    private String remark;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getSource() {
        return source;
    }

    public void setSourceValue(String keyword) {
        if (!TextUtils.isEmpty(keyword) && keyword.equals(mobile)) {
            source = AddFriendType.Mobile.getType();
        } else {
            source = AddFriendType.UID.getType();
        }
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(String idFlag) {
        this.idFlag = idFlag;
    }

    public String getId() {
        return idFlag;
    }

    public void setId(String id) {
        this.idFlag = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }


    public void replaceSave() {
        if (TextUtils.isEmpty(requestId)) {
            saveOrUpdate();
        } else {
            saveOrUpdate("requestId=?", requestId);
        }
    }
}
