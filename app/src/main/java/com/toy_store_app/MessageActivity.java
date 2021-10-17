package com.toy_store_app;

import static com.util.Helper.loadLocale;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.MessageAdapter;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView messageRecyclerView;
    private String titleName[];
    private String descriptionName[];
    private int imageAvatar[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_message);

        messageRecyclerView = findViewById(R.id.messageRecyclerView);

        titleName = new String[]{"HangNguyen", "HangNguyen1", "HangNguyen2", "HangNguyen3", "HangNguyen4", "HangNguyen5", "HangNguyen6", "HangNguyen7", "HangNguyen5", "HangNguyen5", "HangNguyen5"};
        descriptionName = new String[]{"Mời bạn mua sản phẩm 1", "Mời bạn mua sản phẩm 2", "Mời bạn mua sản phẩm 3", "Mời bạn mua sản phẩm 4", "Mời bạn mua sản phẩm 5", "Mời bạn mua sản phẩm 6", "Mời bạn mua sản phẩm 6", "Mời bạn mua sản phẩm 6", "Mời bạn mua sản phẩm 6", "Mời bạn mua sản phẩm 6"};
        imageAvatar = new int[]{R.drawable.hangnguyen, R.drawable.hangnguyen, R.drawable.hangnguyen, R.drawable.hangnguyen, R.drawable.hangnguyen, R.drawable.hangnguyen, R.drawable.hangnguyen, R.drawable.hangnguyen, R.drawable.hangnguyen, R.drawable.hangnguyen};

        MessageAdapter messageAdapter = new MessageAdapter(this, titleName, descriptionName, imageAvatar);
        messageRecyclerView.setAdapter(messageAdapter);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}