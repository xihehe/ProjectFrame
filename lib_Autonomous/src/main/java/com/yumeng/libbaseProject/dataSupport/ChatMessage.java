package com.yumeng.libbaseProject.dataSupport;


import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.yumeng.libbaseProject.model.MyMemberBean;
import com.yumeng.libbaseProject.type.SystemPushStatus;


import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;
import org.whispersystems.libsignal.SessionCipher;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.Serializable;
import java.util.List;

import kotlin.jvm.Transient;

import static org.litepal.LitePal.where;

/**
 * 聊天消息 实体类
 */

public class ChatMessage extends LitePalSupport implements Serializable, Cloneable {
    @SerializedName("messageId")
    @Column(unique = true)
    private String messageId;
    private String content;
    @Column(nullable = false)
    private String roomId;
    private long timestamp;
    private String type;
    private String local_path;
    private String sourceId;
    private String duration;
    @Column(ignore = true)
    private boolean isUpOrDownLoad = false;
    private String image_path;//大图
    private int sendState = 1;//0、发送中 1、发送成功 2、发送失败 3接收方开始下载
    private String backId;//标识消息
    @SerializedName("fileName")
    private String fileName;
    private boolean isRead;
    @Column(ignore = true)
    private boolean isDisplayTime = false;
    @Column(ignore = true)
    private String descrpiton;
    @Column(ignore = true)
    private boolean playing;
    private String sender;
    private String receiver;
    private String operType;
    private List<Long> userIds;
    private String users;
    private String groupName;
    private String ownerId;
    private List<MyMemberBean> members;
    private Object operInfo;//添加成员|二维码分享者
    private String fileSize;
    private String locationInfo;
    private String userName;//转让群|扫码加入人
    @Column(ignore = true)
    private boolean isContain = true;
    private String measureInfo;
    private long withdrawDate;
    @Column(ignore = true)
    private int month;
    @Column(ignore = true)
    private int day;
    @Column(ignore = true)
    private String title;
    private int cryptoType;//加密消息类型
    private String remoteIdentityKey;//接收者身份密钥
    private int isCryptoMessage;//是否是加密消息0 非加密 1加密
    @Column(defaultValue = "0", nullable = false)
    private String rtcRead;//0rtc已读 1未读
    @Transient
    @Column(ignore = true)
    private SessionCipher sessionCipher;
    @Column(defaultValue = "0", nullable = false)
    private String isDelete = "0";
    @Column(defaultValue = "0", nullable = false)
    private String unReadMessage;//0读过 1未读
    @Column(ignore = true)
    private boolean isOriginal;//是否是原图|视频类型下是否是视频
    private String fileKey;//加密文件密钥
    private String groupCryptoReceiver;
    @Column(defaultValue = "0", nullable = false)
    private String offlineMsg = "0";//离线1mpush0
    private int downloadState;//普通图片下载原图使用
    @Column(ignore = true) //当前消息播放消息
    private int audioState = 3;
    private int isMsgAck;
    private String systemDescription;
    @Column(ignore = true)
    private boolean noSend;//不发送
    @Column(ignore = true)
    private String route;
    @Transient
    @Column(ignore = true)
    private int index;//消息在adapter中的位置
    @Column(ignore = true)
    private String mobile;
    //这几个在临时会话有用到
    @Column(ignore = true)
    private String avatar;
    @Column(ignore = true)
    private String sex;
    @Column(ignore = true)
    private String uid;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRtcRead() {
        return rtcRead;
    }

    public void setRtcRead(String rtcRead) {
        this.rtcRead = rtcRead;
    }

    private String userId;//适配IOS新增RTC通话列表的字段,代表永远是对方的ID

    public boolean isNoSend() {
        return noSend;
    }

    public void setNoSend(boolean noSend) {
        this.noSend = noSend;
    }

    @Column(ignore = true)
    private boolean isSelect = false; //通话列表多选用的
    @Column(defaultValue = "0", nullable = false)
    private String rtcDelFlag = "0";//音视频消息逻辑删除
    @Column(defaultValue = "-1", nullable = false)
    private String rtcStatus = "-1"; //RtcMessageStatus    默认-1 ，写给方法把旧数据的值改正常了 //和ios的一致
    @Column(ignore = true)
    private int sessionType;
    @Column(ignore = true)
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSessionType() {
        return sessionType;
    }

    public void setSessionType(int sessionType) {
        this.sessionType = sessionType;
    }

