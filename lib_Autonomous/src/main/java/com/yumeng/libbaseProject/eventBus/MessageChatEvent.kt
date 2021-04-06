package com.yumeng.libbaseProject.eventBus

import com.yumeng.libcommon.event.MessageEvent

//如果需要 自定义一些自己的参数 可以这么写
//但是 在 onMessageEvent  一定要把  MessageEvent 转成 自定义的Model
// val message = event as MessageChatEvent
class MessageChatEvent : MessageEvent {
    var target: String? = null//接收对象
    var behavior: String? = null//行为

    var includeMe: Boolean? = false
    var leaveUser: Long? = 0
    var groupName: String? = null
    var roomId: String? = null
    var num: Int? = 0//离线消息数

    constructor(target: String?, behavior: String?) {
        this.target = target
        this.behavior = behavior
    }

    constructor(target: String?, behavior: String?, includeMe: Boolean?) {
        this.target = target
        this.behavior = behavior
        this.includeMe = includeMe
    }

    constructor(target: String?, behavior: String?, leaveUser: Long?) {
        this.target = target
        this.behavior = behavior
        this.leaveUser = leaveUser
    }

    constructor(target: String?, behavior: String?, groupName: String?, roomId: String?) {
        this.target = target
        this.behavior = behavior
        this.groupName = groupName
        this.roomId = roomId
    }

    constructor(target: String?, behavior: String?, num: Int?, roomId: String?) {
        this.target = target
        this.behavior = behavior
        this.roomId = roomId
        this.num = num
    }

}