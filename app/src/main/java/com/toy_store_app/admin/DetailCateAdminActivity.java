package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.CategoryDao;
import com.jdbc.RoomConnection;
import com.model.Category;
import com.shopee.R;

public class DetailCateAdminActivity extends AppCompatActivity {
    private TextView cateCode;
    private EditText cateName;
    private Button btnUpdate;
    private RoomConnection roomConnection;
    private CategoryDao categoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cate_admin);
        cateCode = findViewById(R.id.categoryCode);
        cateName = findViewById(R.id.categoryName);
        btnUpdate = findViewById(R.id.btnUpdate);

        roomConnection = getInstance(this);
        categoryDao = roomConnection.categoryDao();

        Category category = (Category) getIntent().getSerializableExtra("category");
        cateCode.setText(category.getCategoryCode());
        cateName.setText(category.getCategoryName());
        cateName.setSelection(cateName.getText().toString().length());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update category Name
                category.setCategoryName(cateName.getText().toString());
                categoryDao.update(category);
                finish();
            }
        });
    }
}