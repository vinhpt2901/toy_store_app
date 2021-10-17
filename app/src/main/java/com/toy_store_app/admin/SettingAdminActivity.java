package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.getSavedObjectFromPreference;
import static com.util.Helper.loadLocale;
import static com.util.Helper.saveObjectToSharedPreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.dao.AccountDao;
import com.google.android.material.navigation.NavigationView;
import com.jdbc.RoomConnection;
import com.model.Account;
import com.model.AccountDetail;
import com.shopee.LoginActivity;
import com.shopee.R;

public class SettingAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView nameSetting;
    private TextView phoneSetting;
    private TextView addressSetting;
    private TextView changePassword;
    private RoomConnection roomConnection;
    private AccountDao accountDao;
    private TextView createDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_admin);
        roomConnection = getInstance(getApplicationContext());
        accountDao = roomConnection.accountDao();

        AccountDetail accountDetail = getSavedObjectFromPreference(getApplicationContext(), "mPreferenceAdmin", "accountAdmin", AccountDetail.class);

        nameSetting = findViewById(R.id.nameSetting);
        phoneSetting = findViewById(R.id.phoneSetting);
        addressSetting = findViewById(R.id.addressSetting);
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
                Intent intent = new Intent(SettingAdminActivity.this, ChangeNamAdminActivity.class);
                startActivity(intent);
            }
        });

        phoneSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingAdminActivity.this, ChangePhoneAdminActivity.class);
                startActivity(intent);
            }
        });

        addressSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingAdminActivity.this, ChangeAdressAdminActivity.class);
                startActivity(intent);
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingAdminActivity.this, ChangePasswordAdminActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(this, DashboardAdminActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_product) {
            Intent intent = new Intent(this, ProductAdminActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_category) {
            Intent intent = new Intent(this, CategoryAdminActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_message) {
            Intent intent = new Intent(this, MessageAdminActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, SettingAdminActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_account) {
            Intent intent = new Intent(this, AccountAdminActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_order) {
            Intent intent = new Intent(this, OrderAdminActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            // remove shared references
            SharedPreferences sharedPreferences = getSharedPreferences("mPreferenceAdmin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        AccountDetail accountDetail = getSavedObjectFromPreference(getApplicationContext(), "mPreferenceAdmin", "accountAdmin", AccountDetail.class);
        Account account = getSavedObjectFromPreference(getApplicationContext(), "accountAdmin", "infoAccountAdmin", Account.class);
        nameSetting = findViewById(R.id.nameSetting);
        nameSetting.setText(accountDetail.getName());
        phoneSetting = findViewById(R.id.phoneSetting);
        phoneSetting.setText(account.getMobile());
        addressSetting = findViewById(R.id.addressSetting);
        addressSetting.setText(accountDetail.getAddress());
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}