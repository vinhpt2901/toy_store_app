package com.toy_store_app;

import static com.util.Helper.getSavedObjectFromPreference;
import static com.util.Helper.loadLocale;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.DefaultAccountAdapter;
import com.model.AccountDetail;
import com.model.ImageAvatar;

import java.io.IOException;
import java.io.InputStream;

public class InformationAccountActivity extends AppCompatActivity {
    private RecyclerView settingAccount;
    private int imageSetting[];
    private String settingAccountString[];
    private TextView username;
    private ImageView imageAvatar;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getApplicationContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_information_account);
        AccountDetail accountDetail = getSavedObjectFromPreference(getApplicationContext(), "mPreference", "account", AccountDetail.class);
        ImageAvatar avatar = getSavedObjectFromPreference(getApplicationContext(), "imageAvatar", "avatar", ImageAvatar.class);
        ImageAvatar bia = getSavedObjectFromPreference(getApplicationContext(), "imageBia", "bia", ImageAvatar.class);

        settingAccount = findViewById(R.id.settingAccount);
        linearLayout = findViewById(R.id.imageBia);
        imageAvatar = findViewById(R.id.imageAvatar);

        imageSetting = new int[]{R.drawable.heart, R.drawable.settings, R.drawable.help, R.drawable.introduce, R.drawable.language, R.drawable.logout};
        settingAccountString = getResources().getStringArray(R.array.inforInSettingAccount);
        DefaultAccountAdapter settingAccountAdapter = new DefaultAccountAdapter(this, settingAccountString, imageSetting);
        settingAccount.setAdapter(settingAccountAdapter);
        settingAccount.setLayoutManager(new LinearLayoutManager(this));

        username = findViewById(R.id.username);
        username.setText(accountDetail.getName());

        // set image avatar
        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open(avatar.getImageLink());
            Drawable d = Drawable.createFromStream(ims, "hang.jpg");
            imageAvatar.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }
        // set image bia
        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open(bia.getImageLink());
            Drawable d = Drawable.createFromStream(ims, "bia.png");
            linearLayout.setBackground(d);
        } catch (IOException ex) {
            return;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getApplicationContext(), "Language", "My_Lang");
        AccountDetail accountDetail = getSavedObjectFromPreference(getApplicationContext(), "mPreference", "account", AccountDetail.class);
        username = findViewById(R.id.username);
        username.setText(accountDetail.getName());
        imageAvatar = findViewById(R.id.imageAvatar);
    }
}