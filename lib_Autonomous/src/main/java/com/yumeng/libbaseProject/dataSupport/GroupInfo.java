package com.yumeng.libbaseProject.dataSupport;

import android.text.TextUtils;

import com.yumeng.libbaseProject.listener.IndexableEntity;
import com.yumeng.libcommon.utils.PinYinUtils;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

import static org.litepal.LitePal.where;

/**
 * 群信息
 */

public class GroupInfo extends LitePalSupport implements Serializable, IndexableEntity {
    private String name;//群名称
    private String creator;//创建者
    private String owner;//群主
    @Column(unique = true)
    private String roomId;//房间号
    @Column(ignore = true)
    private List<GroupMember> groupMembers;
    @Column(ignore = true)
    private ChatMessage message;
    //    private String content;//消息内容
//    @JSONField(name = "_offline_count")
//    private int offline_count;//未读消息
//    private int onlineMessage;//在线未读消息数
    private String avatar;
    private String isTop;
    private String topFlag;//是否置顶
    private String shieldFlag;//免打扰
    @Column(ignore = true)
    private List<String> userIds;
    @Column(ignore = true)
    private int searCount;
    private String inviteSwitch;
    private int memberCount;//群人数
    @Column(ignore = true)
    private List<String> userNames;
    @Column(ignore = true)
    private int isEncrypt;
    private int isEncryptGroup = 0;
    private String isForbidAdd;//是否允许加好友
    private boolean forbidSpeakFlag;//是否禁止全群发言false：未禁止 true：禁止
    private int status; //0表示正常，1表示解散群
    @Column(ignore = true)
    private boolean select;
    //拼音
    @Column(ignore = true)
    private String pinyin;
    //拼音首字母
    @Column(ignore = true)
    private String headerWord;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    public String getHeaderWord() {
        if (TextUtils.isEmpty(pinyin)) {
            this.pinyin = PinYinUtils.getPinyin(name);
        }
        headerWord=pinyin.substring(0, 1).toUpperCase();
        return headerWord;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isForbidSpeakFlag() {
        return forbidSpeakFlag;
    }

    public void setForbidSpeakFlag(boolean forbidSpeakFlag) {
        this.forbidSpeakFlag = forbidSpeakFlag;
    }

    public String getIsForbidAdd() {
        return isForbidAdd;
    }

    public void setIsForbidAdd(String isForbidAdd) {
        this.isForbidAdd = isForbidAdd;
    }

    public int getIsEncryptGroup() {
        return isEncryptGroup;
    }

    public void setIsEncryptGroup(int isEncryptGroup) {
        this.isEncryptGroup = isEncryptGroup;
    }

    public int getIsEncrypt() {
        return isEncryptGroup;
    }

    public void setIsEncrypt(int isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String isInviteSwitch() {
        return inviteSwitch;
    }

    public void setInviteSwitch(String inviteSwitch) {
        this.inviteSwitch = inviteSwitch;
    }

    public int getSearCount() {
        return searCount;
    }

    public void setSearCount(int searCount) {
        this.searCount = searCount;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getRoomId() {
        if(roomId==null){
            roomId="1";
        }
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
        if (!TextUtils.isEmpty(shieldFlag))
            return shieldFlag;
        else
            return "false";
    }

    public void setShieldFlag(String shieldFlag) {
        this.shieldFlag = shieldFlag;
    }

    public void replaceSave() {
        if(roomId!=null){
            saveOrUpdate("roomId=?", roomId);
        }
    }

    public GroupInfo findFirstData() {
        return where("roomId=?", roomId).findFirst(GroupInfo.class);
    }

    @Override
    public String getFieldIndexBy() {
        return name;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        pinyin=indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }
}
