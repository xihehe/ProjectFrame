package com.yumeng.libbaseProject.activity

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yumeng.libbase.helper.DialogHelper
import com.yumeng.libbaseProject.Contants.Constants
import com.yumeng.libbaseProject.R
import com.yumeng.libbaseProject.eventBus.MessageChatEvent
import com.yumeng.libcommon.helper.Preference
import com.yumeng.libcommon.utils.OSUtils
import com.yumeng.libcommonview.activity.CommonToolbarActivity
import com.yumeng.libcommonview.base.BaseVMActivity
import com.yumeng.libcommonview.viewmodel.BaseViewModel
import org.greenrobot.eventbus.EventBus
//
abstract class BaseImVMActivity<VM : BaseViewModel> : BaseVMActivity<VM>() {


    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OSUtils.isFloatPermission(this)
    }



    override fun onPause() {
        super.onPause()
        if (alertDialog != null && alertDialog!!.isShowing) {
            alertDialog!!.dismiss()
        }
    }
    override fun onStart() {
        super.onStart()
        //TODO
//        AccountHelper.reconnection()
//        CommonInterface.checkInitiating(this, AppSharePre.getPersonalInfo(), false, null)
    }

    override fun onResume() {
        super.onResume()
        var isFloating by Preference(Constants.HOME_FLOAT_PERMISSION,false)
        if (isFloating) {
            isFloating = false
            if (OSUtils.isFloatPermission(this)) {
                EventBus.getDefault().post(MessageChatEvent(Constants.TARGET_VIDEO_ACTIVITY, Constants.MESSAGE_EVENT_START_FLOATING_WINDOW))
            } else {
                showPermissionDialog()
            }
        }
    }

    private fun showPermissionDialog() {
        //TODO
//        alertDialog = DialogHelper.showSimpleDialog(this, getString(R.string.you_cant_minimize_a_video_call_as_authorized), getString(R.string.enable), DialogInterface.OnClickListener{ dialog, which -> startActivityForResult(
//            Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")), Constants.HOME_FLOAT_PERMISSION_CODE) })
//        alertDialog?.show()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val isHome = intent.getBooleanExtra(Constants.HOME_FLOAT_PERMISSION, false)
        if (isHome) {
            showPermissionDialog()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.HOME_FLOAT_PERMISSION_CODE) {
            if (OSUtils.isFloatPermission(this)) {
                EventBus.getDefault().post(MessageChatEvent(Constants.TARGET_VIDEO_ACTIVITY, Constants.MESSAGE_EVENT_START_FLOATING_WINDOW))
            }
        }
    }

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}