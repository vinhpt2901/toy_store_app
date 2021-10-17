package com.toy_store_app;

import static com.util.Helper.loadLocale;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ShopeeFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_shopee_feed);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}