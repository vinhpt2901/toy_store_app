package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.CategoryDao;
import com.dao.ImageCategoryDao;
import com.jdbc.RoomConnection;
import com.model.Category;
import com.model.ImageCategory;
import com.shopee.R;

public class AddCategoryAdminActivity extends AppCompatActivity {
    private EditText cateCode, cateName;
    private Button addCate;
    private RoomConnection roomConnection;
    private CategoryDao categoryDao;
    private ImageCategoryDao imageCategoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_admin);
        cateCode = findViewById(R.id.categoryCode);
        cateName = findViewById(R.id.categoryName);
        addCate = findViewById(R.id.btnUpdate);

        roomConnection = getInstance(this);
        categoryDao = roomConnection.categoryDao();
        imageCategoryDao = roomConnection.imageCategoryDao();

        addCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add cate
                int cateID = (int) categoryDao.add(new Category(cateCode.getText().toString(), cateName.getText().toString()));

                // add image
                imageCategoryDao.add(new ImageCategory(cateID, ""));
                finish();
            }
        });
    }
}