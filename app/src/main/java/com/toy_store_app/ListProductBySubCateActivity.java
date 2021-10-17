package com.toy_store_app;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.loadLocale;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.ListProductsAdapter;
import com.dao.ImageDao;
import com.dao.ProductDao;
import com.jdbc.RoomConnection;
import com.model.Product;
import com.model.SubCategory;
import com.util.Helper;

import java.util.ArrayList;
import java.util.List;

public class ListProductBySubCateActivity extends AppCompatActivity {
    private RoomConnection roomConnection;
    private ProductDao productDao;
    private ImageDao imageDao;
    private List<Product> listProduct;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewSecond;
    private List<String> listImage;
    private List<String> listImageOdd;
    private List<Product> listEven;
    private List<Product> listOdd;
    private int subCateID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_list_product_by_sub_cate);
        recyclerView = findViewById(R.id.listProductBySub);
        recyclerViewSecond = findViewById(R.id.listProductBySubSecond);
        SubCategory subCategory = (SubCategory) getIntent().getSerializableExtra("subCate");
        if (subCategory != null) {
            subCateID = subCategory.getId();
        } else {
            subCateID = 1;
        }
        roomConnection = getInstance(getApplicationContext());
        productDao = roomConnection.productDao();

        listProduct = productDao.getProductBySubCate(subCateID);
        imageDao = roomConnection.imageDao();

        listEven = new Helper().getListProductByIndex(listProduct, 1);
        listImage = new ArrayList<>();
        for (Product p : listEven) {
            listImage.add(imageDao.getImageByProductCoverTrue(p.getId()));
        }

        listOdd = new Helper().getListProductByIndex(listProduct, 2);
        listImageOdd = new ArrayList<>();
        for (Product p : listOdd) {
            listImageOdd.add(imageDao.getImageByProductCoverTrue(p.getId()));
        }

        ListProductsAdapter listProductsAdapter = new ListProductsAdapter(this, listEven, listImage);
        recyclerView.setAdapter(listProductsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListProductsAdapter listProductsAdapterSecond = new ListProductsAdapter(this, listOdd, listImageOdd);
        recyclerViewSecond.setAdapter(listProductsAdapterSecond);
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(this));

        // Close scoll of recyclerview
        recyclerView.setNestedScrollingEnabled(false);
        recyclerViewSecond.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}