package com.toy_store_app;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.getSavedObjectFromPreference;
import static com.util.Helper.loadLocale;
import static com.util.Helper.saveObjectToSharedPreference;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.AccountDetailDao;
import com.jdbc.RoomConnection;
import com.model.AccountDetail;
import com.toast.CustomToast;

public class ChangeNamActivity extends AppCompatActivity {
    private RoomConnection roomConnection;
    private AccountDetailDao accountDetailDao;
    private EditText txtInputName;
    private TextView saveData;
    private AccountDetail accountDetail;
    private String currentName;
    private String newName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_change_nam);
        accountDetail = getSavedObjectFromPreference(getApplicationContext(), "mPreference", "account", AccountDetail.class);
        txtInputName = findViewById(R.id.txtInputName);
        saveData = findViewById(R.id.txtSaveData);

        txtInputName.setText(accountDetail.getName());
        txtInputName.setSelection(txtInputName.getText().toString().length());
        currentName = txtInputName.getText().toString();
        roomConnection = getInstance(this);
        accountDetailDao = roomConnection.accountDetailDao();

        txtInputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newName = txtInputName.getText().toString();
                if (before != start) {
                    saveData.setTextColor(Color.RED);
                } else {
                    saveData.setTextColor(Color.parseColor("#f5b7b5"));
                }
                if (newName.length() > 100) {
                    Toast.makeText(getApplicationContext(), "Only 100 Character!", Toast.LENGTH_SHORT).show();
                    saveData.setTextColor(Color.parseColor("#f5b7b5"));
                    txtInputName.setText(currentName);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                newName = txtInputName.getText().toString();
                saveData.setTextColor(Color.RED);
            }
        });

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newName.equalsIgnoreCase(currentName)) {
                    accountDetail.setName(newName);
                    saveObjectToSharedPreference(getApplicationContext(), "mPreference", "account", accountDetail);
                    accountDetailDao.update(accountDetail);
                    CustomToast.makeText(getApplicationContext(), "Update name success", Toast.LENGTH_SHORT).show();
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