package com.example.foodapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.ByteArrayOutputStream;

public class DetailsActivity extends AppCompatActivity {

    ImageView plusIV, minusIV,featureIV, greeksaladIv, spinachIv, backIV;
    TextView countVal, itemTitle, itemVal, saladTitle, spinachTitle;
    RadioGroup radioGroup;
    RadioButton saladValRbtn, spinachValRbtn;
    AppCompatButton addToCartBtn;
    int count = 1;
    private static final String TAG = "DetailsActivity";
    Bundle bundle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backIV=findViewById(R.id.backIV);

        plusIV=findViewById(R.id.plusIV);
        minusIV=findViewById(R.id.minusIV);
        featureIV=findViewById(R.id.featureImgV);
        greeksaladIv=findViewById(R.id.saladImgV);
        spinachIv=findViewById(R.id.spinachIV);

        itemTitle=findViewById(R.id.item_title);
        itemVal=findViewById(R.id.itemVal);
        countVal=findViewById(R.id.countValTv);

        radioGroup = findViewById(R.id.radioGroup);
        saladValRbtn=findViewById(R.id.greeksaladRbtn);
        spinachValRbtn=findViewById(R.id.spinachRbtn);

        saladTitle=findViewById(R.id.saladTitleTv);
        spinachTitle=findViewById(R.id.spinachTitleTv);

        addToCartBtn=findViewById(R.id.addtoCartBtn);

        // Retrieve imageResource from intent extras
        int imageResource = getIntent().getIntExtra("imageResource", 0);

        if (imageResource != 0) {
            featureIV.setImageResource(imageResource);
        } else {
            // Handle case where imageResource is not provided or invalid
            Toast.makeText(this, "Image resource not found", Toast.LENGTH_SHORT).show();
        }

        String title = getIntent().getStringExtra("title");
        if (title!=null){
            itemTitle.setText(title);
        } else {
            // Handle case where imageResource is not provided or invalid
            Toast.makeText(this, "Title not found", Toast.LENGTH_SHORT).show();
        }

        itemVal.setText("950");

        // Set initial count value
        countVal.setText(String.valueOf(count));


        backIV.setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));


        // Set up click listener for plusIV
        plusIV.setOnClickListener(v -> {
            count++;
            countVal.setText(String.valueOf(count));
        });

        // Set up click listener for minusIV
        minusIV.setOnClickListener(v -> {
            if (count > 0) {
                count--;
            }
            countVal.setText(String.valueOf(count));
        });

        addToCartBtn.setOnClickListener(v -> {
            Bundle args = new Bundle();

            int saladImg=0;
            String secTitle=" ", secPrice=" ";
            if (saladValRbtn.isChecked()){
                saladImg=R.drawable.greeksalad64px;
                secTitle=saladTitle.getText().toString();
                secPrice=saladValRbtn.getText().toString();
            } else if (spinachValRbtn.isChecked()){
                saladImg=R.drawable.spinach64px;
                secTitle=spinachTitle.getText().toString();
                secPrice=spinachValRbtn.getText().toString();
            }

            // Example: Passing other data
            args.putString("itemTitle", itemTitle.getText().toString());
            args.putString("itemVal", itemVal.getText().toString());
            args.putString("count", countVal.getText().toString());

            // Start CartActivity
            Intent intent = new Intent(DetailsActivity.this, CartActivity.class);

            if(saladImg != 0){
                intent.putExtra("secImg",saladImg);
                args.putString("secTitle", secTitle);
                args.putString("secPrice",secPrice);
            }

            intent.putExtra("imageResource1",imageResource);
            intent.putExtras(args);
            startActivity(intent);
        });


    }
}