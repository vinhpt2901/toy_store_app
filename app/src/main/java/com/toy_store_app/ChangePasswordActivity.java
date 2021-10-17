package com.toy_store_app;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.getSavedObjectFromPreference;
import static com.util.Helper.loadLocale;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.AccountDao;
import com.jdbc.RoomConnection;
import com.model.Account;

public class ChangePasswordActivity extends AppCompatActivity {
    private RoomConnection roomConnection;
    private AccountDao accountDao;
    private EditText txtInputPhone;
    private TextView saveData;
    private Account account;
    private String newPassword;
    private String oldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_change_password);
        account = getSavedObjectFromPreference(getApplicationContext(), "accountPreference", "accountOfAccountDetail", Account.class);
        txtInputPhone = findViewById(R.id.txtPassworNew);
        saveData = findViewById(R.id.txtSaveData);

        txtInputPhone.setText(account.getMobile());
        roomConnection = getInstance(this);
        accountDao = roomConnection.accountDao();

        txtInputPhone.setText(account.getPassword());
        txtInputPhone.setSelection(txtInputPhone.getText().toString().length());
        oldPassword = txtInputPhone.getText().toString();


        txtInputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newPassword = txtInputPhone.getText().toString();
                if (before != start) {
                    saveData.setTextColor(Color.RED);
                } else {
                    saveData.setTextColor(Color.parseColor("#f5b7b5"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                newPassword = txtInputPhone.getText().toString();
                saveData.setTextColor(Color.RED);
            }
        });

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldPassword != newPassword) {
                    account.setPassword(newPassword);
                    accountDao.update(account);
                    Toast.makeText(getApplicationContext(), "Update password success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}