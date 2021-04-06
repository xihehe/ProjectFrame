package com.yumeng.libbaseProject.Contants;

import android.Manifest;


import com.yumeng.libbaseProject.BuildConfig;
import com.yumeng.libcommon.utils.FileUtil;
import com.yumeng.libcommon.utils.FileUtils;

import java.io.File;

/**
 * Created by Administrator on 2018/3/21.
 */

public class Constants {
    public static final String FileUrl = BuildConfig.FileUrl;//文件正式
    public static final String BaseUrl = BuildConfig.BASE_URL;//基本正式
    public static final String GroupUrl = BuildConfig.GroupUrl;//群聊正式
    public static final String FriendUrl = BuildConfig.FriendUrl;//好友正式
    public static final String RTCUrl = BuildConfig.RTCUrl;//rtc push正式
    public static final String SOCKETUrl = BuildConfig.SOCKETUrl;//socket正式
    public static final String ChatUrl = BuildConfig.ChatUrl;//单聊正式
    public static final String MpushUrl = BuildConfig.MpushUrl;//mpush正式
    public static final String SINGLE_URL = BuildConfig.SINGLE_URL;
    public static final String GROUP_URL = BuildConfig.GROUP_URL;
    public static final String COMMON_SOCKET_URL = BuildConfig.COMMON_SOCKET_URL;
    public static final String FeedUrl = BuildConfig.FEED; // 动态
    public static final String APP_FIRST_IN = "app_first_inf";
    public static final String WEB_LOGIN = "web_login";
    public static final String APP_USER_INFO = "app_user_info";
    public static final String APP_USER_PUSH_TOKEN = "app_user_push_token";
    public static final String APPNAME = "Hipro";

