package com.fragment;

import static com.util.Helper.getSavedObjectFromPreference;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.model.AccountDetail;
import com.shopee.CartActivity;
import com.shopee.LoginActivity;
import com.shopee.MessageActivity;
import com.shopee.R;
import com.shopee.SearchProduct;

public class HeaderFragement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.header, container, false);
        // event of image
        SearchView searchView = view.findViewById(R.id.homeSearchView);
        ImageView imageCart = view.findViewById(R.id.homeCart);
        ImageView imageMessage = view.findViewById(R.id.homeChatHeader);

        imageCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDetail accountDetail = getSavedObjectFromPreference(getContext(), "mPreference", "account", AccountDetail.class);
                Intent intent;
                if (accountDetail != null) {
                    intent = new Intent(getContext(), CartActivity.class);
                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                }
                startActivity(intent);
            }
        });
        imageMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDetail accountDetail = getSavedObjectFromPreference(getContext(), "mPreference", "account", AccountDetail.class);
                Intent intent;
                if (accountDetail != null) {
                    intent = new Intent(getContext(), MessageActivity.class);
                } else {
                    intent = new Intent(getContext(), LoginActivity.class);
                }
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getContext(), SearchProduct.class);
                intent.putExtra("productName", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;
    }
}
