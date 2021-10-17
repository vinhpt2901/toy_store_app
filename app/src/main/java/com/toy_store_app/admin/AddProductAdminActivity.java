package com.toy_store_app.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.BrandDao;
import com.dao.ImageDao;
import com.dao.ProductDao;
import com.dao.StatusDao;
import com.dao.SubCategoryDao;
import com.jdbc.RoomConnection;
import com.model.Brand;
import com.model.Image;
import com.model.Product;
import com.model.Status;
import com.model.SubCategory;
import com.shopee.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddProductAdminActivity extends AppCompatActivity {
    private EditText etCode;
    private EditText etName;
    private EditText etSellPrice;
    private EditText etOriginPrice;
    private EditText etQuantity;
    private EditText etDescription;
    private Spinner spBrand;
    private Spinner spSubCate;

    private RoomConnection roomConnection;
    private BrandDao brandDao;
    private SubCategoryDao subCategoryDao;
    private ArrayAdapter<Brand> brandArrayAdapter;
    private ArrayAdapter<SubCategory> subCategoryArrayAdapter;
    private ProductDao productDao;
    private Button btn;
    private String currentDate;
    private ImageDao imageDao;
    private StatusDao statusDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_admin);
        etCode = findViewById(R.id.productCode);
        etName = findViewById(R.id.productName);
        etSellPrice = findViewById(R.id.sellPrice);
        spBrand = findViewById(R.id.brand);
        etOriginPrice = findViewById(R.id.originPrice);
        etQuantity = findViewById(R.id.quantity);
        etDescription = findViewById(R.id.description);
        spSubCate = findViewById(R.id.subCate);
        btn = findViewById(R.id.btnAdd);
        currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        roomConnection = RoomConnection.getInstance(this);
        brandDao = roomConnection.brandDao();
        subCategoryDao = roomConnection.subCategoryDao();
        productDao = roomConnection.productDao();
        imageDao = roomConnection.imageDao();
        statusDao = roomConnection.statusDao();
        List<Brand> brandList = brandDao.getAll();
        brandArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, brandList);
        spBrand.setAdapter(brandArrayAdapter);

        List<SubCategory> subCategoryList = subCategoryDao.getAll();
        subCategoryArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, subCategoryList);
        spSubCate.setAdapter(subCategoryArrayAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newBrandID = spBrand.getSelectedItemPosition();
                int subCateID = spSubCate.getSelectedItemPosition();
                Product p = new Product(subCateID+1, newBrandID+1, etCode.getText().toString(), etName.getText().toString(), Integer.valueOf(etQuantity.getText().toString()),
                        Double.valueOf(etSellPrice.getText().toString()), Integer.valueOf(etOriginPrice.getText().toString()), "", etDescription.getText().toString(), currentDate);
                int productID = (int) productDao.add(p);

                // add image
                imageDao.add(new Image(productID, "anh41.jpg", true));
                imageDao.add(new Image(productID, "anh42.jpg", false));
                imageDao.add(new Image(productID, "anh43.jpg", false));

                // add status
                statusDao.add(new Status(productID, 0, "Sell"));
                finish();
            }
        });
    }
}