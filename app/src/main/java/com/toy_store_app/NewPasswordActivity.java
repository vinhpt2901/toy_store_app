package com.toy_store_app;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.loadLocale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.AccountDao;
import com.facebook.login.Login;
import com.jdbc.RoomConnection;
import com.model.Account;

public class NewPasswordActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText newPassword;
    private RoomConnection roomConnection;
    private AccountDao accountDao;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_new_password);
        newPassword = findViewById(R.id.txtNewPassword);
        btnLogin = findViewById(R.id.loginNewpassord);

        SharedPreferences sh
                = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);

        String phone = sh.getString("accountPhone", "");

        roomConnection = getInstance(this);
        accountDao = roomConnection.accountDao();
        account = accountDao.getAccountByPhone(phone);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update password
                String newPass = newPassword.getText().toString();
                account.setPassword(newPass);
                accountDao.update(account);
                Toast.makeText(getApplicationContext(), "Update password success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewPasswordActivity.this, Login.class);
                startActivity(intent);
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