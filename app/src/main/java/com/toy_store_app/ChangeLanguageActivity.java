package com.toy_store_app;

import static com.util.Helper.loadLocale;
import static com.util.Helper.setLocale;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeLanguageActivity extends AppCompatActivity {
    private RadioButton vietNameBtn;
    private RadioButton englishBtn;
    private TextView saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_change_language);
        vietNameBtn = findViewById(R.id.vietNameGroup);
        englishBtn = findViewById(R.id.englishGroup);
        saveBtn = findViewById(R.id.txtSaveData);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vietNameBtn.isChecked()) {
                    // tieng viet
                    setLocale(getBaseContext(), "Language", "My_Lang", "vi");
                } else {
                    // tieng anh
                    setLocale(getBaseContext(), "Language", "My_Lang", "en");
                }
                recreate();
                finish();
            }
        });
    }

}