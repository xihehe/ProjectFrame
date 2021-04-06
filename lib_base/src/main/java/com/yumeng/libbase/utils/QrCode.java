package com.yumeng.libbase.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.yumeng.libcommon.listener.ListenableFuture;
import com.yumeng.libcommon.listener.SettableFuture;
import com.yumeng.libcommon.utils.ThreadUtil;


import java.io.File;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;

public class QrCode {

    public static final String TAG = QrCode.class.getSimpleName();

    public static @NonNull
    Bitmap create(String data) {
        try {
            BitMatrix result = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, 512, 512);
            Bitmap bitmap = Bitmap.createBitmap(result.getWidth(), result.getHeight(), Bitmap.Config.ARGB_8888);

            for (int y = 0; y < result.getHeight(); y++) {
                for (int x = 0; x < result.getWidth(); x++) {
                    if (result.get(x, y)) {
                        bitmap.setPixel(x, y, Color.BLACK);
                    }
                }
            }

            return bitmap;
        } catch (WriterException e) {
            Log.w(TAG, e);
            return Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
        }
    }

    public static ListenableFuture<String> decodeQRCode(String filePath) {
        SettableFuture future =new SettableFuture<String>();
        if (TextUtils.isEmpty(filePath)) {
            return future;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return future;
        }
        ThreadUtil.executeSubThread(new Runnable() {
            @Override
            public void run() {
                Bitmap obmp = BGAQRCodeUtil.getDecodeAbleBitmap(filePath);
                if(obmp==null){
                    return;
                }
                int width = obmp.getWidth();
                int height = obmp.getHeight();
                int[] data = new int[width * height];
                obmp.getPixels(data, 0, width, 0, 0, width, height);
                RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
                BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
                QRCodeReader reader = new QRCodeReader();
                Result re = null;
                try {
                    re = reader.decode(bitmap1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(re!=null){
//                    byte[] rawBytes = re.getRawBytes();
//                    String s1 = bytes2HexString(rawBytes);
//                    try {
//                        String s = new String(rawBytes, "ISO-8859-1");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                    String text = re.getText();
                    future.set(re.getText());
                }
            }
        });
        return future;
    }


    /*
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] array) {
        StringBuilder builder = new StringBuilder();

        for (byte b : array) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            builder.append(hex);
        }

        return builder.toString().toUpperCase();
    }



}
