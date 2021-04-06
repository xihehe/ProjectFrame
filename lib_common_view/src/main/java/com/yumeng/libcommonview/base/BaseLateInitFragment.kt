package com.yumeng.libcommonview.base

import android.os.Bundle
import com.yumeng.libcommon.event.MessageEvent
import com.yumeng.libcommonview.fragment.LateInitFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

open class BaseLateInitFragment : LateInitFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    open fun onMessageEvent(event: MessageEvent) {

    }

}