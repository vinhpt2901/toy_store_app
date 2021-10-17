package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.OrderDao;
import com.dao.StatusOrderDao;
import com.example.toy_store_app.R;
import com.jdbc.RoomConnection;
import com.model.CustomInfo;
import com.model.Order;
import com.model.StatusOrder;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DetailOrderAdminActivity extends AppCompatActivity {
    private TextView customerName, address, totalPrice, createDate;
    private Button btnUpdate;
    private Spinner spinner;
    private RoomConnection roomConnection;
    private StatusOrderDao statusOrderDao;
    private OrderDao orderDao;
    private ArrayAdapter<StatusOrder> listStatusOrder;
    private List<StatusOrder> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_admin);
        roomConnection = getInstance(this);
        statusOrderDao = roomConnection.statusOrderDao();
        orderDao = roomConnection.orderDao();
        lists = statusOrderDao.getAll();

        customerName = findViewById(R.id.txtCustomerName);
        address = findViewById(R.id.txtAddressCustomer);
        totalPrice = findViewById(R.id.txtTotalPrice);
        createDate = findViewById(R.id.txtCreateDate);
        btnUpdate = findViewById(R.id.btnUpdate);
        spinner = findViewById(R.id.statusOrder);

        Order order = (Order) getIntent().getSerializableExtra("order");
        CustomInfo customInfo = (CustomInfo) getIntent().getSerializableExtra("customInfo");
        StatusOrder statusOrder = (StatusOrder) getIntent().getSerializableExtra("statusOrder");

        customerName.setText(customInfo.getCustomerName());
        address.setText(customInfo.getAddress());
        totalPrice.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(order.getTotalPrice()) + " Đ");
        createDate.setText(order.getCreateDate());

        listStatusOrder = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, lists);
        spinner.setAdapter(listStatusOrder);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusOrder statusOrder1 = (StatusOrder) spinner.getSelectedItem();
                order.setStatusOrderID(statusOrder1.getId());
                orderDao.update(order);
                finish();
            }
        });
    }
}