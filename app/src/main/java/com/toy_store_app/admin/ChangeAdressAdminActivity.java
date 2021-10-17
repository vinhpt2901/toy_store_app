package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.getSavedObjectFromPreference;

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
import com.shopee.R;
import com.toast.CustomToast;

public class ChangeAdressAdminActivity extends AppCompatActivity {
    private RoomConnection roomConnection;
    private AccountDetailDao accountDetailDao;
    private EditText txtInputPhone;
    private TextView saveData;
    private AccountDetail accountDetail;
    private String currentPhone;
    private String newPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_adress_admin);
        accountDetail = getSavedObjectFromPreference(getApplicationContext(), "mPreferenceAdmin", "accountAdmin", AccountDetail.class);
        txtInputPhone = findViewById(R.id.txtInputName);
        saveData = findViewById(R.id.txtSaveData);

        txtInputPhone.setText(accountDetail.getAddress());

        roomConnection = getInstance(this);
        accountDetailDao = roomConnection.accountDetailDao();


        txtInputPhone.setText(accountDetail.getName());
        txtInputPhone.setSelection(txtInputPhone.getText().toString().length());
        currentPhone = txtInputPhone.getText().toString();

        txtInputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newPhone = txtInputPhone.getText().toString();
                if (before != start) {
                    saveData.setTextColor(Color.RED);
                } else {
                    saveData.setTextColor(Color.parseColor("#f5b7b5"));
                }
                if (newPhone.length() > 11) {
                    Toast.makeText(getApplicationContext(), "Only 11 Character!", Toast.LENGTH_SHORT).show();
                    saveData.setTextColor(Color.parseColor("#f5b7b5"));
                    txtInputPhone.setText(currentPhone);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                newPhone = txtInputPhone.getText().toString();
                saveData.setTextColor(Color.RED);
            }
        });

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newPhone != currentPhone) {
                    accountDetail.setAddress(newPhone);
                    accountDetailDao.update(accountDetail);
                    CustomToast.makeText(getApplicationContext(), "Update address success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}