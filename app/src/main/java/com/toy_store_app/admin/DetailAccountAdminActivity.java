package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.AccountDao;
import com.dao.AccountDetailDao;
import com.jdbc.RoomConnection;
import com.model.Account;
import com.model.AccountDetail;
import com.model.Role;
import com.shopee.R;

public class DetailAccountAdminActivity extends AppCompatActivity {
    private TextView name, address, phone, roleTxt;
    private Button btnUpdate;
    private RoomConnection roomConnection;
    private AccountDetailDao accountDetailDao;
    private AccountDao accountDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_account_admin);
        name = findViewById(R.id.txtAccountName);
        address = findViewById(R.id.txtAddress);
        phone = findViewById(R.id.txtPhone);
        btnUpdate = findViewById(R.id.btnUpdate);
        roleTxt = findViewById(R.id.txtRole);

        Account account = (Account) getIntent().getSerializableExtra("account");
        AccountDetail accountDetail = (AccountDetail) getIntent().getSerializableExtra("accountDetail");
        Role role = (Role) getIntent().getSerializableExtra("role");

        roomConnection = getInstance(this);
        accountDao = roomConnection.accountDao();
        accountDetailDao = roomConnection.accountDetailDao();

        name.setText(accountDetail.getName());
        address.setText(accountDetail.getAddress());
        phone.setText(account.getMobile());
        roleTxt.setText(role.getRoleName());

        if (role.getId() == 1) {
            btnUpdate.setEnabled(false);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update role
                account.setRoleID(1);
                accountDao.update(account);
                finish();
            }
        });
    }
}