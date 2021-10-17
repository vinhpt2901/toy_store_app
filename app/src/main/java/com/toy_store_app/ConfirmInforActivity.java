package com.toy_store_app;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.getSavedObjectFromPreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.AccountDao;
import com.dao.CustomInfoDao;
import com.dao.OrderDao;
import com.dao.OrderDetailDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jdbc.RoomConnection;
import com.model.Account;
import com.model.AccountDetail;
import com.model.Card;
import com.model.CustomInfo;
import com.model.Order;
import com.model.OrderDetail;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ConfirmInforActivity extends AppCompatActivity {
    private EditText nameCustomer, phoneCustomer, addressCustomer, customerNote;
    private Button btnConfirm;
    private RoomConnection roomConnection;
    private CustomInfoDao customInfoDao;
    private OrderDao orderDao;
    private OrderDetailDao orderDetailDao;
    private String currentDate;
    private AccountDetail accountDetail;
    private Account account;
    private double totalPrice = 0;
    private List<Card> lists;
    private AccountDao accountDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_infor);

        nameCustomer = findViewById(R.id.customerName);
        phoneCustomer = findViewById(R.id.customerPhone);
        addressCustomer = findViewById(R.id.customerAddress);
        customerNote = findViewById(R.id.customerNote);
        btnConfirm = findViewById(R.id.btnUpdate);

        accountDetail = getSavedObjectFromPreference(getApplicationContext(), "mPreference", "account", AccountDetail.class);
        roomConnection = getInstance(this);
        accountDao = roomConnection.accountDao();
        account = accountDao.getAccountByAccountDetail(accountDetail.getId());
        lists = loadCart();
        for (Card c : lists) {
            totalPrice += c.getTotalPrice();
        }
        nameCustomer.setText(accountDetail.getName());
        phoneCustomer.setText(account.getMobile());
        addressCustomer.setText(accountDetail.getAddress());

        roomConnection = getInstance(this);
        customInfoDao = roomConnection.customInfoDao();
        orderDao = roomConnection.orderDao();
        orderDetailDao = roomConnection.orderDetailDao();
        currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add customer infor
                int customerID = (int) customInfoDao.add(new CustomInfo(nameCustomer.getText().toString(), phoneCustomer.getText().toString(), addressCustomer.getText().toString()));

                // add order
                Order order = new Order(1, 1, customerID, accountDetail.getId(), totalPrice, customerNote.getText().toString(), currentDate);
                int orderID = (int) orderDao.add(order);

                // add orderDetail
                for (Card c : lists) {
                    OrderDetail orderDetail = new OrderDetail(orderID, c.getProductId(), c.getProductName(), c.getOriginPrice(), c.getSellPrice(), c.getQuantity(), currentDate, currentDate);
                    orderDetailDao.add(orderDetail);
                }
                Toast.makeText(getApplicationContext(),"Thank you for your purchase. Your order is being processed!",Toast.LENGTH_SHORT).show();
                //CustomToast.makeText(getApplicationContext(), "Thank you for your purchase. Your order is being processed!", Toast.LENGTH_SHORT).show();
                saveCart(new ArrayList<Card>());
                Intent intent = new Intent(ConfirmInforActivity.this, com.shopee.MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Card> loadCart() {
        SharedPreferences preferences = getSharedPreferences("Carts", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("listCart", null);
        Type type = new TypeToken<ArrayList<Card>>() {
        }.getType();
        List<Card> cards = (List) gson.fromJson(json, type);
        if (cards == null) {
            cards = new ArrayList<>();
        }
        return cards;
    }

    private void saveCart(List<Card> cardList) {
        SharedPreferences preferences = getSharedPreferences("Carts", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cardList);
        editor.putString("listCart", json);
        editor.commit();
    }
}