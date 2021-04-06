package com.yumeng.libbaseProject.service.ResponseModel;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yumeng.libbase.helper.DataHelper;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//多布局item bean
public class TestResponse implements MultiItemEntity, Serializable {
    public static final int ITEM_INFO_CENTER_PIC_NEWS = 0;  //推荐 中间一个大图
    public static final int ITEM_INFO_PIC_RIGHT_NEWS = 1;  //推荐 文字加右边一个小图
    public static final int ITEM_INFO_THREE_PICS_NEWS = 2; //推荐 3个小图加文字
    public static final int ITEM_INFO_CENTER_VIDEO_NEWS = 3; //视频
    public static final int ITEM_INFO_FOCUS_CENTER_VIDEO_NEWS = 4; //关心 中间一个大图
    public static final int ITEM_INFO_FOCUS_PIC_RIGHT_NEWS = 5; //关心 文字加右边一个小图
    public static final int ITEM_INFO_FOCUS_THREE_PICS_NEWS = 6; //关心 3个小图加文字

    private String id; //文章id
    private String authId; //作者id
    private String title; //文章标题
    private String type;  //文章类型0表示图文，1表示音视频
    private String comments; //评论数
    private String collections; //收藏数
    private String shares; //分享数
    private String video; //音视频
    private String coverimg; //封面图
    private String userId;  //关注者id (待删除)
    private String authName;  //作者名称
    private long createDate;  //创建日期
    private boolean hadFocus; //是否关注
    private String avatar; //
    private boolean hadUpvote;//是否点赞
    private String upvote;//点赞数量
    private String content;
    private List<String> coverimgList; //
    private Boolean isTop = false;

    private boolean catalogFollow = false;//后续可以请求服务端关注和推荐|其他栏目接口新增该类似字段，以免需要一次for循环

    public boolean isCatalogFollow() {
        return catalogFollow;
    }

    public void setCatalogFollow(boolean catalogFollow) {
        this.catalogFollow = catalogFollow;
    }

    @Override
    public int hashCode() {
        int result = 1;
        if (TextUtils.isEmpty(id)) {
            result = 31 * result + authId.hashCode();
        } else {
            result = 31 * result + id.hashCode();
        }
        result = 31 * result + Long.valueOf(createDate).hashCode();
        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof TestResponse)) {
            return false;
        }
        TestResponse other = (TestResponse) obj;
        if (TextUtils.isEmpty(id)) {
            return authId.equals(other.getAuthId()) && createDate == other.createDate;
        } else {
            return id.equals(other.getId()) && createDate == other.createDate;
        }
    }

    @Override
    public String toString() {
        return "InformationMultiItemBean{" +
                ", id='" + id + '\'' +
                ", authId='" + authId + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                ", collections='" + collections + '\'' +
                ", shares='" + shares + '\'' +
                ", video='" + video + '\'' +
                ", coverimg='" + coverimg + '\'' +
                ", userId='" + userId + '\'' +
                ", authName='" + authName + '\'' +
                ", createDate=" + createDate +
                ", hadFocus=" + hadFocus +
                ", avatar='" + avatar + '\'' +
                ", coverimgList=" + coverimgList +
                ", upvote=" + upvote +
                ", hadUpvote=" + hadUpvote +
                '}';
    }

    @Override
    public int getItemType() {
        return 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComments() {
        if (TextUtils.isEmpty(comments)) {
            return "0";
        }
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCollections() {
        return collections;
    }

    public void setCollections(String collections) {
        this.collections = collections;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public boolean isHadFocus() {
        return hadFocus;
    }

    public void setHadFocus(boolean hadFocus) {
        this.hadFocus = hadFocus;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getCoverImgList() {
        if (coverimgList == null) {
            if (coverimg != null) {
                coverimgList = JSON.parseArray(coverimg, String.class);
            } else {
                coverimgList = new ArrayList<>();
            }
        }
        return coverimgList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHadUpvote() {
        return hadUpvote;
    }

    public void setHadUpvote(boolean hadUpvote) {
        this.hadUpvote = hadUpvote;
    }

    public String getUpvote() {
        if (upvote == null) {
            return "0";
        }
        return upvote;
    }

    public boolean isVideo() {
        return type != null && !type.equals("0");
    }

    public void setUpvote(String upvote) {
        this.upvote = upvote;
    }

    public void setCoverimgList(List<String> coverimgList) {
        this.coverimgList = coverimgList;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }
}
