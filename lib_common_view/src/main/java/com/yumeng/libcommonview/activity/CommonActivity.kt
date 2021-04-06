package com.yumeng.libcommonview.activity

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.ColorInt
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.yumeng.libcommon.event.MessageEvent

import com.yumeng.libcommon.helper.SoftInputHelper
import com.yumeng.libcommon.helper.StatusBarHelper
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

open abstract class CommonActivity : AppCompatActivity() {

    protected abstract fun initView()

    open fun isNormal(): Boolean {
        return true
    }

    protected fun isImmersionBar(): Boolean {
        return true
    }

    open fun isPortrait(): Boolean {
        return true
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        if (!isNormal()) {
            if (isImmersionBar()) {
                initImmersionBar()
            }
        }
        if (isPortrait())
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//设置为竖屏

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: MessageEvent) {

    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()

            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    fun initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).statusBarColorInt(Color.WHITE).fitsSystemWindows(true).init()
    }

    protected open fun setStatusBarColor(@ColorInt color: Int) {
        StatusBarHelper.setWindowStatusBarColor(this, color)
    }

    protected open fun setStatusBarBlackTextMode(b: Boolean) {
        StatusBarHelper.setStatusBarBlackTextMode(this, b)
    }

    override fun finish() {
        super.finish()
        SoftInputHelper.closeSoftKeyboard(this)
    }
}