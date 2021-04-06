package com.yumeng.libbaseProject.dataSupport;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.yumeng.libbaseProject.listener.IndexableEntity;
import com.yumeng.libcommon.context.AppContextWrapper;
import com.yumeng.libcommon.utils.PinYinUtils;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;


import java.io.Serializable;
import java.util.Locale;

import static org.litepal.LitePal.where;

/**
 * 好友信息 - 数据库 好友表
 */

public class FriendInfo extends LitePalSupport implements Serializable, IndexableEntity {
    //拼音
    private String pinyin;
    //拼音首字母
    private String headerWord;
    @SerializedName("id")
    private String friend_id;
    private String email;
    private String mobile;
    private String name;
    private String avatar;
    private String nickname;
    private String user;
    private String showName;
    @Column(unique = true)
    private String roomId;
    private String not_disturb;
    private String delFlag = "false";
    private String topFlag;
    private String shieldFlag;
    private String blacklistFlag = "false";
    private String region;//地区
    private String country;//国家
    private String sex;
    private String dialCode;
    @Column(ignore = true)
    private boolean select;
    @Column(ignore = true)
    private boolean enable;//不可点击 true
    private boolean openEndToEndEncrypt;
    @Column(ignore = true)
    private String publicKeyJson;
    @SerializedName("enctryptRoomId")
    private String cryptoRoomId;
    @Column(ignore = true)
    private boolean isEncrypted = true;
    private String uid;
    @Column(defaultValue = "-1")
    private long contactId ; //对应通讯录的contactId

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    public String getCryptoRoomId() {
        return cryptoRoomId;
    }

    public void setCryptoRoomId(String cryptoRoomId) {
        this.cryptoRoomId = cryptoRoomId;
    }

    public String getPublicKeyJson() {
        return publicKeyJson;
    }

    public void setPublicKeyJson(String publicKeyJson) {
        this.publicKeyJson = publicKeyJson;
    }

    public boolean isOpenEndToEndEncrypt() {
        return openEndToEndEncrypt;
    }

    public void setOpenEndToEndEncrypt(boolean openEndToEndEncrypt) {
        this.openEndToEndEncrypt = openEndToEndEncrypt;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getNot_disturb() {
        return not_disturb;
    }

    public void setNot_disturb(String not_disturb) {
        this.not_disturb = not_disturb;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderWord() {
        if (TextUtils.isEmpty(pinyin)) {
            this.pinyin = PinYinUtils.getPinyin(getShowname());
        }
        headerWord=pinyin.substring(0, 1).toUpperCase();
        return headerWord;
    }

    public String getSortName(){
        if (TextUtils.isEmpty(pinyin)) {
            String showName = getShowname();
            if(showName==null || showName.isEmpty()){
                showName = "unknow";
            }
            this.pinyin = PinYinUtils.getPinyin(showName);
        }
        headerWord=pinyin.substring(0, 1).toUpperCase();
        char c = headerWord.charAt(0);
        if(c >= 'A' && c <= 'Z'){
            return headerWord;
        }else{
            return "{"+headerWord;
        }
    }

    public String getPinyin() {
        this.pinyin = PinYinUtils.getPinyin(getShowname());
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setHeaderWord(String headerWord) {
        this.headerWord = headerWord;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getShowname() {
        SharedPreferences sharedPreferences = AppContextWrapper.Companion.getBaseContext().getSharedPreferences("app_info", Context.MODE_PRIVATE);
        int languageType = sharedPreferences.getInt("save_language",2);
        String strangerStr = "";
        if(languageType == 1) {
            strangerStr = "stranger";
        }else{
            strangerStr = "陌生人";
        }

        if(delFlag.equals("true") ){
            if(mobile != null )
                showName = mobile + "\n"+ strangerStr;
            else
                showName = strangerStr;
        }else{
            if(showName==null){
                showName = name;
            }
        }

        return showName;
    }

    public void setShowname(String showname) {
        this.showName = showname;
    }

    public String getRoomId() {
        if (isEncrypted) {
            return cryptoRoomId;
        } else {
            return roomId;
        }
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDelFlag() {
        if (TextUtils.isEmpty(delFlag)) {
            return "false";
        }
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(String topFlag) {
        this.topFlag = topFlag;
    }

    public String getShieldFlag() {
        if (!TextUtils.isEmpty(shieldFlag))
            return shieldFlag;
        else
            return "false";
    }

    public void setShieldFlag(String shieldFlag) {
        this.shieldFlag = shieldFlag;
    }

    public String getBlacklistFlag() {
        if (!TextUtils.isEmpty(blacklistFlag))
            return blacklistFlag;
        else
            return "false";
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setBlacklistFlag(String blacklistFlag) {
        this.blacklistFlag = blacklistFlag;
    }

    public void replaceSave() {
        saveOrUpdate("friend_id=?", friend_id);
    }

    public void updateSave() {
        updateAll("friend_id=?", friend_id);
    }


    public FriendInfo findFirstData() {
        return where("friend_id=?", friend_id).findFirst(FriendInfo.class);
    }


    @Override
    public String getFieldIndexBy() {
        return showName;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        pinyin=indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {
    }


    //以上获取方式需要特殊处理一下
    private Locale getSysLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }
}
