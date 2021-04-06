package com.yumeng.libcommon.utils

import android.content.ActivityNotFoundException
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.FileProvider
import com.yumeng.libcommon.R
import java.io.*
import java.util.*


/**
 * Created by Chauncey on 2017/9/20 11:39.
 * 文件辅助类
 * 获取文件uri
 * 类型
 * 等等
 */

object FileUtil {

    private const val DATA_TYPE_ALL = "*/*"//未指定明确的文件类型，不能使用精确类型的工具打开，需要用户选择
    private const val DATA_TYPE_APK = "application/vnd.android.package-archive"
    private const val DATA_TYPE_VIDEO = "video/*"
    private const val DATA_TYPE_AUDIO = "audio/*"
    private const val DATA_TYPE_HTML = "text/html"
    private const val DATA_TYPE_IMAGE = "image/*"
    private const val DATA_TYPE_PPT = "application/vnd.ms-powerpoint"
    private const val DATA_TYPE_EXCEL = "application/vnd.ms-excel"
    private const val DATA_TYPE_WORD = "application/msword"
    private const val DATA_TYPE_CHM = "application/x-chm"
    private const val DATA_TYPE_TXT = "text/plain"
    private const val DATA_TYPE_PDF = "application/pdf"

    private const val VIDEO = "video"
    private const val AUDIO = "audio"
    private const val TEXT = "text"
    private const val IMAGE = "image"
    private const val APPLICATION = "application"
    private const val APK = "application/vnd.android"

    fun getFileFromUri(context: Context, uri: Uri): File {
        return File(getFilePathFromContentUri(context, uri))
    }

    fun getFileFromPath(path: String): File {
        return File(path)
    }

    private fun getFilePathFromContentUri(context: Context, uri: Uri): String {
        val path: String

        when (uri.scheme) {
            "content" -> {
                val projection = arrayOf(MediaStore.Images.ImageColumns.DATA)
                val c = context.contentResolver.query(uri, projection, null, null, null)!!
                c.moveToFirst()
                val columnIndex = c.getColumnIndex(projection[0])
                path = c.getString(columnIndex)
                c.close()
            }

            "file" -> path = uri.path
            else -> path = uri.path
        }

        return path
    }

    fun getFileType(fileName: String?): String? {
        if (fileName.isNullOrBlank() || fileName.endsWith(".")) {
            return null
        }
        val index = fileName.lastIndexOf(".")
        return if (index != -1) {
            fileName.substring(index + 1).toLowerCase(Locale.US)
        } else {
            null
        }

    }

    fun getFileType(file: File?): String? {
        if (file == null || !file.exists() || file.isDirectory) {
            return null
        }
        return getFileType(file.name)
    }

    fun getMimeType(file: File): String {
        val suffix = getFileType(file) ?: return DATA_TYPE_ALL
        val type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(suffix)
        return if (type == null || type.isEmpty()) {
            DATA_TYPE_ALL
        } else type
    }

    /**
     * new File(File.separator);//指向当前class文件所在的盘符,例如D:
     * new File(new File(File.separator),"File_separator.txt");//  D:\File_separator.txt
     */
    fun getAbsolutePath(path: String): String {
        return File(File(File.separator), path).absolutePath
    }

    fun outputStringToFile(path: String, text: String) {
        var bufferedWriter: BufferedWriter? = null
        try {
            val fw = FileWriter(path)
            bufferedWriter = BufferedWriter(fw)
            bufferedWriter.write(text)
            bufferedWriter.flush()

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                assert(bufferedWriter != null)
                bufferedWriter!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }


    /**
     * 获取对应文件的Uri
     *
     * @param intent 相应的Intent
     * @param file   文件对象
     * @return
     */
    private fun getUri(context: Context, intent: Intent, file: File): Uri? {
        val uri: Uri?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //判断版本是否在7.0以上
            uri = FileProvider.getUriForFile(context, context.packageName + ".provider", file)
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        } else {
            uri = Uri.fromFile(file)
        }
        return uri
    }

