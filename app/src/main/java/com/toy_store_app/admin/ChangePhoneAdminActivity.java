package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.getSavedObjectFromPreference;
import static com.util.Helper.loadLocale;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.AccountDao;
import com.jdbc.RoomConnection;
import com.model.Account;
import com.shopee.R;
import com.toast.CustomToast;

public class ChangePhoneAdminActivity extends AppCompatActivity {
    private RoomConnection roomConnection;
    private AccountDao accountDao;
    private EditText txtInputPhone;
    private TextView saveData;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_admin);
        account = getSavedObjectFromPreference(getApplicationContext(), "accountPreference", "infoAccountAdmin", Account.class);
        txtInputPhone = findViewById(R.id.txtPhoneNumber);
        saveData = findViewById(R.id.txtSaveDataPhone);

        txtInputPhone.setText(account.getMobile());

        roomConnection = getInstance(this);
        accountDao = roomConnection.accountDao();

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMobile = txtInputPhone.getText().toString();
                account.setMobile(newMobile);
                accountDao.update(account);
                CustomToast.makeText(getApplicationContext(), "Update phone success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}