package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartRecItemAdapter extends RecyclerView.Adapter<CartRecItemAdapter.ItemViewHolder>{

    private static Context context;
    private List<CartRecItem> itemList;

    public CartRecItemAdapter(Context context, List<CartRecItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_rec_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CartRecItem item = itemList.get(position);

        // Set data to views
        holder.foodImg.setImageResource(item.getImageResource());
        holder.titleVal.setText(item.getItemTitle());
        holder.priceVal.setText(item.getItemPrice());
        holder.countVal.setText(item.getCountValue());

        holder.contentLayout.setOnClickListener(v -> {
//            context.startActivity(new Intent(context, DetailsActivity.class));
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImg;
        TextView titleVal, priceVal, countVal;
        LinearLayout contentLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            contentLayout=itemView.findViewById(R.id.itemll);

            foodImg = itemView.findViewById(R.id.item_img);
            titleVal = itemView.findViewById(R.id.item_title);
            priceVal=itemView.findViewById(R.id.item_price);
            countVal=itemView.findViewById(R.id.countVal);
        }
    }

}