    public static final String CHANNEL_ID = "hyphenate_chatuidemo_notification";
    public static final String MUTE_CHANNEL_ID = "mute_yumeng_notification";
    public static final String VIDEO_CHANNEL = "video_notification";
    public static final String CONTACT_JSON = "contact_json";
    public static final String TREND_JSON = "trend_json";
    public static final String PHOTO_JSON = "photo_json";
    //    http://180.178.124.2:3333
    public static final String ROOM_ID_LIST = "chatUserBean";
    //EventBus 数据分发 标识
    public static final String MESSAGE_EVENT_UPDATE_HEAD = "updateHead";//更新好友列表
    public static final String MESSAGE_EVENT_HAS_MESSAGE = "updateMessage";//收到新消息
    public static final String MESSAGE_EVENT_UPDATE_LOGIN_STATUS = "updateWebLoginStatus";//Web status
    public static final String MESSAGE_EVENT_HAS_MESSAGE_LIST = "updateMessageList";//收到新消息
    public static final String MESSAGE_EVENT_GROUP_HAS_MESSAGE = "updateMessage";//群收到新消息
    public static final String MESSAGE_EVENT_OTHER_ALERT_GROUP_NAME = "otherAlertGroupName";//其他人修改群名称
    public static final String MESSAGE_EVENT_UPDATE_GROUP_MANAGER = "updateGroupManager";//更新群管理
    public static final String MESSAGE_EVENT_DELETE_MEMBER = "deleteMember";//删除群成员
    public static final String MESSAGE_EVENT_UPDATE_MEMBER = "updateMember";//更新群成员
    public static final String MESSAGE_EVENT_UPDATE_SILENCED = "updateSilenced";//更新禁言状态
    public static final String MESSAGE_EVENT_ADD_NEW_MEMBER = "addNewMember";//添加新成员
    public static final String MESSAGE_EVENT_MEMBER_LEAVE = "memberLeave";//群成员离开
    public static final String MESSAGE_EVENT_FRIEND_AGREE = "agreeWithYou";//更新好友列表
    public static final String MESSAGE_EVENT_NEW_FRIEND_REQUEST = "newFriendRequest";//新的好友请求
    public static final String MESSAGE_EVENT_FRIEND_DOT_HIDE = "friendDotHide";//红点消失
    public static final String MESSAGE_EVENT_UPDATE_GROUP = "updateGroupList";//更新群列表
    public static final String MESSAGE_EVENT_UPDATE_GROUP_SESSION = "updateGroupSession";//更新群会话列表
    public static final String MESSAGE_EVENT_INIT_CONVERSATION = "updateConversationList";//更新会话列表
    public static final String MESSAGE_EVENT_UPDATE_PUSH_MESSAGE_STATUS = "toastSendMessage";//更新发消息状态
    public static final String MESSAGE_EVENT_UPDATE_OFFLINE_MESSAGE = "updateSingleOfflineMessage";//更新离线消息
    public static final String MESSAGE_EVENT_UPDATE_GROUP_OFFLINE_MESSAGE = "updateGroupOfflineMessage";//更新群离线消息
    public static final String MESSAGE_EVENT_ALERT_GROUP_NAME = "alertGroupName";
    public static final String MESSAGE_EVENT_UPDATE_CHAT_MESSAGE = "updateChatMessage";
    public static final String MESSAGE_EVENT_CLEAR_OFFLINE = "clearOffline";//清除未读消息数
    public static final String MESSAGE_EVENT_CLEAR_RECODER = "clearRecoder";//清除聊天记录
    public static final String MESSAGE_EVENT_CLEAR_GROUP_RECODER = "clearGroupRecoder";//清除群记录
    public static final String MESSAGE_EVENT_DISBAND_GROUP = "disband_group";//解散群
    public static final String MESSAGE_EVENT_JOIN_VOICE = "joinVoice";//对方同意加入房间
    public static final String MESSAGE_EVENT_RECONNECT = "reconnect";//重连
    public static final String MESSAGE_EVENT_FINISH_VOICE = "finishVoice";//对方拒绝加入房间
    public static final String MESSAGE_EVENT_FINISH_RTC = "finishVoiceRTC";//RTC情况下的单点登录
    public static final String MESSAGE_EVENT_START_FLOATING_WINDOW = "startFloatingWindow";//启动悬浮窗
    public static final String MESSAGE_EVENT_CANCEL_VOICE = "cancelFinishVoice";//对方拒绝加入房间
    public static final String MESSAGE_EVENT_BUSY_VOICE = "busyFinishVoice";//对方繁忙
    public static final String MESSAGE_UPDATE_FRIEND_NAME = "updateFriendName";//更新好友备注
    public static final String MESSAGE_UPDATE_CONVERSATION = "updateConversation";//更新会话
    public static final String MESSAGE_UPDATE_TMP_CHAT_BOX = "updateConversation";//更新会话
    public static final String MESSAGE_UPDATE_CONVERSATION_REFRESH = "updateConversationRefresh";//更新会话是否开启刷新
    public static final String MESSAGE_DELETE_CONVERSATION = "deleteConversation";//更新会话
    public static final String MESSAGE_DOWNLOAD_COMPLETE = "download_complete";//下载完成
    public static final String MESSAGE_DOWNLOAD_PROGRESS = "download_progress";//下载进度
    public static final String MESSAGE_DOWNLOAD_COMPLETE_FAVORITE = "download_complete_favorite";//下载完成
    public static final String MESSAGE_DOWNLOAD_FAIL = "download_fail";//下载失败
    public static final String MESSAGE_DISCONNECT_ERROR = "disconnectError";//未连接到socket
    public static final String MESSAGE_CONNECT_SUCCESS = "connectSuccess";//连接成功
    public static final String MESSAGE_CONNECTING = "connecting";//连接中
    public static final String MESSAGE_EVENT_GROUP_INVITE = "groupInvite";//邀请入群
    public static final String MESSAGE_UPDATE_OFFLINE_MESSAGE = "updateOfflineMessage";//更新离线消息
    public static final String MESSAGE_UPDATE_VIDEO_FAIL = "update_video_fail";//视频下载失败
    public static final String MESSAGE_START_REFRESH = "messageStartRefresh";//开始刷新
    public static final String MESSAGE_FINISH_REFRESH = "messageEndRefresh";//结束刷新
    public static final String MESSAGE_GET_ROOM_ID = "getRoomId";  //获取roomId
    public static final String MESSAGE_EVENT_RECOMMEND_LIST = "recommendList";//推荐联系人列表
    public static final String MESSAGE_EVENT_RECOMMEND_UPLOAD_SUCCESS = "recommendUpLoadSuccess";//本地联系人上传成功
    public static final String MESSAGE_EVENT_UPDATE_TMP_BOX = "deleteTmpListItem";//添加好友后要删除对应临时消息列表
    public static final String UPDATE_EVENT_UPDATE_CALL_LIST = "updateCallList";//添加好友后要删除对应临时消息列表
    public static final String MESSAGE_SCARN_FRIEND = "messageScarnFriend";//扫码好友
    public static final String MESSAGE_EVENT_UPDATE_NET_STATUS = "netStatus";//网络状态
    public static final String MESSAGE_EVENT_NEW_TRENDS = "messageEventNewTrends";//新的动态推送
    public static final String TARGET_CHAT_FRAGMENT = "chatFragment";
    public static final String TARGET_TMP_CHAT_BOX_ACTIVITY = "TmpChatBoxActivity";
    public static final String TARGET_INDEX_ACTIVITY = "indexActivity";
    public static final String TARGET_VERIFY_INFO_ACTIVITY = "VerifyInfoActivity";
    public static final String TARGET_GROUP_FRAGMENT = "groupFragment";
    public static final String UPLOAD_TAG = "UPLOAD_TAG";
    public static final String DOWNLOAD_TAG = "DOWNLOAD_TAG";
    public static final String TARGET_CHAT_ACTIVITY = "chatActivity";
    public static final String TARGET_UPDATE_FRIEND = "friendActivity";
    public static final String TARGET_VIDEO_ACTIVITY = "videoActivity";
    public static final String TARGET_GROUP_CHAT_ACTIVITY = "GroupChatActivity";
    public static final String TARGET_VIDEO_VIEW_ACTIVITY = "VideoViewActivity";
    public static final String TARGET_FAVORITE_IMAGE_ACTIVITY = "FavoriteImgActivity";
    public static final String TARGET_FAVORITE_VIDEO_ACTIVITY = "FavoriteVideoActivity";
    public static final String TARGET_FILE_OPEN_ACTIVITY = "FileOpenActivity";
    public static final String TARGET_GROUP_SETTING_ACTIVITY = "GroupSettingActivity";
    public static final String TARGET_MAIN_ACTIVITY = "mainActivity";
    public static final String TARGET_FORWARD_ACTIVITY = "forwardActivity";
    public static final String TARGET_POST_TREND_ACTIVITY = "PostTrendsActivity";
    public static final String TARGET_PREVIEW_TRENDS_IMAGE_ACTIVITY = "PreviewTrendsImageActivity";
    public static final String TARGET_TRENDS_REPLY_INPUT_FRAGMENT = "trendsReplyInputFragment";
    public static final String TARGET_TRENDS_MORE_ACTION_FRAGMENT = "trendsMoreActionFragment";
    public static final String TARGET_TRENDS_TIME_LINE_FRAGMENT = "trendsTimeLineFragment";
    public static final String TARGET_RECOMMEND_ACTIVITY = "ReCommendActivity";
    public static final String TARGET_TMP_CHAT_BOX = "tmp_chat_box";
    public static final String TARGET_CALLLOG_FRAGMENT = "CALLLOGFragment";

