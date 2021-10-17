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

import com.google.android.material.card.MaterialCardView;
import com.model.Product;
import com.shopee.DetailProductActivity;
import com.shopee.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ListProductsAdapter extends RecyclerView.Adapter<ListProductsAdapter.MyViewHolder> {
    private Context context;
    private List<Product> listProduct;
    private List<String> listImage;


    public ListProductsAdapter(Context context, List<Product> listProduct, List<String> listImage) {
        this.context = context;
        this.listProduct = listProduct;
        this.listImage = listImage;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView imageView;
        MaterialCardView materialCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            imageView = itemView.findViewById(R.id.imageViewProduct);
            materialCardView = itemView.findViewById(R.id.itemProductLayout);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.get_all_product_layout, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

}
