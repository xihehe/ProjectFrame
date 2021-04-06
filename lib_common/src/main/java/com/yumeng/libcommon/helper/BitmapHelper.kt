package com.yumeng.libcommon.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.VectorDrawable
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.yumeng.libcommon.utils.ByteUtils
import com.yumeng.libcommon.utils.FileUtil

import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Created by Chauncey on 2017/9/20 15:30.
 * Bitmap辅助类
 */

object BitmapHelper {
    fun fromPath(path: String?): Bitmap? {
        return if (path == null) null else BitmapFactory.decodeFile(path)
    }

    fun fromUri(context: Context, uri: Uri) =
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)


    private fun fromFile(path: String): Bitmap? {
        return if (TextUtils.isEmpty(path)) null else BitmapFactory.decodeFile(path)
    }

    private fun fromFile(path: String, options: BitmapFactory.Options): Bitmap? {
        return if (TextUtils.isEmpty(path)) null else BitmapFactory.decodeFile(path, options)

    }

    fun fromResource(context: Context, resId: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, resId)
    }

    fun getBitmapFromDrawable(context: Context, @DrawableRes drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableId)

        return if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else if (drawable is VectorDrawable || drawable is VectorDrawableCompat || drawable is GradientDrawable) {
            getBitmapFromVectorDrawableRes(context, drawableId)
        } else {
            throw IllegalArgumentException("unsupported drawable type")
        }
    }

    private fun fromFileLessThanSize(path: String, size: Int): Bitmap? {
        val opt = BitmapFactory.Options()

        val bitmapSize = getBitmapSize(path)
        if (bitmapSize < size) {
            return fromFile(path)
        }

        var inSampleSize = bitmapSize / size

        var pow = 1

        while (inSampleSize.apply { inSampleSize /= 2 } >= 2) {

            pow++
        }

        opt.inSampleSize = Math.pow(2.0, pow.toDouble()).toInt()

        opt.inJustDecodeBounds = false

        return fromFile(path, opt)
    }

    private fun getBitmapSize(path: String): Int {
        val opt = BitmapFactory.Options()
        opt.inJustDecodeBounds = true
        fromFile(path, opt)
        return Math.max(opt.outWidth, opt.outHeight)
    }

    fun getBitmapSize(bitmap: Bitmap): Int {
        return Math.max(bitmap.width, bitmap.height)
    }

    fun createBitmapFile(bitmap: Bitmap?, path: String, recycle: Boolean = true, quality: Int = 100): File? {

        if (bitmap == null) {
            return null
        }

        var file: File? = File(path)
        var newFileB = false
        if (file?.exists() == true) {
            file.delete()
        }

        try {
            newFileB = file?.createNewFile() ?: false
        } catch (e: IOException) {
            e.printStackTrace()
        }


        if (newFileB) {
            val stream: FileOutputStream
            try {
                stream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
                stream.flush()
                stream.close()
            } catch (e: IOException) {
                e.printStackTrace()
                file = null
            }

            if (recycle)
                bitmap.recycle()
        } else {
            file = null
        }

        return file
    }

    private fun getBitmapFileLessThanSize(context: Context, file: File, size: Int): File? {
        val bitmap = fromFileLessThanSize(file.absolutePath, size)
        val dir = context.externalCacheDir ?: return null
        val newFilePath =
            StringBuilder(
                dir.absolutePath + File.separator + FileUtil.getPictureNameFromPath(
                    file.absolutePath
                )
            )
        newFilePath.insert(newFilePath.lastIndexOf("."), System.currentTimeMillis())

        return createBitmapFile(bitmap, newFilePath.toString())
    }

    fun getBitmapFileLessThanSizeFromUri(context: Context, uri: Uri, size: Int): File? {
        val file = FileUtil.getFileFromUri(context, uri)
        return getBitmapFileLessThanSize(context, file, size)
    }

    fun getBitmapFileLessThanSizeFromPath(context: Context, path: String, size: Int): File? {
        val file = FileUtil.getFileFromPath(path)
        return getBitmapFileLessThanSize(context, file, size)
    }

    fun getBitmapFromVectorDrawableRes(context: Context, drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        //        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        //            drawable = DrawableCompat.wrap(drawable).mutate()
        //        }

        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    fun getBitmapByteFromDrawableRes(
        context: Context,
        drawableId: Int,
        size: Int = 150
    ): ByteArray {
        val bmp = fromResource(context, drawableId)
        val thumbBmp = Bitmap.createScaledBitmap(bmp, size, size, true)
        bmp.recycle()
        return ByteUtils.bmpToByteArray(thumbBmp, true)
    }

    fun readPictureDegree(path: String): Int {
        var degree = 0
        try {
            val exifInterface = ExifInterface(path)
            val orientation = exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return degree
    }

    fun rotateBitmap(angle: Int, bitmap: Bitmap): Bitmap? {
        var returnBm: Bitmap? = null
        // 根据旋转角度，生成旋转矩阵
        val matrix = Matrix()
        matrix.postRotate(angle.toFloat())
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
        }

        if (returnBm == null) {
            returnBm = bitmap
        }
        if (bitmap != returnBm) {
            bitmap.recycle()
        }
        return returnBm
    }

    fun getRotatedBitmap(path: String) = BitmapHelper.rotateBitmap(
        BitmapHelper.readPictureDegree(
            path
        ), BitmapFactory.decodeFile(path)
    )

}
