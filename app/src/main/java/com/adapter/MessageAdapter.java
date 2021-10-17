package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shopee.R;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private String data1[];
    private String data2[];
    private Context context;
    private int images[];


    public MessageAdapter(Context context, String[] data1, String[] data2, int[] images) {
        this.data1 = data1;
        this.data2 = data2;
        this.context = context;
        this.images = images;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleAvatarMessage);
            description = itemView.findViewById(R.id.descriptionAvatarMessage);
            imageView = itemView.findViewById(R.id.imageAvatarMessage);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.message_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(data1[position]);
        holder.description.setText(data2[position]);
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }


}
