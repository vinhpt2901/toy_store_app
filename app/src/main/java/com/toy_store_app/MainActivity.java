

package com.toy_store_app;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.loadLocale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.CategoryMainAdapter;
import com.adapter.ListProductsAdapter;
import com.dao.CategoryDao;
import com.dao.ImageCategoryDao;
import com.dao.ImageDao;
import com.dao.ProductDao;
import com.jdbc.RoomConnection;
import com.model.Category;
import com.model.Product;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CarouselView carouselView;
    private int[] lists;

    private TextView moreInfoNewProduct;
    private TextView moreInfoHotProduct;
    private TextView moreInfoAllProduct;
    private TextView moreCategory;

    private RoomConnection roomConnection;
    private ProductDao productDao;
    private ImageDao imageDao;
    private CategoryDao categoryDao;
    private ImageCategoryDao imageCategoryDao;

    private List<Product> listProductHot;
    private List<Product> listProductNew;
    private List<Product> listProduct;
    private List<Category> listCate;
    private List<String> listImageCate;
    private List<String> images;
    private List<String> imagesHot;
    private List<String> imagesNew;

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewHot;
    private RecyclerView recyclerViewList;
    private RecyclerView recyclerViewCate;

    private LinearLayoutManager layoutManager;
    private LinearLayoutManager layoutManagerHot;
    private LinearLayoutManager layoutManagerNew;
    private LinearLayoutManager layoutManagerCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_main);

        findInstanceInView();

        layoutManagerHot = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManagerNew = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManagerCate = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // get data for list
        lists = new int[]{R.drawable.home1, R.drawable.home2, R.drawable.home3, R.drawable.home4, R.drawable.home5};
        carouselView.setPageCount(lists.length);

        // set data to carouse view
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.valueOf("CENTER_CROP"));
                imageView.setImageResource(lists[position]);
            }
        });

        // More information of Get All Product
        moreInfoAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.shopee.GetAllProductActivity.class);
                startActivity(intent);
            }
        });

        // More information of Hot Product
        moreInfoHotProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.shopee.HotProductActivity.class);
                startActivity(intent);
            }
        });

        // More information of Get New Product
        moreInfoNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                startActivity(intent);
            }
        });

        // More cate
        moreCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryProductActivity.class);
                startActivity(intent);
            }
        });
        roomConnection = getInstance(getApplicationContext());
        productDao = roomConnection.productDao();
        imageDao = roomConnection.imageDao();
        categoryDao = roomConnection.categoryDao();
        imageCategoryDao = roomConnection.imageCategoryDao();

        // New product
        listProductNew = productDao.getNewProductMain();
        imagesNew = new ArrayList<>();
        for (Product p : listProductNew) {
            imagesNew.add(imageDao.getImageByProductCoverTrue(p.getId()));
        }
        loadDataForRecyclerView(recyclerView, listProductNew, layoutManagerNew, imagesNew);

        // Hot product
        listProductHot = productDao.getHotProductMain();
        imagesHot = new ArrayList<>();
        for (Product p : listProductHot) {
            imagesHot.add(imageDao.getImageByProductCoverTrue(p.getId()));
        }
        loadDataForRecyclerView(recyclerViewHot, listProductHot, layoutManagerHot, imagesHot);

        // All Product
        listProduct = productDao.getAllMain();
        images = new ArrayList<>();
        for (Product p : listProduct) {
            images.add(imageDao.getImageByProductCoverTrue(p.getId()));
        }
        loadDataForRecyclerView(recyclerViewList, listProduct, layoutManager, images);

        listCate = categoryDao.getAll();
        listImageCate = new ArrayList<>();
        for (Category p : listCate) {
            listImageCate.add(imageCategoryDao.getImageByProductCoverTrue(p.getId()));
        }
        if (listCate.size() > 0) {
            CategoryMainAdapter listProductAllAdapter = new CategoryMainAdapter(this, listCate, listImageCate);
            recyclerViewCate.setAdapter(listProductAllAdapter);
            recyclerViewCate.setLayoutManager(layoutManagerCate);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }

    /**
     * Load all instance need in view
     */
    private void findInstanceInView() {
        recyclerView = findViewById(R.id.listNewProductMain);
        recyclerViewHot = findViewById(R.id.listHotProductMain);
        recyclerViewList = findViewById(R.id.listsProductMain);
        carouselView = findViewById(R.id.carouselView);
        moreInfoAllProduct = findViewById(R.id.card2138);
        moreInfoHotProduct = findViewById(R.id.moreInfoAllProduct);
        moreCategory = findViewById(R.id.moreCategory);
        moreInfoNewProduct = findViewById(R.id.moreNewProduct);
        recyclerViewCate = findViewById(R.id.listCategoryMain);
    }

    /**
     * Load data for recycler view
     *
     * @param recyclerView:  recycler view want to show
     * @param listProduct:   list product want to list
     * @param layoutManager: layout
     * @param listImages:    list image want to list
     */
    private void loadDataForRecyclerView(RecyclerView recyclerView, List<Product> listProduct, LinearLayoutManager layoutManager, List<String> listImages) {
        if (listProduct.size() > 0) {
            ListProductsAdapter listProductAllAdapter = new ListProductsAdapter(this, listProduct, listImages);
            recyclerView.setAdapter(listProductAllAdapter);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
}
