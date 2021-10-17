package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.BrandDao;
import com.dao.ImageDao;
import com.dao.ProductDao;
import com.dao.SubCategoryDao;
import com.jdbc.RoomConnection;
import com.model.Brand;
import com.model.Image;
import com.model.Product;
import com.model.SubCategory;
import com.shopee.R;

import java.util.List;

public class DetailProductAdminActivity extends AppCompatActivity {
    private TextView productCode;
    private EditText productName, sellPrice, originPrice, quantity, description;
    private Spinner brand, subCate;
    private Button btnUpdate, btnDelete;
    private RoomConnection roomConnection;
    private ProductDao productDao;
    private BrandDao brandDao;
    private SubCategoryDao subCategoryDao;
    private ArrayAdapter<Brand> listBrandAdapter;
    private List<Brand> listBrand;
    private ArrayAdapter<SubCategory> listSubAdapter;
    private List<SubCategory> listSub;
    private List<Image> listImage;
    private ImageDao imageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product_admin);
        getInstanceView();

        Product product = (Product) getIntent().getSerializableExtra("product");
        SubCategory subCategory = (SubCategory) getIntent().getSerializableExtra("subCategory");
        Brand brandObj = (Brand) getIntent().getSerializableExtra("brand");

        roomConnection = getInstance(this);
        productDao = roomConnection.productDao();
        brandDao = roomConnection.brandDao();
        subCategoryDao = roomConnection.subCategoryDao();
        listBrand = brandDao.getAll();
        listSub = subCategoryDao.getAll();
        imageDao = roomConnection.imageDao();
        listImage = imageDao.getImageByProductCover(product.getId());

        // set data
        productCode.setText(product.getProductCode());
        productName.setText(product.getProductName());
        sellPrice.setText(String.valueOf((int) product.getSellPrice()));
        originPrice.setText(String.valueOf((int) product.getOriginPrice()));
        quantity.setText(String.valueOf(product.getQuantity()));
        description.setText(product.getDescription());

        // set all adapter
        listBrandAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listBrand);
        brand.setAdapter(listBrandAdapter);
        int index = brandObj.getId();
        brand.setSelection(index - 1);

        listSubAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listSub);
        subCate.setAdapter(listSubAdapter);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update product
                int newBrandID = brand.getSelectedItemPosition();
                int subCateID = subCate.getSelectedItemPosition();
                product.setProductName(productName.getText().toString());
                product.setDescription(description.getText().toString());
                product.setBrandID(newBrandID);
                product.setSubCateID(subCateID);
                product.setSellPrice(Double.valueOf(sellPrice.getText().toString()));
                product.setOriginPrice(Integer.valueOf(originPrice.getText().toString()));
                product.setQuantity(Integer.valueOf(quantity.getText().toString()));

                productDao.update(product);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // remove image
                for (Image i : listImage) {
                    imageDao.delete(i);
                }
                // remove product
                productDao.delete(product);
                finish();
            }
        });
    }

    private void getInstanceView() {
        productCode = findViewById(R.id.productCode);
        productName = findViewById(R.id.productName);
        brand = findViewById(R.id.brand);
        sellPrice = findViewById(R.id.sellPrice);
        originPrice = findViewById(R.id.originPrice);
        quantity = findViewById(R.id.quantity);
        subCate = findViewById(R.id.subCate);
        description = findViewById(R.id.description);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
    }
}