package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class BoundService extends Service {

    private IBinder myBinder = new LocalBounder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    public  int getRandomNumber(){
        return  new Random().nextInt(100);
    }
    public class LocalBounder extends Binder{
        BoundService getService(){
            return BoundService.this;
        }
    }
}
