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

import com.dao.CategoryDao;
import com.dao.ImageCategoryDao;
import com.google.android.material.navigation.NavigationView;
import com.jdbc.RoomConnection;
import com.model.Category;
import com.shopee.LoginActivity;
import com.shopee.R;

import java.util.List;

public class CategoryAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView menuView;
    private RoomConnection roomConnection;
    private ImageCategoryDao imageCategoryDao;
    private CategoryDao categoryDao;
    private List<Category> listCate;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_admin);
        tableLayout = findViewById(R.id.tableLayoutCate);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        menuView = findViewById(R.id.iconMenu);

        roomConnection = getInstance(this);
        imageCategoryDao = roomConnection.imageCategoryDao();
        categoryDao = roomConnection.categoryDao();
        listCate = categoryDao.getAll();

        findViewById(R.id.addCate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCategoryAdminActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.addSubCate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddSubCategoryAdminActivity.class);
                startActivity(intent);
            }
        });
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
        textViewName.setText("Category Code");
        textViewName.setTextColor(Color.BLACK);
        textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewName.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewName);

        // Address column
        TextView textViewAddress = new TextView(this);
        textViewAddress.setText("Category Name");
        textViewAddress.setTextColor(Color.BLACK);
        textViewAddress.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewAddress.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewAddress);

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
        textViewAddress.setText("------------------");
        textViewAddress.setTextColor(Color.BLACK);
        textViewAddress.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewAddress.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewAddress);

        tableLayout.addView(tableRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
    }

    private void fillData() {
        for (int i = 0; i < listCate.size(); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TableRow currentRow = (TableRow) v;
                    TextView textViewID = (TextView) currentRow.getChildAt(0);
                    Category category = listCate.get(Integer.valueOf(textViewID.getText().toString()) - 1);
                    Intent intent = new Intent(CategoryAdminActivity.this, DetailCateAdminActivity.class);
                    intent.putExtra("category", category);
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
            textViewName.setText(listCate.get(i).getCategoryCode());
            textViewName.setTextColor(Color.BLACK);
            textViewName.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            textViewName.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewName);

            // Address column
            TextView textViewAddress = new TextView(this);
            textViewAddress.setText(listCate.get(i).getCategoryName());
            textViewAddress.setTextColor(Color.BLACK);
            textViewAddress.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            textViewAddress.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewAddress);

            tableLayout.addView(tableRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }
    }
}
