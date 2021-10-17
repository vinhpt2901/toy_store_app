package com.toy_store_app;

import static com.util.Helper.loadLocale;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.fragment.CategoryFragement;
import com.fragment.ListSubCategoryFragment;
import com.model.Category;

public class CategoryProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_category_product);

        // add fragment category
        CategoryFragement categoryFragement = new CategoryFragement();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.layoutCategory, categoryFragement, categoryFragement.getTag()).commit();

        // add fragment sub category
        ListSubCategoryFragment subCategoryFragment = new ListSubCategoryFragment();
        manager.beginTransaction().replace(R.id.layoutSubCategory, subCategoryFragment, subCategoryFragment.getTag()).commit();

        // truyen cateID to subcate fragment
        Bundle bundle = new Bundle();
        // Check category
        Category category = (Category) getIntent().getSerializableExtra("category");
        if (category != null) {
            bundle.putInt("cateID", category.getId());
        } else {
            bundle.putInt("cateID", 1);
        }
        subCategoryFragment.setArguments(bundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }

}