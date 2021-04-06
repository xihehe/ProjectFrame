package com.yumeng.libcommon.helper

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.yumeng.libcommon.R
import com.yumeng.libcommon.utils.LogUtils
import java.io.File

/**
 * 图片辅助类
 */

object PictureHelper {
    fun loadImageFromPath(path: String?, imageView: ImageView, @DrawableRes placeholderRes: Int = 0) {
//        try {
//            imageView.scaleType = ImageView.ScaleType.FIT_XY
//        } catch (e: IllegalArgumentException) {
//            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        }
        if (imageView.context != null)
            Glide.with(imageView.context).load(path).skipMemoryCache(false).apply(RequestOptions().placeholder(placeholderRes)).into(imageView)
    }


    fun loadImageFromPath(path: String?, imageView: ImageView) {
        if (imageView.context != null) {
            Glide.with(imageView.context).load(path).skipMemoryCache(false)/*.thumbnail(0.2F)*/.apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA)
            )
                    .into(imageView)
        }
    }

    fun loadFromPath(path: String?, imageView: ImageView) {
        if (imageView.context != null) {
            Glide.with(imageView.context).load(path).skipMemoryCache(false).apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))
                    .into(imageView)
        }
    }

    fun loadImageFromPath(path: String?, imageView: ImageView, drawable: Drawable? = null) {
        try {
            imageView.scaleType = ImageView.ScaleType.FIT_XY
        } catch (e: IllegalArgumentException) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        Glide.with(imageView.context).load(path).skipMemoryCache(false).apply(RequestOptions().placeholder(drawable)).into(imageView)
    }

    fun loadImageFromPath(path: Int?, imageView: ImageView, @DrawableRes placeholderRes: Int = 0) {
        try {
            imageView.scaleType = ImageView.ScaleType.FIT_XY
        } catch (e: IllegalArgumentException) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        Glide.with(imageView.context).load(path).skipMemoryCache(false).apply(RequestOptions().placeholder(placeholderRes)).into(imageView)
    }


    fun loadImageAvatar(path: String?, imageView: ImageView) {
        try {
            imageView.scaleType = ImageView.ScaleType.FIT_XY
        } catch (e: IllegalArgumentException) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        if (imageView.context == null) {
            LogUtils.i("loadImageAvatar", "Picture loading failed,context is null")
            return
        }
        Glide.with(imageView.context).load(path ?: "")
//                .thumbnail(0.2f)
                .skipMemoryCache(false)
                .dontAnimate()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.mipmap.ic_avatar).error(R.mipmap.ic_avatar))
                .into(imageView)
    }

    fun loadImageGroupAvatar(path: String?, imageView: ImageView) {
        try {
            imageView.scaleType = ImageView.ScaleType.FIT_XY
        } catch (e: IllegalArgumentException) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        if (imageView.context == null) {
            LogUtils.i("loadImageAvatar", "Picture loading failed,context is null");
            return
        }
        Glide.with(imageView.context).load(path ?: "")
                .thumbnail(0.2f)
                .skipMemoryCache(false)
                .apply(RequestOptions()/*.transform(GlideCircleTransform(imageView.context))*/.diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.mipmap.create_group).error(R.mipmap.create_group))
                .into(imageView)
    }

    fun loadImageGroupAvatar(path: String?, imageView: ImageView, @DrawableRes placeholderRes: Int = R.mipmap.create_group) {
        try {
            imageView.scaleType = ImageView.ScaleType.FIT_XY
        } catch (e: IllegalArgumentException) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        if (imageView.context == null) {
            LogUtils.i("loadImageAvatar", "Picture loading failed,context is null");
            return
        }
        Glide.with(imageView.context).load(path ?: "")
                .thumbnail(0.2f)
                .skipMemoryCache(false)
                .apply(RequestOptions()/*.transform(GlideCircleTransform(imageView.context))*/.diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(placeholderRes).error(placeholderRes))
                .into(imageView)
    }

    fun loadImageAvatar(path: Int?, imageView: ImageView) {
        try {
            imageView.scaleType = ImageView.ScaleType.FIT_XY
        } catch (e: IllegalArgumentException) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        if (imageView.context == null) {
            LogUtils.i("loadImageAvatar", "Picture loading failed,context is null")
            return
        }
        Glide.with(imageView.context).load(path)
                .thumbnail(0.2f)
                .skipMemoryCache(false)
                .apply(RequestOptions()/*.transform(GlideCircleTransform(imageView.context))*/.diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.mipmap.ic_avatar).error(R.mipmap.ic_avatar))
                .into(imageView)
    }

    //资讯那边的图片
    fun loadImagePic(path: String?, imageView: ImageView, @DrawableRes placeholderRes: Int = 0) {
        if (imageView.context == null) {
            LogUtils.i("loadImageAvatar", "Picture loading failed,context is null")
            return
        }
        Glide.with(imageView.context).load(path ?: "")
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(placeholderRes).error(placeholderRes).transform(RoundedCorners(
                    DensityHelper.dp2px(imageView.context, 5f))))
                .into(imageView)
    }

    fun loadVideo(path: String?, imageView: ImageView, @DrawableRes placeholderRes: Int = 0) {
        if (imageView.context == null) {
            LogUtils.i("loadImageAvatar", "Picture loading failed,context is null")
            return
        }
        Glide.with(imageView.context).load(path ?: "")
                .dontAnimate()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(placeholderRes).error(placeholderRes))
                .into(imageView)
    }

    fun loadImagePic(path: String?, imageView: ImageView) {
        if (imageView.context == null) {
            LogUtils.i("loadImageAvatar", "Picture loading failed,context is null")
            return
        }
        Glide.with(imageView.context).load(path ?: "")
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.color.colorGray).error(R.color.colorGray).transform(RoundedCorners(
                    DensityHelper.dp2px(imageView.context, 5f))))
                .into(imageView)
    }


    fun loadImageFromPath(
            path: String?,
            imageView: ImageView, @DrawableRes placeholderRes: Int = 0,
            roundedRadius: Float
    ) {
        if (imageView.context == null) {
            LogUtils.i("loadImageAvatar", "Picture loading failed,context is null")
            return
        }
        Glide.with(imageView.context).load(path).apply(
                RequestOptions().placeholder(placeholderRes).transform(
                        RoundedCorners(
                                DensityHelper.dp2px(
                                        imageView.context,
                                        roundedRadius
                                )
                        )
                )
        )
                .into(imageView)
    }

    fun loadImageFromPath(
            path: Int?,
            imageView: ImageView, @DrawableRes placeholderRes: Int = 0,
            roundedRadius: Float
    ) {
        if (imageView.context == null) {
            LogUtils.i("loadImageAvatar", "Picture loading failed,context is null")
            return
        }
        Glide.with(imageView.context).load(path).apply(
                RequestOptions().placeholder(placeholderRes).transform(
                        RoundedCorners(
                                DensityHelper.dp2px(
                                        imageView.context,
                                        roundedRadius
                                )
                        )
                )
        )
                .into(imageView)
    }

    fun loadImageFromPath(
            path: String?,
            imageView: ImageView,
            onFailed: () -> Unit,
            callback: (Bitmap) -> Unit
    ) {
        Glide.with(imageView)
            .asBitmap()
            .load(path)
            .into(object : CustomViewTarget<ImageView, Bitmap>(imageView) {
                override fun onStart() {
                    super.onStart()
                }

                override fun onResourceLoading(placeholder: Drawable?) {
                    super.onResourceLoading(placeholder)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    onFailed()
                }

                override fun onResourceCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                ) {
                    callback(resource)
                }

            })
    }


    fun loadImageFromPath(path: String?, imageView: ImageView, width: Int, height: Int, placeholder: Int = R.color.colorGray2) {
        if (imageView.context == null) {
            return
        }
        Glide.with(imageView.context).load(path).apply(RequestOptions().placeholder(placeholder).override(width, height).centerCrop()).into(imageView)
    }

    fun loadImageFromPath(
            path: String?,
            imageView: ImageView,
            width: Int,
            height: Int,
            onFailed: () -> Unit,
            callback: (Bitmap) -> Unit,
            failUrl:String?=null
    ) {
        Glide.with(imageView)
                .asBitmap()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.colorGray2).override(width, height).centerCrop())
                .load(path)
                .error(Glide.with(imageView).asBitmap().load(failUrl))
                .into(object : CustomViewTarget<ImageView, Bitmap>(imageView) {
                    override fun onStart() {
                        super.onStart()
                    }

                    override fun onResourceLoading(placeholder: Drawable?) {
                        super.onResourceLoading(placeholder)
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        onFailed()
                    }

                    override fun onResourceCleared(placeholder: Drawable?) {

                    }

                    override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                    ) {
                        callback(resource)
                    }


                })
    }

    fun loadImageGifFromPath(
            path: String?,
            imageView: ImageView,
            width: Int,
            height: Int,
            onStart: () -> Unit,
            callback: (GifDrawable) -> Unit,
            failUrl:String?=null
    ) {
        Glide.with(imageView)
                .asGif()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.colorGray2).override(width, height).centerCrop())
                .error(Glide.with(imageView).asGif().load(failUrl))
                .listener(object : RequestListener<GifDrawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<GifDrawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: GifDrawable, model: Any?, target: Target<GifDrawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        callback(resource)
                        return false
                    }

                })
                .load(path)
                .into(imageView)
    }

    fun loadImageGifFromPath(
            path: String?,
            imageView: ImageView,
            onStart: () -> Unit,
            callback: (GifDrawable) -> Unit,
            failUrl:String?=null
    ) {
        Glide.with(imageView)
                .asGif()
                .error(Glide.with(imageView).asGif().load(failUrl))
                .listener(object : RequestListener<GifDrawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<GifDrawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: GifDrawable, model: Any?, target: Target<GifDrawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        callback(resource)
                        return false
                    }

                })
                .load(path)
                .into(imageView)
    }

    fun loadRoundCornerImageFromFile(path: String?, imageView: ImageView) {
        Glide.with(imageView.context)
                .load(File(path))
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.drawable.shape_imageview_roundedcorners).error(R.drawable.shape_imageview_roundedcorners).transform(RoundedCorners(
                    DensityHelper.dp2px(imageView.context, 5f))))
                .into(imageView)
    }

    fun loadImageFromFile(file: File, imageView: ImageView) {
        Glide.with(imageView.context).load(file).into(imageView)
    }

}