    public String isRtcDelFlag() {
        return rtcDelFlag;
    }

    public String getRtcStatus() {
        return rtcStatus;
    }

    public void setRtcStatus(String rtcStatus) {
        this.rtcStatus = rtcStatus;
    }

    public void setRtcDelFlag(String rtcDelFlag) {
        this.rtcDelFlag = rtcDelFlag;
    }

    public String getSystemDescription() {
        return systemDescription;
    }

    public void setSystemDescription(String systemDescription) {
        this.systemDescription = systemDescription;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public boolean isFileHelper() {
        if (TextUtils.isEmpty(route)) {
            return false;
        } else {
            return route.equals(SystemPushStatus.fileHelper.type);
        }
    }

    public boolean isOtherType() {
        return isFileHelper() || isSystemInform();
    }



    public boolean isSystemInform() {
        if (TextUtils.isEmpty(route)) {
            return false;
        } else {
            return route.equals(SystemPushStatus.systemInform.type);
        }
    }

    public int getIsMsgAck() {
        return isMsgAck;
    }

    public void setIsMsgAck(int isMsgAck) {
        this.isMsgAck = isMsgAck;
    }

    public int getAudioState() {
        return audioState == 0 ? 3 : audioState;
    }

    public void setAudioState(int audioState) {
        this.audioState = audioState;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDownloadState() {
        return downloadState;
    }

    public void setDownloadState(int downloadState) {
        this.downloadState = downloadState;
    }

    public String getOfflineMsg() {
        return offlineMsg;
    }

    public void setOfflineMsg(String offlineMsg) {
        this.offlineMsg = offlineMsg;
    }

    @Column(ignore = true)
    private String chatType;

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getGroupCryptoReceiver() {
        return groupCryptoReceiver;
    }

    public void setGroupCryptoReceiver(String groupCryptoReceiver) {
        this.groupCryptoReceiver = groupCryptoReceiver;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public void setOriginal(boolean original) {
        isOriginal = original;
    }

    public String getUnReadMessage() {
        return unReadMessage;
    }

    public void setUnReadMessage(String unReadMessage) {
        this.unReadMessage = unReadMessage;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public SessionCipher getSessionCipher() {
        return sessionCipher;
    }

    public void setSessionCipher(SessionCipher sessionCipher) {
        this.sessionCipher = sessionCipher;
    }

    public int getCryptoType() {
        return cryptoType;
    }

    public void setCryptoType(int cryptoType) {
        this.cryptoType = cryptoType;
    }

    public String getRemoteIdentityKey() {
        return remoteIdentityKey;
    }

    public void setRemoteIdentityKey(String remoteIdentityKey) {
        this.remoteIdentityKey = remoteIdentityKey;
    }

    public boolean isCrypto() {
        return isCryptoMessage == 1;
    }

    public boolean isCryptoGroup() {
        return !TextUtils.isEmpty(chatType) && chatType.equals("groupEncrypt");
    }

    public boolean isGroupChat() {
        return TextUtils.isEmpty(receiver);
    }

    public int getIsCryptoMessage() {
        return isCryptoMessage;
    }

    public void setIsCryptoMessage(int isCryptoMessage) {
        this.isCryptoMessage = isCryptoMessage;
    }

    public String getMeasureInfo() {
        return measureInfo;
    }

    public void setMeasureInfo(String measureInfo) {
        this.measureInfo = measureInfo;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    private List<User> atModelList;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getDescrpiton() {
        return descrpiton;
    }

    public void setDescrpiton(String descrpiton) {
        this.descrpiton = descrpiton;
    }

    public boolean isDisplayTime() {
        return isDisplayTime;
    }

    public void setDisplayTime(boolean displayTime) {
        isDisplayTime = displayTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public boolean isUpOrDownLoad() {
        return isUpOrDownLoad;
    }

    public void setUpOrDownLoad(boolean upOrDownLoad) {
        boolean old = this.isUpOrDownLoad;
        isUpOrDownLoad = upOrDownLoad;
        changeSupport.firePropertyChange("upOrDownLoad", old, this.isUpOrDownLoad);
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getRoomid() {
        return roomId;
    }

    public void setRoomid(String roomId) {
        this.roomId = roomId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocal_path() {
        return local_path;
    }

    public boolean localFileExists() {
        if (local_path != null) {
            return new File(local_path).exists();
        } else {
            return false;
        }
    }

    public boolean localImageExists() {
        if (image_path != null) {
            return new File(image_path).exists();
        } else {
            return false;
        }
    }

    public void setLocal_path(String local_path) {
        this.local_path = local_path;
    }


    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getBackId() {
        if (TextUtils.isEmpty(backId)) {
            return "";
        }
        return backId;
    }

    public void setBackId(String backId) {
        this.backId = backId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PropertyChangeSupport getChangeSupport() {
        return changeSupport;
    }

    public void setChangeSupport(PropertyChangeSupport changeSupport) {
        this.changeSupport = changeSupport;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        boolean old = this.playing;
        this.playing = playing;
        changeSupport.firePropertyChange("playing", old, this.playing);
    }

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(
            this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName,
                                          PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public String messageToString() {
        return "messageId" + messageId + "sender" + sender + "roomId" + roomId + "receiver" + receiver;
    }

    public List<MyMemberBean> getMembers() {
        return members;
    }

    public void setMembers(List<MyMemberBean> members) {
        this.members = members;
    }

    public Object getOperInfo() {
        return operInfo;
    }

    public void setOperInfo(Object operInfo) {
        this.operInfo = operInfo;
    }

    public List<User> getAtModelList() {
        return atModelList;
    }

    public void setAtModelList(List<User> atModelList) {
        this.atModelList = atModelList;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public boolean isContain() {
        return isContain;
    }

    public void setContain(boolean contain) {
        isContain = contain;
    }

    public long getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(long withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public void replaceSave() {
        if (TextUtils.isEmpty(messageId)) {
            save();
        } else {
            saveOrUpdate("messageId=?", messageId);
        }
    }

    public ChatMessage findFirstData() {
        return where("messageId=? and isDelete=?", messageId, String.valueOf(0)).findFirst(ChatMessage.class);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public int hashCode() {
        int result = 1;
        if (!TextUtils.isEmpty(messageId)) {
            result = 31 * result + messageId.hashCode();
        } else {
            result = 31 * result + backId.hashCode();
        }
        result = 31 * result + Long.valueOf(timestamp).hashCode();
        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ChatMessage)) {
            return false;
        }
        ChatMessage other = (ChatMessage) obj;
        if (messageId == null) {
            return backId.equals(other.backId) && timestamp == other.timestamp;
        } else {
            return messageId.equals(other.getMessageId()) && timestamp == other.timestamp;
        }
    }


    @Override
    public String toString() {
        return "ChatMessage{" +
                "messageId='" + messageId + '\'' +
                ", content='" + content + '\'' +
                ", roomId='" + roomId + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", local_path='" + local_path + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", duration='" + duration + '\'' +
                ", isUpOrDownLoad=" + isUpOrDownLoad +
                ", image_path='" + image_path + '\'' +
                ", sendState=" + sendState +
                ", backId='" + backId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", isRead=" + isRead +
                ", isDisplayTime=" + isDisplayTime +
                ", descrpiton='" + descrpiton + '\'' +
                ", playing=" + playing +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", operType='" + operType + '\'' +
                ", userIds=" + userIds +
                ", users='" + users + '\'' +
                ", groupName='" + groupName + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", members=" + members +
                ", operInfo=" + operInfo +
                ", fileSize='" + fileSize + '\'' +
                ", locationInfo='" + locationInfo + '\'' +
                ", userName='" + userName + '\'' +
                ", isContain=" + isContain +
                ", measureInfo='" + measureInfo + '\'' +
                ", withdrawDate=" + withdrawDate +
                ", month=" + month +
                ", day=" + day +
                ", title='" + title + '\'' +
                ", cryptoType=" + cryptoType +
                ", remoteIdentityKey='" + remoteIdentityKey + '\'' +
                ", isCryptoMessage=" + isCryptoMessage +
                ", sessionCipher=" + sessionCipher +
                ", isDelete='" + isDelete + '\'' +
                ", unReadMessage='" + unReadMessage + '\'' +
                ", isOriginal=" + isOriginal +
                ", fileKey='" + fileKey + '\'' +
                ", groupCryptoReceiver='" + groupCryptoReceiver + '\'' +
                ", offlineMsg='" + offlineMsg + '\'' +
                ", downloadState=" + downloadState +
                ", audioState=" + audioState +
                ", isMsgAck=" + isMsgAck +
                ", systemDescription='" + systemDescription + '\'' +
                ", chatType='" + chatType + '\'' +
                ", atModelList=" + atModelList +
                ", changeSupport=" + changeSupport +
                ", rtcStatus=" + rtcStatus +
                '}';
    }
}
