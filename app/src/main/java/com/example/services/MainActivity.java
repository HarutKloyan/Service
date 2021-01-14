package com.example.services;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start;
    private Button stop;
    Intent service = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start1);
        stop = findViewById(R.id.stop1);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        service = new Intent(this, MyService.class);



    }

    public void onClick(View v) {
        if (v.equals(start)) {
            startService(service);

        } else if (v.equals(stop)) {
            Toast.makeText(this,"no problem",Toast.LENGTH_LONG).show();
            stopService(service);
        }
    }
}