    public static final String[] MANDATORY_PERMISSIONS = {Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};
    public static final String[] AUDIO_PERMISSIONS = {Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};
    public static final String[] WRITE_READ = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final long[] VIBRATION_PATTERN = new long[]{0, 180, 80, 120};
    /**
     * 接口地址
     */
    //获取短信验证码
    public static final String getSMS = "/getSmsCode";

    public static final String rebind = "/rebind";
    //注册
    public static final String register = "/register";
    //注册
    public static final String registerV1217 = "/register-v1217";
    //登陆
    public static final String login = "/login";
    //登陆验证码
    public static final String loginMsgCode = "/getSmsCode";
    //退出登陆
    public static final String logout = "/logout";
    //获取用户信息
    public static final String getUserInfo = "/getUserInfo";
    //修改用户信息
    public static final String alertUserInfo = "/api/user/info";
    //上传头像
    public static final String uploadImage = "/api/user/avatar/upload";
    //忘记密码
    public static final String forgetPassword = "/forget";
    //投诉好友
    public static final String complaintFriend = "/complaint";
    //问题反馈
    public static final String feedback = "/feedback";
    //获取黑名单列表
    public static final String getBlackList = "/blacklist";
    //更新黑名单状态
    public static final String updateBlackList = "/blacklist/status";
    //拉取房间设置列表
    public static final String getRoomSetting = "/room/setting-list";
    //删除好友请求
    public static final String friendRequest = "/friendRequest";
    //拒绝登录
    public static final String qrLoginReject = "/appScanQrLoginReject";
    //扫码成功
    public static final String qrScan = "/qrScanSucceed";
    //扫码登录
    public static final String qrLoginAgree = "/appScanQrLoginAgree";
    //检测网页版用户登录状态
    public static final String checkUserLoginStatus = "/checkWebLoginStatus";
    //web强制下线
    public static final String webLoginExit = "/webLoginExit";
    //屏蔽动态
    public static final String shieldTrends = "/info/shield";
    /**
     * 聊天接口
     */
    public static final String searchUser = "/searchFriend?";
    //修改ID
    public static final String uid = "/uid?";


