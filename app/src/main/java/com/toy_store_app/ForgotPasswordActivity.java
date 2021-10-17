package com.toy_store_app;

import static com.util.Helper.loadLocale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText txtPhone;
    private Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_forgot_password);
        txtPhone = findViewById(R.id.txtSendPhone);
        btnSend = findViewById(R.id.sendCodeBtn);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = txtPhone.getText().toString().trim();

                if (mobile.isEmpty() || mobile.length() < 10) {
                    txtPhone.setError("Enter a valid mobile");
                    txtPhone.requestFocus();
                    return;
                }

                // Storing data into SharedPreferences
                SharedPreferences sharedPreferences
                        = getSharedPreferences("MySharedPref",
                        MODE_PRIVATE);

                // Creating an Editor object
                // to edit(write to the file)
                SharedPreferences.Editor myEdit
                        = sharedPreferences.edit();

                // Storing the key and its value
                // as the data fetched from edittext
                myEdit.putString(
                        "accountPhone",
                        mobile);

                // Once the changes have been made,
                // we need to commit to apply those changes made,
                // otherwise, it will throw an error
                myEdit.commit();

                Intent intent = new Intent(ForgotPasswordActivity.this, com.shopee.ConfirmOTPActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}
