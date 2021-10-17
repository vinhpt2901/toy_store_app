package com.toy_store_app.admin;

import static com.jdbc.RoomConnection.getInstance;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.CategoryDao;
import com.dao.ImageSubcateDao;
import com.dao.SubCategoryDao;
import com.jdbc.RoomConnection;
import com.model.Category;
import com.model.ImageSubCate;
import com.model.SubCategory;
import com.shopee.R;

import java.util.List;

public class AddSubCategoryAdminActivity extends AppCompatActivity {
    private EditText cateCode, cateName;
    private Button addCate;
    private RoomConnection roomConnection;
    private SubCategoryDao categoryDao;
    private ImageSubcateDao imageCategoryDao;
    private Spinner spinner;
    private CategoryDao c;
    private List<Category> lists;
    private ArrayAdapter<Category> listStatusOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_category_admin);
        cateCode = findViewById(R.id.subCateCode);
        cateName = findViewById(R.id.subCateName);
        spinner = findViewById(R.id.cateSpinner);
        addCate = findViewById(R.id.btnUpdate);

        roomConnection = getInstance(this);
        categoryDao = roomConnection.subCategoryDao();
        imageCategoryDao = roomConnection.imageSubcateDao();
        c = roomConnection.categoryDao();
        lists = c.getAll();

        listStatusOrder = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, lists);
        spinner.setAdapter(listStatusOrder);

        addCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category c = (Category) spinner.getSelectedItem();
                SubCategory s = new SubCategory(c.getId(), cateCode.getText().toString(), cateName.getText().toString());
                int sID = (int) categoryDao.add(s);

                imageCategoryDao.add(new ImageSubCate(sID, ""));
                finish();
            }
        });
    }
}