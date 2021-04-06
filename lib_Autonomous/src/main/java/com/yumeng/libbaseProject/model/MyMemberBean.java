package com.yumeng.libbaseProject.model;

import java.io.Serializable;

/**
 * 添加成员、
 */
public class MyMemberBean implements Serializable {
    private String userId;
    private String name;
    private String avatar;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

}
