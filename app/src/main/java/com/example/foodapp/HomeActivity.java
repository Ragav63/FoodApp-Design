package com.example.foodapp;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView featureRv, lookingRv;
    private LinearLayoutManager linearLayoutManager;
    private FeatureRecItemAdapter featureRecItemAdapter;
    private LookingRecItemAdapter lookingRecItemAdapter;
    private List<FeatureRecItem> itemList;
    private List<LookingRecItem> lookingRecItemList;

    private List<FeatureRecItem> generateItemList() {
        List<FeatureRecItem> itemFeatureList = new ArrayList<>();
        itemFeatureList.add(new FeatureRecItem(R.drawable.eggsalad,"Egg Salad"));
        itemFeatureList.add(new FeatureRecItem(R.drawable.burgerloaded,"Burger Kings"));
        itemFeatureList.add(new FeatureRecItem(R.drawable.burgerfriesimg,"KFC"));

        return itemFeatureList;
    }

    private List<LookingRecItem> generateLookingRecItemList() {
        List<LookingRecItem> itemLookingList = new ArrayList<>();
        itemLookingList.add(new LookingRecItem(R.drawable.crab64px, "Spacy fresh crab"));
        itemLookingList.add(new LookingRecItem(R.drawable.bruschetta64px, "Bruchetta"));

        return itemLookingList;
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        featureRv = findViewById(R.id.recVItems);
        lookingRv=findViewById(R.id.lookingRecVItems);

        featureRv.setLayoutManager(new LinearLayoutManager(this));
        lookingRv.setLayoutManager(new LinearLayoutManager(this));

        // Initialize data for RecyclerView
        itemList = generateItemList();
        lookingRecItemList=generateLookingRecItemList();

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        featureRecItemAdapter = new FeatureRecItemAdapter(this, itemList);
        lookingRecItemAdapter=new LookingRecItemAdapter(this,lookingRecItemList);

        featureRv.setLayoutManager(linearLayoutManager);

        // Set adapter to RecyclerView
        featureRv.setAdapter(featureRecItemAdapter);
        lookingRv.setAdapter(lookingRecItemAdapter);


    }
}