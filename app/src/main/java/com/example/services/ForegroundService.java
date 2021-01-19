package com.example.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.nio.channels.Channel;

public class ForegroundService extends Service {
    private final String CHANNEL_ID = "ForegroundServiceNotification";
    TextView textView;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
                // return super.onStartCommand(intent, flags, startId);
        String editText = intent.getStringExtra("Data");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        Intent[] notificationIntents ={notificationIntent};
        PendingIntent pendingIntent = PendingIntent.getActivities(this,0,notificationIntents,0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Уведомление")
                .setContentText(editText)
                .setSmallIcon(R.drawable.ic_ac_unit)
                .setContentIntent(pendingIntent)
                .build();


        startForeground(1,notification);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