    /**
     * 打开文件
     *
     * @param file 文件
     */
    fun openFile(context: Context, file: File) {
        if (!file.exists()) {
            //如果文件不存在
            Toast.makeText(
                context,
                context.getString(R.string.open_the_file_failure_reason_file_has_been_removed),
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val filePath = file.path
        /* 取得扩展名 */
        val mimeType = getMimeType(file)
        if (mimeType == APK) {

        } else {
            try {
                context.startActivity(generateFileIntent(context, filePath, mimeType))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    context.getString(R.string.no_app_installed_to_open_this_file),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        //        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
        //        /* 依扩展名的类型决定MimeType */
        //        Intent intent;
        //        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
        //            intent = generateVideoAudioIntent(context, filePath, DATA_TYPE_AUDIO);
        //        } else if (end.equals("3gp") || end.equals("mp4")) {
        //            intent = generateVideoAudioIntent(context, filePath, DATA_TYPE_VIDEO);
        //        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_IMAGE);
        //        } else if (end.equals("apk")) {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_APK);
        //        } else if (end.equals("html") || end.equals("htm")) {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_ALL);
        //        } else if (end.equals("ppt")) {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_PPT);
        //        } else if (end.equals("xls")) {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_EXCEL);
        //        } else if (end.equals("doc") || end.equals("docx")) {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_WORD);
        //        } else if (end.equals("pdf")) {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_PDF);
        //        } else if (end.equals("chm")) {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_CHM);
        //        } else if (end.equals("txt")) {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_TXT);
        //        } else {
        //            intent = generateCommonIntent(context, filePath, DATA_TYPE_ALL);
        //        }
        //        context.startActivity(intent);
    }

    /**
     * 产生打开视频或音频的Intent
     *
     * @param filePath 文件路径
     * @param dataType 文件类型
     * @return
     */
    private fun generateVideoAudioIntent(
        context: Context,
        filePath: String,
        dataType: String
    ): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("oneshot", 0)
        intent.putExtra("configchange", 0)
        val file = File(filePath)
        intent.setDataAndType(getUri(context, intent, file), dataType)
        return intent
    }

    /**
     * 创建Intent
     */
    private fun generateFileIntent(context: Context, filePath: String, mimeType: String): Intent {
        return when {
            mimeType.startsWith(VIDEO) || mimeType.startsWith(AUDIO) -> {
                generateVideoAudioIntent(context, filePath, mimeType)
            }
            else -> {
                generateCommonIntent(context, filePath, mimeType)
            }
        }
    }

    /**
     * 产生打开网页文件的Intent
     *
     * @param filePath 文件路径
     * @return
     */
    private fun generateHtmlFileIntent(filePath: String): Intent {
        val uri = Uri.parse(filePath)
            .buildUpon()
            .encodedAuthority("com.android.htmlfileprovider")
            .scheme("content")
            .encodedPath(filePath)
            .build()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, DATA_TYPE_HTML)
        return intent
    }

    /**
     * 产生除了视频、音频、网页文件外，打开其他类型文件的Intent
     *
     * @param filePath 文件路径
     * @param dataType 文件类型
     * @return
     */
    private fun generateCommonIntent(context: Context, filePath: String, dataType: String): Intent {
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.action = Intent.ACTION_VIEW
        val file = File(filePath)
        val uri = getUri(context, intent, file)
        intent.setDataAndType(uri, dataType)
        return intent
    }

    /**
     * 随机生产文件名
     *
     * @return
     */
    private fun generateFileName(): String {
        return UUID.randomUUID().toString()
    }

    /**
     * 在SD卡上创建文件夹
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    fun createSDDirectory(fileName: String): File {
        val file = File(fileName)
        if (!file.exists())
            file.mkdirs()
        return file
    }


    /**
     * 根据相册媒体库路径转换成sd卡路径
     *
     * @param context
     * @param uri
     * @return
     */
    fun getPath(context: Context, uri: Uri): String? {
        val isOverKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
        // DocumentProvider
        if (isOverKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split =
                    docId.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return (Environment.getExternalStorageDirectory().toString() + "/"
                        + split[1])
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split =
                    docId.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf<String>(split[1])
                return getDataColumn(
                    context, contentUri, selection,
                    selectionArgs
                )
            }// MediaProvider
            // DownloadsProvider
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(
                context,
                uri,
                null,
                null
            )
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }// File
        // MediaStore (and general)
        return null
    }

    private fun getDataColumn(
        context: Context, uri: Uri?,
        selection: String?, selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(
                uri!!, projection,
                selection, selectionArgs, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            if (cursor != null)
                cursor.close()
        }
        return null
    }

    private fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri
            .authority
    }

    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri
            .authority
    }

    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri
            .authority
    }

    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri
            .authority
    }

    internal fun getPictureNameFromPath(picturePath: String): String {
        val temp = picturePath.replace("\\\\".toRegex(), "/").split("/".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()
        var fileName = ""
        if (temp.size > 1) {
            fileName = temp[temp.size - 1]
        }
        return fileName
    }
}
