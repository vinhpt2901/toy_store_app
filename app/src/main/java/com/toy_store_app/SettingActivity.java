package com.toy_store_app;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.getSavedObjectFromPreference;
import static com.util.Helper.loadLocale;
import static com.util.Helper.saveObjectToSharedPreference;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.AccountDao;
import com.jdbc.RoomConnection;
import com.model.Account;
import com.model.AccountDetail;

public class SettingActivity extends AppCompatActivity {
    private TextView nameSetting;
    private TextView phoneSetting;
    private TextView addressSetting;
    private TextView changeFacebook;
    private TextView changePaymentAccount;
    private TextView changePassword;
    private RoomConnection roomConnection;
    private AccountDao accountDao;
    private TextView createDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_setting);

        roomConnection = getInstance(getApplicationContext());
        accountDao = roomConnection.accountDao();

        AccountDetail accountDetail = getSavedObjectFromPreference(getApplicationContext(), "mPreference", "account", AccountDetail.class);

        nameSetting = findViewById(R.id.nameSetting);
        phoneSetting = findViewById(R.id.phoneSetting);
        addressSetting = findViewById(R.id.addressSetting);
        changeFacebook = findViewById(R.id.changeNetWorkAccount);
        changePaymentAccount = findViewById(R.id.changeAccountLienKet);
        changePassword = findViewById(R.id.changePassword);
        createDate = findViewById(R.id.dateSetting);

        // set information
        nameSetting.setText(accountDetail.getName());

        Account account = accountDao.getAccountByAccountDetail(accountDetail.getId());
        saveObjectToSharedPreference(getApplicationContext(), "accountPreference", "accountOfAccountDetail", account);

        phoneSetting.setText(account.getMobile());
        addressSetting.setText(accountDetail.getAddress());
        createDate.setText(accountDetail.getCreateDate());

        nameSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ChangeNamActivity.class);
                startActivity(intent);
            }
        });

        phoneSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, com.shopee.ChangePhoneActivity.class);
                startActivity(intent);
            }
        });

        addressSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ChangeAdressActivity.class);
                startActivity(intent);
            }
        });
        changePaymentAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changeFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ConnectFacebookInstagramActivity.class);
                startActivity(intent);
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AccountDetail accountDetail = getSavedObjectFromPreference(getApplicationContext(), "mPreference", "account", AccountDetail.class);
        Account account = getSavedObjectFromPreference(getApplicationContext(), "accountPreference", "accountOfAccountDetail", Account.class);
        nameSetting = findViewById(R.id.nameSetting);
        nameSetting.setText(accountDetail.getName());
        phoneSetting = findViewById(R.id.phoneSetting);
        phoneSetting.setText(account.getMobile());
        addressSetting = findViewById(R.id.addressSetting);
        addressSetting.setText(accountDetail.getAddress());
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}