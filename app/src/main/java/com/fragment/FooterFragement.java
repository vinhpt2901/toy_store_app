package com.fragment;

import static com.util.Helper.getSavedObjectFromPreference;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.model.AccountDetail;
import com.shopee.InformationAccountActivity;
import com.shopee.LoginActivity;
import com.shopee.MainActivity;
import com.shopee.NotificaionActivity;
import com.shopee.R;
import com.shopee.ShopeeFeedActivity;
import com.shopee.ShoppeLiveActivity;

public class FooterFragement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.footer, container, false);
        ImageView shoppeFeed = view.findViewById(R.id.shoppeeFeed);
        shoppeFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShopeeFeedActivity.class);
                startActivity(intent);
            }
        });
        // Footer
        // Chuyen sang trang information
        ImageView shoppeLiveImage = view.findViewById(R.id.shoppeLive);
        shoppeLiveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShoppeLiveActivity.class);
                startActivity(intent);
            }
        });

        // Chuyen sang account setting
        ImageView accountSetting = view.findViewById(R.id.footerAccount);
        accountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDetail accountDetail = getSavedObjectFromPreference(getContext(), "mPreference", "account", AccountDetail.class);
                Intent intent;
                if (accountDetail != null) {
                    intent = new Intent(getContext(), InformationAccountActivity.class);
                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                }
                startActivity(intent);
            }
        });

        // Chuyen lai chinh trang nay
        ImageView homeImage = view.findViewById(R.id.footerHome);
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // Chuyen lai notificatiion
        ImageView notificationImage = view.findViewById(R.id.footerNotification);
        notificationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotificaionActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
