package com.toy_store_app;

import static com.jdbc.RoomConnection.getInstance;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.ListProductsAdapter;
import com.dao.ImageDao;
import com.dao.ProductDao;
import com.jdbc.RoomConnection;
import com.model.Product;
import com.util.Helper;

import java.util.ArrayList;
import java.util.List;

public class SearchProduct extends AppCompatActivity {
    private RoomConnection roomConnection;
    private ProductDao productDao;
    private ImageDao imageDao;
    private List<Product> listProduct;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewSecond;
    private List<String> listImageEven;
    private List<String> listImageOdd;
    private List<Product> listEven;
    private List<Product> listOdd;
    private TextView notFound;
    private TextView productOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        String searchName = getIntent().getStringExtra("productName");
        recyclerView = findViewById(R.id.listProduct);
        recyclerViewSecond = findViewById(R.id.listProductSecond);
        notFound = findViewById(R.id.notfound);
        productOther = findViewById(R.id.cacSanPham);

        roomConnection = getInstance(getApplicationContext());
        productDao = roomConnection.productDao();

        listProduct = productDao.searchProductByProductName(searchName);

        // Check list have product Name
        if (listProduct.size() > 0) {
            imageDao = roomConnection.imageDao();

            listEven = new Helper().getListProductByIndex(listProduct, 2);
            listImageEven = new ArrayList<>();
            for (Product p : listEven) {
                listImageEven.add(imageDao.getImageByProductCoverTrue(p.getId()));
            }
            ListProductsAdapter listProductsAdapterSecond = new ListProductsAdapter(this, listEven, listImageEven);
            recyclerViewSecond.setAdapter(listProductsAdapterSecond);
            recyclerViewSecond.setLayoutManager(new LinearLayoutManager(this));

            listOdd = new Helper().getListProductByIndex(listProduct, 1);
            listImageOdd = new ArrayList<>();
            for (Product p : listOdd) {
                listImageOdd.add(imageDao.getImageByProductCoverTrue(p.getId()));
            }
            ListProductsAdapter listProductsAdapter = new ListProductsAdapter(this, listOdd, listImageOdd);
            recyclerView.setAdapter(listProductsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));


            // Close scoll of recyclerview
            recyclerView.setNestedScrollingEnabled(false);
            recyclerViewSecond.setNestedScrollingEnabled(false);
        } else {
            notFound.setText("Not found product:  " + searchName);
            productOther.setText("Product other");
            listProduct = productDao.getAll();
            imageDao = roomConnection.imageDao();

            listEven = new Helper().getListProductByIndex(listProduct, 1);
            listImageEven = new ArrayList<>();
            for (Product p : listEven) {
                listImageEven.add(imageDao.getImageByProductCoverTrue(p.getId()));
            }
            listOdd = new Helper().getListProductByIndex(listProduct, 2);
            listImageOdd = new ArrayList<>();
            for (Product p : listOdd) {
                listImageOdd.add(imageDao.getImageByProductCoverTrue(p.getId()));
            }

            ListProductsAdapter listProductsAdapter = new ListProductsAdapter(this, listOdd, listImageOdd);
            recyclerView.setAdapter(listProductsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ListProductsAdapter listProductsAdapterSecond = new ListProductsAdapter(this, listEven, listImageEven);
            recyclerViewSecond.setAdapter(listProductsAdapterSecond);
            recyclerViewSecond.setLayoutManager(new LinearLayoutManager(this));

            // Close scoll of recyclerview
            recyclerView.setNestedScrollingEnabled(false);
            recyclerViewSecond.setNestedScrollingEnabled(false);
        }

    }
}