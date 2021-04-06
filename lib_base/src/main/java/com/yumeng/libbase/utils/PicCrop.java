package com.yumeng.libbase.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;


import com.yumeng.libbase.R;
import com.yumeng.libbase.activity.scan.CustomUCropActivity;
import com.yumeng.libcommon.context.AppContextWrapper;
import com.yumeng.libcommon.utils.LogUtils;
import com.yumeng.libcommonview.theme.Theme;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class PicCrop {
    public static final int REQUEST_SELECT_PICTURE = 0x11;
    private static final int REQUEST_CAMERA = 0x19;

    public static final int TYPE_AVATAR = 1;
    public static final int TYPE_NORMAL = 2;

    public static Uri getUri() {
        return uri;
    }

    private static Uri uri;

    private static CropConfig config = new CropConfig();


    private static Uri buildUri() {
        String crashDir;
        Context context = AppContextWrapper.Companion.getApplicationContext();
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && context.getExternalCacheDir() != null) {
            crashDir = context.getExternalCacheDir().getPath() + File.separator + "crash" + File.separator;
        } else {
            crashDir = context.getCacheDir().getPath() + File.separator + "crash" + File.separator;
        }
        File cacheFolder = new File(crashDir);
        if (!cacheFolder.exists()) {
            try {
                boolean result = cacheFolder.mkdir();
                LogUtils.d("uri", "generateUri " + cacheFolder + " result: " + (result ? "succeeded" : "failed"));
            } catch (Exception e) {
                Log.e("uri", "generateUri failed: " + cacheFolder, e);
            }
        }
        String name = String.format("imagecrop-%d.jpg", System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, context.getPackageName()+".fileprovider", new File(cacheFolder.getAbsoluteFile() + File.separator + name));
        } else
            uri = Uri
                    .fromFile(cacheFolder)
                    .buildUpon()
                    .appendPath(name)
                    .build();
        Log.e("crop", uri.toString());

        return uri;

    }

    public static Uri getImageContentUri() {
        File cacheFolder = new File(Environment.getExternalStorageDirectory() + File.separator + "crop");
        if (!cacheFolder.exists()) {
            try {
                boolean result = cacheFolder.mkdir();
                LogUtils.d("uri", "generateUri " + cacheFolder + " result: " + (result ? "succeeded" : "failed"));
            } catch (Exception e) {
                Log.e("uri", "generateUri failed: " + cacheFolder, e);
            }
        }
        String name = String.format("imagecrop-%d.jpg", System.currentTimeMillis());
        String filePath = cacheFolder.getAbsolutePath() + File.separator + name;
        File imageFile = new File(filePath);
        Cursor cursor = AppContextWrapper.Companion.getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return AppContextWrapper.Companion.getApplicationContext().getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * 创建一条图片地址uri,用于保存拍照后的照片
     *
     * @param context
     * @return 图片的uri
     */
    public static Uri createImagePathUri(final Context context) {
        final Uri[] imageFilePath = {null};

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            imageFilePath[0] = Uri.parse("");
//            RxToast.error("请先获取写入SDCard权限");
        } else {
            String status = Environment.getExternalStorageState();
            SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
            long time = System.currentTimeMillis();
            String imageName = timeFormatter.format(new Date(time));
            // ContentValues是我们希望这条记录被创建时包含的数据信息
            ContentValues values = new ContentValues(3);
            values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
            values.put(MediaStore.Images.Media.DATE_TAKEN, time);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

            if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
                imageFilePath[0] = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                imageFilePath[0] = context.getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, values);
            }
        }
        uri = imageFilePath[0];
        LogUtils.i("", "生成的照片输出路径：" + imageFilePath[0].toString());
        return imageFilePath[0];
    }

    public static void cropAvatarFromGallery(Activity context) {
        cropFromGallery(context, null, TYPE_AVATAR);
    }

    public static void cropAvatarFromCamera(Activity context) {
        cropFromCamera(context, null, TYPE_AVATAR);
    }

    public static void cropAvatarFromGallery(Fragment context) {
        cropFromGallery(context, null, TYPE_AVATAR);
    }

    public static void cropAvatarFromCamera(Fragment context) {
        cropFromCamera(context, null, TYPE_AVATAR);
    }


    public static void cropFromGallery(Activity context, CropConfig config, int type) {
        if (config != null) {
            PicCrop.config = config;//怎么避免前后两次config
        } else {
            PicCrop.config = new CropConfig();
        }

        setType(type);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        context.startActivityForResult(Intent.createChooser(intent, context.getString(R.string.select_image)), REQUEST_SELECT_PICTURE);
    }

    public static void cropFromGallery(Fragment fragment, CropConfig config, int type) {
        if (config != null) {
            PicCrop.config = config;//怎么避免前后两次config
        } else {
            PicCrop.config = new CropConfig();
        }

        setType(type);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        fragment.startActivityForResult(Intent.createChooser(intent, fragment.getString(R.string.select_image)), REQUEST_SELECT_PICTURE);
    }

    private static void setType(int type) {
        if (type == TYPE_AVATAR) {
            config.isOval = true;
            config.aspectRatioX = 1;
            config.aspectRatioY = 1;
            config.hideBottomControls = true;
            config.showGridLine = false;
            config.showOutLine = false;
            config.maxHeight = 400;
            config.maxWidth = 400;
        } else if (type == TYPE_NORMAL) {//什么都不用做


        } else {

        }
    }

    public static void cropFromCamera(Activity context, CropConfig config, int type) {
        if (config != null) {
            PicCrop.config = config;
        } else {
            PicCrop.config = new CropConfig();
        }

        setType(type);
        Uri mDestinationUri = createImagePathUri(context);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mDestinationUri);
        context.startActivityForResult(intent, REQUEST_CAMERA);
    }

    public static void cropFromCamera(Fragment fragment, CropConfig config, int type) {
        if (config != null) {
            PicCrop.config = config;
        } else {
            PicCrop.config = new CropConfig();
        }

        setType(type);
        Uri mDestinationUri = createImagePathUri(fragment.getContext());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mDestinationUri);
        fragment.startActivityForResult(intent, REQUEST_CAMERA);
    }

    public static void cropFromGallery(Activity context) {

        cropFromGallery(context, null, 0);
    }

    public static void cropFromCamera(Activity context) {
        cropFromCamera(context, null, 0);
    }

    /**
     * 注意，调用时data为null的判断
     *
     * @param context
     * @param cropHandler
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void onActivityResult(int requestCode, int resultCode, Intent data, Activity context, CropHandler cropHandler) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {//第一次，选择图片后返回
                final Uri selectedUri = data.getData();
                if (selectedUri != null) {
                    startCropActivity(context, data.getData());
                } else {
                    Toast.makeText(context, R.string.unable_to_retrieve_selected_image, Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {//第二次返回，图片已经剪切好

                Uri finalUri = UCrop.getOutput(data);
                cropHandler.handleCropResult(finalUri, config.tag);

            } else if (requestCode == REQUEST_CAMERA) {//第一次，拍照后返回，因为设置了MediaStore.EXTRA_OUTPUT，所以data为null，数据直接就在uri中
                startCropActivity(context, uri);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            cropHandler.handleCropError(data);
        }

    }


    public static void onActivityResult(int requestCode, int resultCode, Intent data, Fragment fragment, CropHandler cropHandler) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {//第一次，选择图片后返回
                final Uri selectedUri = data.getData();
                if (selectedUri != null) {
                    startCropActivity(fragment, data.getData());
                } else {
                    Toast.makeText(fragment.getContext(), R.string.unable_to_retrieve_selected_image, Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {//第二次返回，图片已经剪切好

                Uri finalUri = UCrop.getOutput(data);
                cropHandler.handleCropResult(finalUri, config.tag);

            } else if (requestCode == REQUEST_CAMERA) {//第一次，拍照后返回，因为设置了MediaStore.EXTRA_OUTPUT，所以data为null，数据直接就在uri中
                startCropActivity(fragment, uri);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            cropHandler.handleCropError(data);
        }

    }


    private static void startCropActivity(Activity context, Uri sourceUri) {
        Uri destinationUri = Uri.fromFile(new File(AppContextWrapper.Companion.getApplicationContext().getCacheDir(), System.currentTimeMillis() + ".jpeg"));

        UCrop uCrop = UCrop.of(sourceUri, destinationUri);

        uCrop.withAspectRatio(config.aspectRatioX, config.aspectRatioY);
        uCrop.withMaxResultSize(config.maxWidth, config.maxHeight);

        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        options.setCompressionQuality(config.quality);
//        options.setOvalDimmedLayer(config.isOval);
        options.setCircleDimmedLayer(config.isOval);
        options.setShowCropGrid(config.showGridLine);
        options.setHideBottomControls(config.hideBottomControls);
        options.setShowCropFrame(config.showOutLine);
        if(Theme.Companion.getThemePosition()==0){
            options.setStatusBarColor(config.statusBarColor);
            options.setToolbarColor(config.toolbarColor);
        }else{
            options.setStatusBarColor(config.statusBarColor);
            options.setToolbarColor(config.toolbarColor);
//            options.setStatusBarColor(0xFF007AFF);
//            options.setToolbarColor(0xFF007AFF);
        }
        options.setToolbarTitle(context.getString(R.string.avatar_editing));
        options.setImageToCropBoundsAnimDuration(666);
        uCrop.withOptions(options);
//        Intent intent = uCrop.getIntent(context);
//        intent.setClass(context,CustomUCropActivity.class);
//        context.startActivityForResult(intent,UCrop.REQUEST_CROP);
        uCrop.start(context);
    }


    private static void startCropActivity(Fragment fragment, Uri sourceUri) {
        Uri destinationUri = Uri.fromFile(new File(AppContextWrapper.Companion.getApplicationContext().getCacheDir(), System.currentTimeMillis() + ".jpeg"));

        UCrop uCrop = UCrop.of(sourceUri, destinationUri);

        uCrop.withAspectRatio(config.aspectRatioX, config.aspectRatioY);
        uCrop.withMaxResultSize(config.maxWidth, config.maxHeight);

        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        options.setCompressionQuality(config.quality);
//        options.setOvalDimmedLayer(config.isOval);
        options.setCircleDimmedLayer(config.isOval);
        options.setShowCropGrid(config.showGridLine);
        options.setHideBottomControls(config.hideBottomControls);
        options.setShowCropFrame(config.showOutLine);
        options.setToolbarTitle(AppContextWrapper.Companion.getApplicationContext().getString(R.string.avatar_editing));
        if(Theme.Companion.getThemePosition()==0){
            options.setStatusBarColor(config.statusBarColor);
            options.setToolbarColor(config.toolbarColor);
        }else{
            options.setStatusBarColor(config.statusBarColor);
            options.setToolbarColor(config.toolbarColor);
//            options.setStatusBarColor(0xFF007AFF);
//            options.setToolbarColor(0xFF007AFF);
        }
        options.setImageToCropBoundsAnimDuration(666);
        uCrop.withOptions(options);
        Intent intent = uCrop.getIntent(fragment.getContext());
        intent.setClass(fragment.getContext(), CustomUCropActivity.class);
        fragment.startActivityForResult(intent, UCrop.REQUEST_CROP);
//        uCrop.start(fragment.getContext(), fragment);
    }


    public static class CropConfig {
        public int aspectRatioX = 1;
        public int aspectRatioY = 1;
        public int maxWidth = 1080;
        public int maxHeight = 1920;

        //options
        public int tag;
        public boolean isOval = false;//是否为椭圆
        public int quality = 80;

        public boolean hideBottomControls = true;//底部操作条
        public boolean showGridLine = true;//内部网格
        public boolean showOutLine = true;//最外面的矩形线

        public @ColorInt
        int toolbarColor = 0xFF54d0ac;
        public @ColorInt
        int statusBarColor = 0xFF3fbe99;


        public void setAspectRation(int x, int y) {
            this.aspectRatioX = x;
            this.aspectRatioY = y;
        }

        public void setMaxSize(int width, int height) {
            this.maxHeight = height;
            this.maxWidth = width;
        }

    }


    public interface CropHandler {
        void handleCropResult(Uri uri, int tag);

        void handleCropError(Intent data);
    }
}