package com.example.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start;
    private Button stop;
    Intent service = null;
    EditText editText;



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
        service = new Intent(this, ForegroundService.class);


    }


    public void onClick(View v) {
        if (v.equals(start)) {
            service = new Intent(this,ForegroundService.class);
            service.putExtra("Data",editText.getText().toString());
            //startService(data);

          //  startService(service(new Intent(this, ForegroundService.class).putExtra("DATA",findViewById(R.id.editText1))));
            ContextCompat.startForegroundService(this,service);

        } else if (v.equals(stop)) {
            Toast.makeText(this,"no problem",Toast.LENGTH_LONG).show();
            stopService(service);
        }
    }
}