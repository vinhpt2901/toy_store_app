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

import com.dao.BrandDao;
import com.dao.ImageDao;
import com.dao.ProductDao;
import com.dao.StatusDao;
import com.dao.SubCategoryDao;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.jdbc.RoomConnection;
import com.model.Brand;
import com.model.Product;
import com.model.Status;
import com.model.SubCategory;
import com.shopee.LoginActivity;
import com.shopee.R;

import java.util.List;

public class ProductAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView menuView;
    private RoomConnection roomConnection;
    private ImageDao imageDao;
    private ProductDao productDao;
    private StatusDao statusDao;
    private List<Product> listProducts;
    private TableLayout tableLayout;
    private SubCategoryDao subCategoryDao;
    private BrandDao brandDao;
    private MaterialButton btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_admin);
        tableLayout = findViewById(R.id.tableLayoutProduct);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        menuView = findViewById(R.id.iconMenu);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        roomConnection = getInstance(this);
        productDao = roomConnection.productDao();
        imageDao = roomConnection.imageDao();
        statusDao = roomConnection.statusDao();
        subCategoryDao = roomConnection.subCategoryDao();
        brandDao = roomConnection.brandDao();
        listProducts = productDao.getAll();

        createColumns();
        fillData();
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ProductAdminActivity.this,AddProductAdminActivity.class);
                startActivity(intent);
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
        textViewName.setText("Product Name");
        textViewName.setTextColor(Color.BLACK);
        textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewName.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewName);

        // Address column
        TextView textViewAddress = new TextView(this);
        textViewAddress.setText("Quantity");
        textViewAddress.setTextColor(Color.BLACK);
        textViewAddress.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewAddress.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewAddress);

        // Address column
        TextView brandName = new TextView(this);
        brandName.setText("Brand");
        brandName.setTextColor(Color.BLACK);
        brandName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        brandName.setPadding(5, 5, 5, 0);
        tableRow.addView(brandName);

        // Address column
        TextView status = new TextView(this);
        status.setText("Status");
        status.setTextColor(Color.BLACK);
        status.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        status.setPadding(5, 5, 5, 0);
        tableRow.addView(status);
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
        textViewName.setText("-------------------------------------");
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

        // Address column
        brandName = new TextView(this);
        brandName.setText("------------------");
        brandName.setTextColor(Color.BLACK);
        brandName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        brandName.setPadding(5, 5, 5, 0);
        tableRow.addView(brandName);

        // Address column
        status = new TextView(this);
        status.setText("------------------");
        status.setTextColor(Color.BLACK);
        status.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        status.setPadding(5, 5, 5, 0);
        tableRow.addView(status);
        tableLayout.addView(tableRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
    }

    private void fillData() {
        for (int i = 0; i < listProducts.size(); i++) {
            SubCategory subCategory = subCategoryDao.getOneByProduct(listProducts.get(i).getId());
            Brand brand = brandDao.getOneByProduct(listProducts.get(i).getId());
            Status status = statusDao.getOne(listProducts.get(i).getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TableRow currentRow = (TableRow) v;
                    TextView textViewID = (TextView) currentRow.getChildAt(0);
                    Product product = listProducts.get(Integer.valueOf(textViewID.getText().toString()) - 1);
                    Intent intent = new Intent(ProductAdminActivity.this, DetailProductAdminActivity.class);
                    intent.putExtra("product", product);
                    intent.putExtra("status", status);
                    intent.putExtra("subCategory", subCategory);
                    intent.putExtra("brand", brand);
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
            textViewName.setText(listProducts.get(i).getProductName());
            textViewName.setTextColor(Color.BLACK);
            textViewName.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            textViewName.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewName);

            // Address column
            TextView textViewAddress = new TextView(this);
            textViewAddress.setText(String.valueOf(listProducts.get(i).getQuantity()));
            textViewAddress.setTextColor(Color.BLACK);
            textViewAddress.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            textViewAddress.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewAddress);

            // Address column
            TextView brandName = new TextView(this);
            brandName.setText(brand.getBrandName()
            );
            brandName.setTextColor(Color.BLACK);
            brandName.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            brandName.setPadding(5, 5, 5, 0);
            tableRow.addView(brandName);

            // Address column
            TextView statusTxt = new TextView(this);
            statusTxt.setText(status.getStatusName());
            statusTxt.setTextColor(Color.BLACK);
            statusTxt.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            statusTxt.setPadding(5, 5, 5, 0);
            tableRow.addView(statusTxt);
            tableLayout.addView(tableRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }
    }
}
