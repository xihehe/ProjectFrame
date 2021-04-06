package com.yumeng.libcommon.utils.contact

import android.Manifest
import android.annotation.SuppressLint
import android.content.AsyncQueryHandler
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract
import android.text.format.Formatter
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.github.stuxuhai.jpinyin.PinyinFormat
import com.github.stuxuhai.jpinyin.PinyinHelper
import com.yumeng.libcommon.helper.BitmapHelper

import com.yumeng.libcommon.rxManager.RxPermissions

import java.util.ArrayList
import java.util.HashMap
import kotlin.Comparator

object ContactsUtils {

    /**
     * 联系人信息
     */
    @SuppressLint("CheckResult")
    fun getContactsInfo(activity: FragmentActivity, callback: Callback) {

        RxPermissions(activity).request(Manifest.permission.READ_CONTACTS)
            .subscribe { b ->

                if (b) {
                    callback.onStart()
                    //这里需要指定需要查询的类型,不然会报-1 游标没有初始化
                    val cols = arrayOf(
                        ContactsContract.PhoneLookup.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.PHOTO_ID
                    )
                    MyAsyncQueryHandler(
                        activity,
                        callback
                    ).startQuery(0, null, ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols, null, null, null)
                }
            }
    }


    class MyAsyncQueryHandler(private val activity: FragmentActivity,private val callback: ContactsUtils.Callback):AsyncQueryHandler(activity.contentResolver){
        override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
            super.onQueryComplete(token, cookie, cursor)
            val result = ArrayList<MobileContact>()
            cursor?.let {
                for (i in 0 until cursor.count) {
                    it.moveToPosition(i)
                    // 取得联系人名字,电话,拼音,首字母
                    val contactsName =
                        it.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)
                    val contactsPhone =
                        it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val contactIdIndex =
                        it.getColumnIndex(ContactsContract.Contacts._ID)//获取 id 所在列的索引

                    val name = it.getString(contactsName)
                    val phoneNumber = it.getString(contactsPhone)
                    val contactId = it.getString(contactIdIndex)//联系人id

                    val pinYin = PinyinHelper.convertToPinyinString(
                        name,
                        "",
                        PinyinFormat.WITHOUT_TONE
                    )
                    val headerWord = pinYin.substring(0, 1)

                    val person = MobileContact(
                        name, phoneNumber, contactId, pinYin, headerWord,
                        getPhotoByte(
                            activity,
                            contactId
                        )
                            ?: BitmapHelper.getBitmapFromDrawable(
                                activity,
                                AvatarHelper.getAvatarImageRes(name)
                            )
                    )

                    result.run {
                        sortWith(Comparator { lhs, rhs ->
                            //根据拼音进行排序
                            lhs.headerWord.compareTo(rhs.pinYin)
                        })

                        add(person)
                    }

                }
                activity.runOnUiThread {
                    callback.onSuccess(result)
                    callback.onCompleted()
                }
            }
        }
    }

    /**
     * 联系人头像缓存
     */
    fun getPhotoByte(context: Context, contactId: String): Bitmap? {
        val photoMap = HashMap<String, ByteArray>()
        var bytes: ByteArray? = photoMap[contactId]
        if (bytes == null || bytes.isEmpty()) {
            val dataCursor = context.contentResolver.query(
                ContactsContract.Data.CONTENT_URI,
                arrayOf("data15"),
                ContactsContract.Data.CONTACT_ID + "=?" + " AND "
                        + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE + "'",
                arrayOf(contactId), null
            )
            if (dataCursor != null) {
                if (dataCursor.count > 0) {
                    dataCursor.moveToFirst()
                    bytes = dataCursor.getBlob(dataCursor.getColumnIndex("data15"))
                    photoMap[contactId] = bytes
                    Log.i("数据大小:", Formatter.formatFileSize(context, bytes!!.size.toLong()))
                }
                dataCursor.close()
            }
        } else {
            Log.i("缓存数据大小:", Formatter.formatFileSize(context, bytes.size.toLong()))
        }

        return if (bytes?.size != null) {
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        } else {
            null
        }

    }

    interface Callback {

        fun onStart()

        fun onSuccess(result: ArrayList<MobileContact>)

        fun onCompleted()
    }

}