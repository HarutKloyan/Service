package com.example.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start;
    private Button stop;
    private Intent serviceBound = null;
    private EditText editText;
    private BoundService boundService = new BoundService();
    private boolean myServiceBounded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText1);

        start = findViewById(R.id.start1);
        stop = findViewById(R.id.stop1);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);

      //  service = new Intent(this, MyService.class);
        serviceBound = new Intent(this, ForegroundService.class);
    }
    @Override
    protected void onStart(){
        startService(serviceBound);
        bindService(serviceBound,myServiceConnection,Context.BIND_AUTO_CREATE);
        super.onStart();

    }
    @Override
    protected void onStop(){
        unbindService(myServiceConnection);
        stopService(serviceBound);
        super.onStop();
    }


    public void onClick(View v) {
        if (v.equals(start)) {
            int randomNumber = boundService.getRandomNumber();
            editText.setText(String.valueOf(randomNumber));
            serviceBound = new Intent(this,ForegroundService.class);
            serviceBound.putExtra("Data",editText.getText().toString());

            ContextCompat.startForegroundService(this,serviceBound);

        } else if (v.equals(stop)) {
            Toast.makeText(this,"no problem",Toast.LENGTH_LONG).show();
            stopService(serviceBound);
        }
    }
    private ServiceConnection myServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.LocalBounder myBinder = (BoundService.LocalBounder) service;
            boundService = myBinder.getService();
            myServiceBounded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myServiceBounded = false;
        }
    };


}