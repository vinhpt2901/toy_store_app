package com.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.AddToCardAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.Card;
import com.shopee.ConfirmInforActivity;
import com.shopee.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCardFragment extends Fragment {
    double total;
    private RecyclerView recyclerView;
    private List<Card> cards;
    private TextView totalPrice;
    private Button btnMuaHang;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListCardFragment newInstance(String param1, String param2) {
        ListCardFragment fragment = new ListCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.listCardProduct);
        totalPrice = view.findViewById(R.id.txtTotalPriceCart);
        btnMuaHang = view.findViewById(R.id.btnMuaHang);
        cards = loadCart();
        total = 0;
        for (Card c : cards) {
            total += c.getQuantity() * c.getSellPrice();
        }
        totalPrice.setText(String.valueOf(total));
        AddToCardAdapter addToCardAdapter = new AddToCardAdapter(getContext(), cards);
        addToCardAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(addToCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ConfirmInforActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Card> loadCart() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("Carts", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("listCart", null);
        Type type = new TypeToken<ArrayList<Card>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}