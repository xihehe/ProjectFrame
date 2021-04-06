package com.yumeng.tillo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import cn.bingoogolapple.qrcode.core.QRCodeView
import cn.bingoogolapple.qrcode.zxing.ZXingView
import com.yalantis.ucrop.util.FileUtils
import com.yumeng.libbase.R

import com.yumeng.libbase.utils.PicCrop
import com.yumeng.libcommon.ext.startUrl
import com.yumeng.libcommon.utils.LogUtils
import com.yumeng.libcommon.utils.ToastUtils
import com.yumeng.libcommonview.activity.CommonToolbarActivity
import com.yumeng.libcommonview.theme.Theme


class QRCodeScanActivity : CommonToolbarActivity(), QRCodeView.Delegate {

    companion object {
        private const val VISIBILITY_ALBUM = "visibility_album"
        private const val RESULT_KEY = "result"
        fun initIntent(context: Context, isAlbum: Boolean = false): Intent {
            return Intent(context, QRCodeScanActivity::class.java).putExtra(VISIBILITY_ALBUM, isAlbum)
        }

        const val QR_CODE_REQUEST_CODE = 2002

        fun start(context: Fragment, isAlbum: Boolean = false) {
            context.startActivityForResult(Intent(context.context, QRCodeScanActivity::class.java).putExtra(VISIBILITY_ALBUM, isAlbum), QR_CODE_REQUEST_CODE)
        }
        fun start(context: Activity, isAlbum: Boolean = false) {
            context.startActivityForResult(Intent(context, QRCodeScanActivity::class.java).putExtra(VISIBILITY_ALBUM, isAlbum), QR_CODE_REQUEST_CODE)
        }

        fun getResult(intent: Intent): String {
            return intent.getStringExtra(RESULT_KEY)
        }
    }

    private var isFlashLight = false
    private var isDark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Theme.themePosition == 1) {
            setContentView(R.layout.activity_qr_code_san)
        } else {
            setContentView(R.layout.activity_qr_code_san)
        }
        title = getString(R.string.scan_it)
        initZXing()
        val isAlbum = intent.getBooleanExtra(VISIBILITY_ALBUM, false)
        if (isAlbum)
            setRightButtonText(getString(R.string.album))
        getTextRightView()?.setOnClickListener {
            PicCrop.cropAvatarFromGallery(this)
        }
    }

    private var mZXingView: ZXingView? = null
    private var tvLightTip: TextView? = null

    private fun initZXing() {
        mZXingView = findViewById(R.id.mZXingView)
        tvLightTip = findViewById(R.id.tvLightTip)
        mZXingView?.setDelegate(this)

        mZXingView?.closeFlashlight()
        tvLightTip?.setOnClickListener {
            if (tvLightTip?.visibility == View.VISIBLE) {
                if (!isFlashLight) {
                    isFlashLight = true
                    mZXingView?.openFlashlight()
                } else {
                    isFlashLight = false
                    mZXingView?.closeFlashlight()
                    if (!isDark) {
                        tvLightTip?.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PicCrop.REQUEST_SELECT_PICTURE) {
                val selectedUri = data?.data
                val path = FileUtils.getPath(this, selectedUri)
                if (!path.isNullOrEmpty()) {
                    mZXingView?.decodeQRCode(path)
                    showLoadingPromptView()
                }
            }
        }
    }




    override fun onStart() {
        super.onStart()
        mZXingView?.startCamera()
        mZXingView?.startSpotAndShowRect()
    }

    override fun onStop() {
        mZXingView?.stopCamera() // 关闭摄像头预览，并且隐藏扫描框
        super.onStop()
    }

    override fun onDestroy() {
        mZXingView?.onDestroy() // 销毁二维码扫描控件
        super.onDestroy()
    }

    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= 26) {
            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(VibrationEffect.createOneShot(200, 10));
        } else {
            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(200)
        }
    }


    override fun onScanQRCodeSuccess(result: String?) {
        vibrate()
        mZXingView?.stopSpot()//停止识别
        dismissPromptView()
        LogUtils.e("onScanQRCodeSuccess", result)
        try {
            if (result?.startsWith("http") == true) {
                Intent().startUrl(this, result)
                return
            }
            if (result.isNullOrEmpty()) {
                ToastUtils.getInstance().shortToast(getString(R.string.no_qr_code_detected))
                finish()
            } else {
//                Router.decodeQRCodeStart(this,result)
                setResult(Activity.RESULT_OK, Intent().putExtra(RESULT_KEY, result))
                finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            startSpot()
            ToastUtils.getInstance().shortToast(getString(R.string.not_recognized))
        }
    }

    private fun startSpot() {
        mZXingView?.postDelayed(
                {
                    mZXingView?.startSpot()
                }, 500
        )
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {
        this.isDark = isDark
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        if (isDark) {
            tvLightTip?.visibility = View.VISIBLE
        } else {
            if (!isFlashLight) {
                tvLightTip?.visibility = View.GONE
            }
        }
    }

    override fun onScanQRCodeOpenCameraError() {
        dismissPromptView()
    }

}