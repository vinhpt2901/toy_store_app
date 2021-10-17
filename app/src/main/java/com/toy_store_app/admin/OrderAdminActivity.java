package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.dao.AccountDetailDao;
import com.dao.CustomInfoDao;
import com.dao.OrderDao;
import com.dao.StatusOrderDao;
import com.google.android.material.navigation.NavigationView;
import com.jdbc.RoomConnection;
import com.model.CustomInfo;
import com.model.Order;
import com.model.StatusOrder;
import com.shopee.LoginActivity;
import com.shopee.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView menuView;
    private TableLayout tableLayout;
    private RoomConnection roomConnection;
    private CustomInfoDao customInfoDao;
    private AccountDetailDao accountDetailDao;
    private StatusOrderDao statusOrderDao;
    private OrderDao orderDao;
    private List<Order> listOrder;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_admin);

        tableLayout = findViewById(R.id.tableLayoutOrder);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        menuView = findViewById(R.id.iconMenu);
        roomConnection = getInstance(this);
        accountDetailDao = roomConnection.accountDetailDao();
        customInfoDao = roomConnection.customInfoDao();
        statusOrderDao = roomConnection.statusOrderDao();
        orderDao = roomConnection.orderDao();
        listOrder = orderDao.getAll();

        createColumns();
        fillData();

        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
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

    private void createColumns() {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        // id column
        TextView textViewID = new TextView(this);
        textViewID.setText("No");
        textViewID.setTextColor(Color.BLACK);
        textViewID.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewID.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewID);

        // Name column
        TextView textViewName = new TextView(this);
        textViewName.setText("Customer Name");
        textViewName.setTextColor(Color.BLACK);
        textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewName.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewName);

        // Address column
        TextView textViewAddress = new TextView(this);
        textViewAddress.setText("Address");
        textViewAddress.setTextColor(Color.BLACK);
        textViewAddress.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewAddress.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewAddress);

        // Delete column
        TextView textViewDelete = new TextView(this);
        textViewDelete.setText("Total Price");
        textViewDelete.setTextColor(Color.BLACK);
        textViewDelete.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewDelete.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewDelete);

        // Delete column
        TextView textViewStatus = new TextView(this);
        textViewStatus.setText("Status");
        textViewStatus.setTextColor(Color.BLACK);
        textViewStatus.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewStatus.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewStatus);

        tableLayout.addView(tableRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        // Add divider
        tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        // id column
        textViewID = new TextView(this);
        textViewID.setText("------");
        textViewID.setTextColor(Color.BLACK);
        textViewID.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewID.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewID);

        // Name column
        textViewName = new TextView(this);
        textViewName.setText("-------------------");
        textViewName.setTextColor(Color.BLACK);
        textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewName.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewName);

        // Address column
        textViewAddress = new TextView(this);
        textViewAddress.setText("-----------");
        textViewAddress.setTextColor(Color.BLACK);
        textViewAddress.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewAddress.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewAddress);

        // Role column
        textViewDelete = new TextView(this);
        textViewDelete.setText("-----------");
        textViewDelete.setTextColor(Color.BLACK);
        textViewDelete.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewDelete.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewDelete);

        // Role column
        textViewStatus = new TextView(this);
        textViewStatus.setText("-----------");
        textViewStatus.setTextColor(Color.BLACK);
        textViewStatus.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewStatus.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewStatus);

        tableLayout.addView(tableRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
    }

    private void fillData() {
        for (int i = 0; i < listOrder.size(); i++) {
            CustomInfo customInfo = customInfoDao.getOneByOrderID(listOrder.get(i).getId());
            StatusOrder statusOrder = statusOrderDao.getOneByOrder(listOrder.get(i).getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TableRow currentRow = (TableRow) v;
                    TextView textViewID = (TextView) currentRow.getChildAt(0);
                    Order order = listOrder.get(Integer.valueOf(textViewID.getText().toString()) - 1);
                    Intent intent = new Intent(OrderAdminActivity.this, DetailOrderAdminActivity.class);
                    intent.putExtra("customInfo", customInfo);
                    intent.putExtra("order", order);
                    intent.putExtra("statusOrder", statusOrder);
                    startActivity(intent);


                }
            });
            // id column
            TextView textViewID = new TextView(this);
            textViewID.setText(String.valueOf(i + 1));
            textViewID.setTextColor(Color.BLACK);
            textViewID.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            textViewID.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewID);

            // Name column
            TextView textViewName = new TextView(this);
            textViewName.setText(customInfo.getCustomerName());
            textViewName.setTextColor(Color.BLACK);
            textViewName.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            textViewName.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewName);

            // Address column
            TextView textViewAddress = new TextView(this);
            textViewAddress.setText(customInfo.getAddress());
            textViewAddress.setTextColor(Color.BLACK);
            textViewAddress.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            textViewAddress.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewAddress);

            // Role column
            TextView textViewRole = new TextView(this);
            textViewRole.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(listOrder.get(i).getTotalPrice()) + " Đ");
            textViewRole.setTextColor(Color.BLACK);
            textViewRole.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            textViewRole.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewRole);

            // Role column
            TextView statusTxt = new TextView(this);
            statusTxt.setText(statusOrder.getStatusOrderName());
            statusTxt.setTextColor(Color.BLACK);
            statusTxt.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            statusTxt.setPadding(5, 5, 5, 0);
            tableRow.addView(statusTxt);

            tableLayout.addView(tableRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

}