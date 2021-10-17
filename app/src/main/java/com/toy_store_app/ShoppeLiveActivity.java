package com.toy_store_app;

import static com.util.Helper.loadLocale;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ShoppeLiveActivity extends AppCompatActivity {
    private CarouselView carouselView;
    private int[] lists;
    private VideoView v0;
    private VideoView v1;
    private VideoView v2;
    private VideoView v3;
    private VideoView v4;
    private VideoView v5;
    private VideoView v6;
    private VideoView v7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_shoppe_live);
        // get data for list
        lists = new int[]{R.drawable.live1, R.drawable.live2, R.drawable.live3, R.drawable.live4, R.drawable.home5};
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(lists.length);

        // set data to carouse view
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.valueOf("CENTER_CROP"));
                imageView.setImageResource(lists[position]);
            }
        });

        v0 = findViewById(R.id.v0);
        v0.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.v3);
        v0.setBackgroundColor(Color.WHITE); // Your color.
        v0.start();
        v0.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                v0.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        v1 = findViewById(R.id.v1);
        v1.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.v9);
        v1.setBackgroundColor(Color.WHITE); // Your color.
        v1.start();
        v1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                v1.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        v2 = findViewById(R.id.v2);
        v2.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.v7);
        v2.setBackgroundColor(Color.WHITE); // Your color.
        v2.start();
        v2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                v2.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        v3 = findViewById(R.id.v3);
        v3.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.v9);
        v3.setBackgroundColor(Color.WHITE); // Your color.
        v3.start();
        v3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                v3.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        v4 = findViewById(R.id.v0);
        v4.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.v3);
        v4.setBackgroundColor(Color.WHITE); // Your color.
        v4.start();
        v4.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                v4.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        v5 = findViewById(R.id.v0);
        v5.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.v7);
        v5.setBackgroundColor(Color.WHITE); // Your color.
        v5.start();
        v5.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                v5.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        v6 = findViewById(R.id.v6);
        v6.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.v9);
        v6.setBackgroundColor(Color.WHITE); // Your color.
        v6.start();
        v6.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                v6.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        v7 = findViewById(R.id.v7);
        v7.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.v3);
        v7.setBackgroundColor(Color.WHITE); // Your color.
        v7.start();
        v7.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                v7.setBackgroundColor(Color.TRANSPARENT);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}