    public static final String searchFriendV1214 = "/searchFriendV1214";

    public static final String encryptGroupPush = "/encryptGroupPush";

    //解散群
    public static final String dissolved = "/dissolved";
    //发送添加好友请求
    public static final String addFriendRequest = "/addFriend";
    //拉取好友请求列表
    public static final String getFriendRequest = "/searchRequest?";
    //操作好友请求
    public static final String passFriendRequest = "/passFriend";
    //获取用户隐私
    public static final String privacySetting = "/privacy-setting";
    //获取好友列表
    public static final String getFriendList = "/friends";
    //修改好友备注
    public static final String alertRemark = "/updateFriend";
    //下载音频文件
    public static final String downloadFile = "/getFile";
    //修改个人资料
    public static final String alertPersonInfo = "/updateInfo";
    //创建群
    public static final String createGroup = "/createGroup";
    //获取管理员列表
    public static final String managerGroup = "/group-member/admin-list/";
    //获取群成员禁言名单
    public static final String membersMutes = "/group-operate/forbid-list/";
    //添加管理员
    public static final String postManagerGroup = "/group-member/admin-add";
    //群禁言操作
    public static final String postMembersMutes = "/group-operate/forbid-setting";
    //移除管理员
    public static final String deleteManagerGroup = "/group-member/admin-remove";
    //获取成员列表
    public static final String getMemberList = "/getGroupMember";
    //添加群成员
    public static final String addGroupMember = "/addGroupMember";
    //修改群名称
    public static final String alertGroupName = "/updateGroupName";
    //拉取会话列表
    public static final String getChatSessionList = "/getOfflineSession";
    public static final String getOfflineMessageListV129 = "/getOfflineMsgV129";
    //同步自己发送的离线记录
    public static final String appGetMySendOffline = "/appMySendOffline";
    //获取群离线消息
    public static final String getGroupOfflineMessageListV129 = "/pullGroupMsgV129";
    //删除联系人
    public static final String deleteLinkman = "/deleteFriend";
    //删除群成员
    public static final String deleteGroupMember = "/delGroupMember";
    //退群
    public static final String quitGroup = "/exitGroup";
    //保存设备token
    public static final String saveDeviceToken = "/saveDeviceToken";
    //获取群列表
    public static final String getGroupList = "/getGroupList";
    //获取好友信息
    public static final String getFriendInfo = "/friendInfo";
    //房间 置顶、免打扰设置
    public static final String roomSetting = "/room/setting";
    //获取未读好友
    public static final String readFlag = "/request/flag";
    //修改群头像
    public static final String updateGroupAvatar = "/updateGroupAvatar";
    //批量获取群内用户公钥
    public static final String getGroupUserKeys = "/getGroupUserKeys";
    //获取最新版本
    public static final String newVersion = "/newVersion";
    //查看发起人是否还在发起中
    public static final String initiating = "/initiating";
    //RTC push
    public static final String RTC_PUSH = "/push";
    //对方
    public static final String HangUp = "/hang";
    //获取群二维码接口
    public static final String getGroupQRCode = "/group/qrcode";
    //保存密钥接口
    public static final String saveUserPublicKey = "/saveUserPublicKey";
    //补充一次性公钥
    public static final String incrementOneTimeKey = "/incrementOneTimeKey";
    //预发起加密聊天
    public static final String beforeEndToEndChat = "/beforeEndToEndChat";
    //查询一次性公钥的剩余数量
    public static final String getOneTimeKeyCount = "/getOneTimeKeyCount";
    //修改群昵称
    public static final String updateName = "/updateName";
    //群聊邀请开关
    public static final String inviteSwitch = "/group/invite/switch";
    //群主转让
    public static final String transferGroup = "/transferGroup";
    //查看群信息
    public static final String groupInfo = "/groupInfo";
    //通知开关
    public static final String Notice = "/notice";
    //新版本上传单文件
    public static final String uploadSingleFile = "/uploadSingleFile";
    //新版本上传图片
    public static final String uploadImageFile = "/uploadImageFile";
    //新版本上传媒体文件
    public static final String uploadMediaFile = "/uploadMediaFile";
    //新版本下载接口
    public static final String download = "/download";
    //云存储删除接口
    public static final String delete = "/operation/delete";
    //新版本获取自定义图片接口
    public static final String getCustomImage = "/getCustomImage";
    //邀请码隐藏开关
    public static final String inviteCodeSwtich = "/invite-code/hf";
    //邀请码
    public static final String inviteCode = "/invite-code";
    //消息回执
    public static final String msgAck = "/msgAck";
    //消息回执
    public static final String msgAckV129 = "/msgAckV129";
    //我的所有收藏
    public static final String chatFavorite = "/chatFavoriteV1211";
    //设置群不可互加好友
    public static final String settingForbidAdd = "/roomInfo/settingForbidAdd";
    //获取目录文章
    public static final String article = "/article";
    //备份数据上传接口
    public static final String backUp = "/chat-backup";

