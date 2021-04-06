package com.yumeng.libbase.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.yumeng.libbase.R;


/**
 * 通知类 Utils
 */
public class NotificationUtils extends ContextWrapper {

    private NotificationManager manager;
    public static final String id = "channel_1";
    public static final String name = "channel_name_1";
    public Notification notification;

    public NotificationUtils(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getChannelNotification(String title, String content) {
        return new Notification.Builder(getApplicationContext(), id)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) //设置通知提示音
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.mipmap.ic_avatar)
                .setAutoCancel(true);
    }

    public Notification.Builder getNotification_25(String title, String content) {
        return new Notification.Builder(this).setTicker(title).
                setSmallIcon(R.mipmap.ic_avatar).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_avatar))
                .setContentText(title).setContentTitle(content);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void sendNotification(String title, String content, String uriStr) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
            this.notification = getChannelNotification
                    (title, content).build();
//
            Uri uri = Uri.parse(uriStr);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            notification.contentIntent = pendingIntent;
            int idRandom = (int) (Math.random() * 100);
            if (idRandom == 0) {
                idRandom = 1;
            }
            getManager().notify(idRandom, notification);
        } else {
            this.notification = getNotification_25(title, content).build();
            Uri uri = Uri.parse(uriStr);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            notification.contentIntent = pendingIntent;
            int idRandom = (int) (Math.random() * 100);
            if (idRandom == 0) {
                idRandom = 1;
            }
            getManager().notify(idRandom, notification);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void sendNotificationChat(String title, String content, String uriStr) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
            this.notification = getChannelNotification
                    (title, content).build();
//
            Uri uri = Uri.parse(uriStr);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            notification.contentIntent = pendingIntent;

            getManager().notify(0, notification);
        } else {
            this.notification = getNotification_25(title, content).build();
            Uri uri = Uri.parse(uriStr);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            notification.contentIntent = pendingIntent;

            getManager().notify(0, notification);
        }
    }


    public Notification getNotification() {
        return this.notification;
    }
}