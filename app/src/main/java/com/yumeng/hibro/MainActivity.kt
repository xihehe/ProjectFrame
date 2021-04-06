package com.yumeng.hibro

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.yumeng.hibro.model.TestEventModelp
import com.yumeng.hibro.model.TestEventModels
import com.yumeng.hibro.viewModel.MainViewModel
import com.yumeng.libbase.activity.EditContentActivity
import com.yumeng.libbase.activity.matisse.matisse.GlideEngine
import com.yumeng.libbase.activity.matisse.matisse.Matisse
import com.yumeng.libbase.activity.matisse.matisse.MimeType
import com.yumeng.libbase.activity.telphone.TelephoneCodeActivity
import com.yumeng.libbase.helper.DialogHelper
import com.yumeng.libbaseProject.activity.BaseImVMActivity
import com.yumeng.libbaseProject.eventBus.MessageChatEvent
import com.yumeng.libcommon.event.MessageEvent
import com.yumeng.libcommon.ext.doAsync
import com.yumeng.libcommon.ext.putData
import com.yumeng.libcommon.ext.startKtxActivity
import com.yumeng.libcommon.ext.startKtxActivityForResult
import com.yumeng.libcommon.helper.Preference
import com.yumeng.libcommon.rxManager.RxPermissions
import com.yumeng.libcommon.utils.LogUtils
import com.yumeng.tillo.QRCodeScanActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseImVMActivity<MainViewModel>() {
    companion object {
        private const val REQUEST_CODE_CHOOSE = 23
        private const val BASE_REQUEST_CODE = 24
    }

    override fun providerVMClass(): Class<MainViewModel>? = MainViewModel::class.java

    override fun getLayoutResId() = R.layout.activity_main
    override fun initData() {
        mViewModel.getTest()
    }

    override fun initView() {
        getNet.setOnClickListener {
            mViewModel.getTest()
        }
        scan.setOnClickListener {
            RxPermissions(this).request(Manifest.permission.CAMERA)
                .subscribe { aBoolean ->
                    if (aBoolean) {
                        QRCodeScanActivity.start(this, true)
                    }
                }
        }
        pick.setOnClickListener {
            RxPermissions(this).request(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                ?.subscribe { aBoolean ->
                    //权限已经开启   enableCrop:是否裁剪
                    if (aBoolean == true) {

                        Matisse.from(this)
                            .choose(MimeType.ofAll(), false)
                            .countable(false)
                            .maxOriginalSize(10 * 1024 * 1024)//最大设置10M每张
                            .originalEnable(true)
                            .theme(R.style.Matisse_Zhihu)
                            .maxSelectable(9)
                            .imageEngine(GlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE)
                    }
                }
        }
        pickDialog.setOnClickListener {
            DialogHelper.showPhotoDialog(this, object : DialogHelper.OnPhotoCallback {
                override fun onSuccess(path: String) {
                    LogUtils.d("test", "path:$path")
                }

                override fun onFailure() {
                }
            })
        }
        phonePick.setOnClickListener {
            startKtxActivityForResult<TelephoneCodeActivity>(BASE_REQUEST_CODE)
        }
        editContent.setOnClickListener {
            startKtxActivityForResult<EditContentActivity>(extra = Bundle().apply {
                putString(EditContentActivity.TITLE_KEY, "ceshi")
                putString(EditContentActivity.RESULT_KEY, "ceshi1")
            }, requestCode = BASE_REQUEST_CODE)
        }

        saveShareData.setOnClickListener {
            //第一种方式
            Preference<String>().putValue(Preference.USER_JSON, "555555")
            //第二种方式
            var userId by Preference(Preference.USER_ID, "")
            userId = "22"
            //第三种方式
            Preference("").putValue(Preference.TOKEN, "12344444")
            //第四种
            1123123.putData("tttt")

        }
        getShareData.setOnClickListener {
            val userInfo by Preference(Preference.USER_JSON, "")
            val userId by Preference(Preference.USER_ID, "")
            val token by Preference(Preference.TOKEN, "")
            val tttt by Preference("tttt", 0)
            val tttt2 = Preference<Long>().getValue("tttt", 0)

            LogUtils.e("test", "userInfo :$userInfo")
            LogUtils.e("test", "userId :$userId")
            LogUtils.e("test", "token :$token")
            LogUtils.e("test", "tttt :$tttt")
            LogUtils.e("test", "tttt2 :$tttt2")
        }

        sendEvent.setOnClickListener {
            EventBus.getDefault().post(MessageChatEvent("111111","22222").apply {
                putValue("a","aaaaa")
                putValue("b","bbbbb")
                putValue("c",TestEventModels("lili","25"))
//                putValue("d", TestEventModelp("bibi","26"))
            })
        }

        jumpSecond.setOnClickListener {
            startKtxActivity<TestSecondActivity>()
        }
        jumpThree.setOnClickListener {
            startKtxActivity<TestthreeActivity>()
        }
    }


    override fun startObserve() {
        super.startObserve()
        mViewModel.uiState.observe(this, Observer {
            it.showError?.let { error ->
                LogUtils.e("test", "showError :$error")
            }
            it.showLoading?.let { loading ->
                LogUtils.e("test", "showError :$loading")
            }
            it.showSuccess?.let { success ->
                LogUtils.e("test", "showError :$success")
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }


    override fun onMessageEvent(event: MessageEvent) {
        super.onMessageEvent(event)
        val message = event as MessageChatEvent

            val content = "msg:${message.target}  ${message.behavior} ${message.getData(
                "a",
                ""
            )}  ${message.getData("b", "")}"

        val content2 = "name:${message.getData("c",TestEventModels()).name}  age: ${message.getData("c",TestEventModels()).age}"
//        val content3 = "name:${message.getData("d",TestEventModelp()).nameP}  age: ${message.getData("d",TestEventModelp()).ageP}"

        LogUtils.e("test", "showEvent :$content  $content2  ")//$content3

    }

}