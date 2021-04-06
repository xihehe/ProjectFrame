package com.yumeng.libbaseProject.type;

/**
 * @author zfm
 * @Description TODO
 * @date 2019/1/16
 */
public enum SystemPushStatus {
    createGroup("create","创建群聊"),
    updateGroupName("update","更新群昵称"),
    deleteGroupMember("delete","删除群成员"),
    transferGroup("transfer","转让群"),
    leaveGroup("leave","退群"),
    addGroupMember("add","添加群成员"),
    scanQrcodejoin("scanJoin","扫码进群"),
    openInviteSwitch("openInviteSwitch","群聊邀请确认开启"),
    closeInviteSwitch("closeInviteSwitch","群聊邀请确认关闭"),
    crypto("crypto","端对端加密"),
    invite("invite","邀请系统消息"),
    addAdmin("addAdmin", "添加管理员"),
    removeAdmin("removeAdmin", "移除管理员"),
    openGroupForbidSpeak("openGroupForbidSpeak", "群禁言开启"),
    closeGroupForbidSpeak("closeGroupForbidSpeak", "群禁言关闭"),
    openUserForbidSpeak("openUserForbidSpeak", "个人禁言开启"),
    closeUserForbidSpeak("closeUserForbidSpeak", "个人禁言关闭"),
    shot("shot", "截屏消息"),
    dissolved("dissolved", "解散群"),
    Band("band", "封禁"),
    isForbidAdd("isForbidAdd", "是否禁止添加好友"),
    autoPassFriend("autoPassFriend", "直接验证通过好友请求"),
    syncMySend("syncMySend", "web发送的自己的消息"),
    fileHelper("fileHelper", "web发送的传输助手"),
    systemInform("systemInform", "系统通知"),
    ;

    public String type;
    public String name;

    SystemPushStatus(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