    //动态相关
    public static final String info = "/info";
    //查询时间轴
    public static final String timeline = "/info/timeline";
    //相册
    public static final String photos = "/info/photos";
    //查询feedinfo
    public static final String detail = "/info/detail";
    //动态可见性设置
    public static final String setPrivate = "/info/visible";
    //点赞
    public static final String like = "/info/like";
    //回复相关
    public static final String comment = "/info/comment";
    //获取置顶
    public static final String commentOverHear = "/info/comment/overheadComment";
    //评论相关
    public static final String commentReply = "/info/comment/reply";
    //获取我的通知列表
    public static final String notification = "/info/notification";
    //清空通知列表
    public static final String notificationClearList = "/info/notification/list";
    //清空通知列表
    public static final String notificationUnread = "/info/notification/unread";
    //清空通知列表
    public static final String publicInfo = "/public/info";

    //验证是否注册过
    public static final String verifyRegister = "/verifyRegister";
    //添加单个通讯录好友
    public static final String addFriendBook = "/addFriendBook";


    public static final String VIDEO_TYPE = "video";
    public static final String AUDIO_TYPE = "audio";
    public static final String LANGUAGE_TYPE = "language";
    public static final String IGNORING_BATTERY = "ignoring_battery";
    public static final String START_PERMISSION = "start_permission";
    public static final String DOWNLOAD_FILE_TAG = "download_file_tag";
    public static final String HEAD_IMAGE_PATH = FileUtils.getSDPath() + File.separator + "Hipro" + File.separator + ".head";
    public static final String FILE_PATH = FileUtils.getSDPath() + File.separator + "Hipro" + File.separator + "download" + File.separator;
    public static final String MEDIA_FILE_PATH = FileUtils.getSDPath() + File.separator + "Hipro" + File.separator + "Media" + File.separator;
    public static final String GLIDE_FILE_PATH = FileUtils.getSDPath() + File.separator + "Hipro" + File.separator + "Media" + File.separator + ".com.yumeng.Hipro" + File.separator;
    public static final String BACK_UP_BASE_PATH = FileUtils.getSDPath() + File.separator + "Hipro" + File.separator + ".BackUp";
    public static final String BACK_UP_FILE_PATH = BACK_UP_BASE_PATH + File.separator + ".backUp" + File.separator;
    public static final String IMAGE_FILE_PATH = MEDIA_FILE_PATH + ".Hipro";
    public static final String IMAGE_FILE_ALBUM_PATH = MEDIA_FILE_PATH + "Hipro";//可见的图片保存地址
    public static final String SENT_IMAGE_FILE_PATH = MEDIA_FILE_PATH + ".Hipro" + File.separator + ".Sent";
    public static final String AUDIO_FILE_PATH = MEDIA_FILE_PATH + ".Hipro Audio";
    public static final String DOCUMENT_FILE_PATH = MEDIA_FILE_PATH + ".Hipro Documents";
    public static final String DOCUMENT_FILE_ALBUM_PATH = MEDIA_FILE_PATH + "Hipro Documents";//可见的文件保存地址
    public static final String SENT_DOCUMENT_FILE_PATH = MEDIA_FILE_PATH + ".Hipro Documents" + File.separator + ".Sent";
    public static final String VIDEO_FILE_PATH = MEDIA_FILE_PATH + ".Hipro Video";
    public static final String VIDEO_FILE_ALBUM_PATH = MEDIA_FILE_PATH + "Hipro Video";//可见的视频保存地址
    public static final String SENT_VIDEO_FILE_PATH = MEDIA_FILE_PATH + ".Hipro Video" + File.separator + ".Sent";
    public static final String CHECK_INIT = "check_init";
    public static final String CHECK_INIT_REGISTER = "first_register";

