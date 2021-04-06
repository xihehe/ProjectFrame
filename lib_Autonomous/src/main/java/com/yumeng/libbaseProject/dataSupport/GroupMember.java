package com.yumeng.libbaseProject.dataSupport;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.yumeng.libbaseProject.listener.IndexableEntity;
import com.yumeng.libcommon.utils.PinYinUtils;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * 群成员列表
 */

public class GroupMember extends LitePalSupport implements Serializable, IndexableEntity {

    private String userId;
    private String name;
    private String avatar;
    private String delFlag;
    //拼音
    private String pinyin;
    //拼音首字母
    private String headerWord;
    //房间号
    private String roomId;
    //在本群的昵称
    private String nickName;

    @Column(ignore = true)
    private boolean select;

    @Column(ignore = true)
    private boolean enable;

    @Column(ignore = true)
    private int operating;//1 添加  -1减少

    private int adminType;//0: 普通成员 1 管理员 9 群主

    private boolean forbidSpeakFlag;//false：未禁止 true：禁止
    private String mobile;
    private String dialCode;

    @Override
    public int hashCode() {
        int result = 1;
        if (!TextUtils.isEmpty(userId)) {
            result = 31 * result + userId.hashCode();
        }
        if(roomId!=null){
            result = 31 * result + roomId.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof GroupMember)) {
            return false;
        }
        GroupMember other = (GroupMember) obj;
        if(roomId==null){
            return userId.equals(other.getUserId());
        }else{
            return userId.equals(other.getUserId()) && roomId.equals(other.roomId);
        }
    }

    public boolean isForbidSpeakFlag() {
        return forbidSpeakFlag;
    }

    public void setForbidSpeakFlag(boolean forbidSpeakFlag) {
        this.forbidSpeakFlag = forbidSpeakFlag;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getAdminType() {
        return adminType;
    }

    public void setAdminType(int adminType) {
        this.adminType = adminType;
    }

    public int getOperating() {
        return operating;
    }

    public final static int PLUS = 1;
    public final static int DEC = -1;

    public void setOperating(int operating) {
        this.operating = operating;
    }

    public String getShowName() {
        if (!TextUtils.isEmpty(nickName)) {
            return nickName;
        } else {
            return name;
        }
    }

    public String getHeaderWord() {
        if (TextUtils.isEmpty(pinyin)) {
            this.pinyin = PinYinUtils.getPinyin(name);
        }
        headerWord = pinyin.substring(0, 1).toUpperCase();
        return headerWord;
    }

    public String getSortName(){
        if (TextUtils.isEmpty(pinyin)) {
            this.pinyin = PinYinUtils.getPinyin(name);
        }
        headerWord=pinyin.substring(0, 1).toUpperCase();
        char c = headerWord.charAt(0);
        if(c >= 'A' && c <= 'Z'){
            return headerWord;
        }else{
            return "{"+headerWord;
        }
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPinyin() {
        this.pinyin = PinYinUtils.getPinyin(name);
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setHeaderWord(String headerWord) {
        this.headerWord = headerWord;
    }

    public String getName() {
//        return name;
        return getShowName();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDelFlag() {
        if (!TextUtils.isEmpty(delFlag))
            return delFlag;
        else
            return "false";
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void replaceSave() {
        saveOrUpdate("roomId=? and userId=?", roomId, userId);
    }

    @Override
    public String getFieldIndexBy() {
        return getShowName();
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        pinyin=indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }
}
