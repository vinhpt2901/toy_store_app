package com.toy_store_app;

import static com.util.Helper.loadLocale;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fragment.CardEmptyFragment;
import com.fragment.ListCardFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.Card;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private List<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_cart);
        cards = loadCart();

        // add fragment card
        CardEmptyFragment cardEmptyFragment = new CardEmptyFragment();
        ListCardFragment listCardFragment = new ListCardFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (cards != null && cards.size() != 0) {
            // co san pham
            transaction.replace(R.id.layoutCard, listCardFragment, listCardFragment.getTag()).commit();

        } else {
            // check user have card or not : use shared references
            // empty
            cards = new ArrayList<>();
            transaction.replace(R.id.layoutCard, cardEmptyFragment, cardEmptyFragment.getTag()).commit();
        }

    }

    private List<Card> loadCart() {
        SharedPreferences preferences = getSharedPreferences("Carts", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("listCart", null);
        Type type = new TypeToken<ArrayList<Card>>() {
        }.getType();
        return (List) gson.fromJson(json, type);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
    }
}