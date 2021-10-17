package com.adapter;

import static com.util.Helper.formatNumber;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toy_store_app.R;
import com.google.android.material.card.MaterialCardView;
import com.model.Product;
import com.toy_store_app.DetailProductActivity;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ListProductTwoColumAdapter extends RecyclerView.Adapter<ListProductTwoColumAdapter.MyViewHolder> {
    private Context context;
    private List<Product> listProduct;
    private List<Product> listProductSecond;
    private List<String> listImage;
    private List<String> listImageSecond;

    public ListProductTwoColumAdapter(Context context, List<Product> listProduct, List<Product> listProductSecond, List<String> listImage, List<String> listImageSecond) {
        this.context = context;
        this.listProduct = listProduct;
        this.listProductSecond = listProductSecond;
        this.listImage = listImage;
        this.listImageSecond = listImageSecond;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView imageView;
        MaterialCardView materialCardView;
        TextView productNameSecond;
        TextView productPriceSecond;
        ImageView imageViewSecond;
        MaterialCardView materialCardViewSecond;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            imageView = itemView.findViewById(R.id.imageViewProduct);
            materialCardView = itemView.findViewById(R.id.itemProductLayout);

            productNameSecond = itemView.findViewById(R.id.productNameSecond);
            productPriceSecond = itemView.findViewById(R.id.productPriceSecond);
            imageViewSecond = itemView.findViewById(R.id.imageViewProductSecond);
            materialCardViewSecond = itemView.findViewById(R.id.itemProductLayoutSecond);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_product_custom_two_row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.productName.setText(listProduct.get(position).getProductName());
        int price = (int) listProduct.get(position).getSellPrice();
        holder.productPrice.setText(formatNumber(price));
        try {
            AssetManager assetManager = context.getAssets();
            InputStream ims = assetManager.open(listImage.get(position));
            Drawable d = Drawable.createFromStream(ims, null);
            holder.imageView.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }

        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                Product product = listProduct.get(position);
                intent.putExtra("product", product);
                context.startActivity(intent);

            }
        });
        holder.productNameSecond.setText(listProductSecond.get(position).getProductName());
        int priceSe = (int) listProductSecond.get(position).getSellPrice();
        holder.productPriceSecond.setText(formatNumber(priceSe));
        try {
            AssetManager assetManager = context.getAssets();
            InputStream ims = assetManager.open(listImageSecond.get(position));
            Drawable d = Drawable.createFromStream(ims, null);
            holder.imageViewSecond.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }
        holder.materialCardViewSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                Product product = listProductSecond.get(position);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProductSecond.size();
    }

}
