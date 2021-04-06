package com.yumeng.libcommon.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Intent.startUrl(context: Context, url:String){
    this.action= Intent.ACTION_VIEW
    val parse=Uri.parse(url)
    this.data=parse
    context.startActivity(this)
}


/**
 * Created by Chauncey on 2018/2/12 09:03.
 *
 */
fun <T : Activity> Context.startActivity(clazz: Class<T>) {
    this.startActivity(Intent(this, clazz))
}

fun <T : Activity> Fragment.startActivity(clazz: Class<T>) {
    this.startActivity(Intent(this.context, clazz))
}

fun <T : Activity> Activity.startActivityForResult(clazz: Class<T>, requestCode: Int) {
    this.startActivityForResult(Intent(this, clazz), requestCode)
}

fun <T : Activity> Fragment.startActivityForResult(clazz: Class<T>, requestCode: Int) {
    this.startActivityForResult(Intent(this.context, clazz), requestCode)
}