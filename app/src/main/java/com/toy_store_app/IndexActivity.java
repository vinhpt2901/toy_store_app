package com.toy_store_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class IndexActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        // Set local default before run after
        Locale locale = new Locale("en");
        Locale.setDefault(locale);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IndexActivity.this, com.shopee.MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}