    public static final long VERIFICATION_DURATION = 60000;
    public static final long VERIFICATION_DURATION_INTERVAL = 1000;
    public static final String SINGLE_CHAT = "single";
    public static final String HISTORY_TYPE = "type";
    public static final String HISTORY_MESSAGE = "message";
    public static final String AUTHORIZATION = "Authorization";
    public static final String HISTORY_KEYWORDS = "keywords";
    public static final String FRIEND_INFO_KEY = "friendInfoList";
    public static final String IS_CLEAR_RECORD = "is_clear_record";
    public static final String MESSAGE_LIST_KEY = "messageList";
    public static final String GROUP_INFO_KEY = "groupInfoList";
    public static final String HOME_FLOAT_PERMISSION = "homeFloatPermission";
    public static final String CHAT_DRAFT = "chatDraft";
    public static final String RESULT_KEY = "result";
    public static final String SINGLE = "singleChat";
    public static final String STRANGER = "stranger";
    public static final String GROUP = "groupChat";
    public static final String ISTMP = "isTmp";
    public static final String QR_CODE_FIRST_GET = "qr_code_first_get";
    public static final String CHAT_DRAFT_LIST = "chatDraftList";
    public static final String ROOM_ID_KEY = "roomId";
    public static final int TOKEN_EXPIRED_STATUS = 401;
    public static final int BANNED_STATUS = -10005;
    public static final int HOME_FLOAT_PERMISSION_CODE = 1006;
    public static final int OTHER_LOGIN_STATUS = -10000;
    //    public static final String NOTIFICATION_ALL_TOGGLE = "notificationAllToggle_" + AppSharePre.getId();
//    public static final String NOTIFICATION_RTC_TOGGLE = "notificationRtcToggle_" + AppSharePre.getId();
//    public static final String NOTIFICATION_CONVERSATION_TOGGLE = "notificationConversationToggle_" + AppSharePre.getId();
    public static final String PUSH_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/V/CXu9bNjIuOYi9jRw2Jn8x59caK4W0TqB5usmIkN0w9GJlOkjTZl6ED+YycZ/+1yzIqF33ES7QfWiY7PgxJYkETEk5WBzh5mQ6zd1Byw6ctMcZJmCDWkDSLC1Y3fx0EkSRJdDeUyUpSXrw9eC4wj0ryVpSQVW0ovLcYvSqxSQIDAQAB";


    public static final int DELETE_FRIEND_CODE = 3002;//对方删除你好友
    public static final int BLACK_LIST_CODE = -10001;//黑名单
    public static final int FORBID_SPEAK_CODE = -10009;//禁言
    public static final int END_TO_END_CODE = 9998;//端对端加密
    public static final int GROUP_BAN_CODE = 30002;//封禁

    public static final int COMMENT_HAS_DELETE = 7001;//评论已删除
    public static final int COMPLAINT_THREE_TIMES = 3005;//一天投诉不能超过3次

    public static final String POST_TREND = "postTrend";
    public static final String INSERT_TREND = "insertTrend";
    public static final String INSERT_REPLY = "insertReply";
    public static final String INSERT_SUB_REPLY = "insertSubReply";
    public static final String UPDATE_LIKE = "updateLike";
    public static final String UPDATE_COMMENT_NUM = "updateCommentNum";
    public static final String DELETE_TREND = "deleteTrend";
    public static final String UPDATE_PRIVACY_TYPE = "unpdatePrivacyType";
    public static final String TMP_STORAGE = "tmpStorage";

    public static final String REFRESH = "refresh";
    public static final String LOADMORE = "loadMore";

    public static final String RoomId = "roomId";
}
