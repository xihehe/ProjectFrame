package com.yumeng.libcommon.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.PhoneNumberUtils
import androidx.fragment.app.FragmentActivity
import com.yumeng.libcommon.rxManager.RxPermissions
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by Chauncey on 2019/1/29 14:27.
 * 调用系统的发短信 以及  打电话
 */
object PhoneUtils {

    /**
     * @param phoneNumberList
     * @param message
     */
    fun startSMSActivity(context: Context, phoneNumberList: ArrayList<String>, message: String?) {
        val phoneNumber = StringBuilder()
        phoneNumberList.forEach {
            if (PhoneNumberUtils.isGlobalPhoneNumber(it)) {
                phoneNumber.append(",$it")
            }
        }
        phoneNumber.removePrefix(",").let {
            if (it.isNotBlank()) {
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$it"))
                intent.putExtra("sms_body", message)
                context.startActivity(intent)
            }
        }
    }

    fun call(activity: FragmentActivity, phoneNumber: String) {
        RxPermissions(activity)
            .request(Manifest.permission.CALL_PHONE)
            .subscribe(object : Observer<Boolean> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Boolean) {
                    if (t) {
                        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
                        activity.startActivity(intent)
                    }
                }

                override fun onError(e: Throwable) {
                }
            })

    }
}