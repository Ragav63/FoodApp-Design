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

public class FeatureRecItemAdapter extends RecyclerView.Adapter<FeatureRecItemAdapter.ItemViewHolder>{

    private static Context context;
    private List<FeatureRecItem> itemList;

    public FeatureRecItemAdapter(Context context, List<FeatureRecItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_rec_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        FeatureRecItem item = itemList.get(position);

        // Set data to views
        holder.foodImg.setImageResource(item.getImageResource());
        holder.titleVal.setText(item.getItemTitle());

        int data=item.getImageResource();
        String title=item.getItemTitle();

        holder.contentLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("imageResource",data);
            intent.putExtra("title",title);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImg;
        TextView titleVal;
        LinearLayout contentLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            contentLayout=itemView.findViewById(R.id.itemll);

            foodImg = itemView.findViewById(R.id.featureImgV);
            titleVal = itemView.findViewById(R.id.item_title);


        }
    }

}
