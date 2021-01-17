package com.example.services;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class App extends Application {
    private final String CHANNEL_ID = "ForegroundServiceNotification";
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creteNotificationChannel();
        }
    }
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void creteNotificationChannel(){
       NotificationChannel serviceChannel1 = new NotificationChannel(CHANNEL_ID,"Example for foreground service", NotificationManager.IMPORTANCE_DEFAULT);
       NotificationManager notificationManager = getSystemService(NotificationManager.class);
       notificationManager.createNotificationChannel(serviceChannel1);
   }
}
