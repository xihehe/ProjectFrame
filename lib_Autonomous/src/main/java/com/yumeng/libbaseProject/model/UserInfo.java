package com.yumeng.libbaseProject.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/21.
 */

public class UserInfo implements Serializable {
    private String token;//用户token
    private int expires_in;//过期时间
    private String refreshToken;
    private String token_type;
    private String id;
    private String avatar;//头像
    private String mobile;
    private String name;
    private String introduce;
    private String uid;
    private String sex;
    private String address;//家庭住址
    private String region;//地区
    private String country;//国家
    private String dialCode;
    private boolean authenticatedFlag;
    private long dynamicNum;
    private long fansNum;
    private long focusNum;
    private boolean uidModifyFlag;

    public String getUid() {
        return uid;
    }

    public boolean isUidModifyFlag() {
        return uidModifyFlag;
    }

    public void setUidModifyFlag(boolean uidModifyFlag) {
        this.uidModifyFlag = uidModifyFlag;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isAuthenticatedFlag() {
        return authenticatedFlag;
    }

    public void setAuthenticatedFlag(boolean authenticatedFlag) {
        this.authenticatedFlag = authenticatedFlag;
    }

    public long getDynamicNum() {
        return dynamicNum;
    }

    public void setDynamicNum(long dynamicNum) {
        this.dynamicNum = dynamicNum;
    }

    public long getFansNum() {
        return fansNum;
    }

    public void setFansNum(long fansNum) {
        this.fansNum = fansNum;
    }

    public long getFocusNum() {
        return focusNum;
    }

    public void setFocusNum(long focusNum) {
        this.focusNum = focusNum;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isMale() {
        return sex.equals("0");
    }
}
