package com.example.foodapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CartActivity extends AppCompatActivity {
    Bundle bundle;
    ImageView itemImg, chitemImg,minusIV, plusIV,chminusIV, chplusIV, backIV, itemremove1, itemremove2;
    TextView itemTitle, itemPrice, itemCount,chitemTitle, chitemPrice, chitemCount, promoCodeTv,subTotal, taxandFees, delivery, totalTv;
    int count, chCount;
    EditText pcodeEdt;
    LinearLayout item1Ll, item2Ll, parentLl;
    AppCompatButton pcodeBtn, checkoutBtn;
    private static final String TAG = "CartActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backIV=findViewById(R.id.backIV);

        itemImg=findViewById(R.id.item_img);
        itemTitle=findViewById(R.id.item_title);
        itemPrice=findViewById(R.id.item_price);
        itemCount=findViewById(R.id.countVal);

        chitemImg=findViewById(R.id.chitemImgV);
        chitemTitle=findViewById(R.id.chitemTitleTv);
        chitemPrice=findViewById(R.id.chitemPriceTv);
        chitemCount=findViewById(R.id.chcountVal);

        minusIV=findViewById(R.id.minusIV);
        plusIV=findViewById(R.id.plusIV);
        chminusIV=findViewById(R.id.chminusIV);
        chplusIV=findViewById(R.id.chplusIV);

        itemremove1=findViewById(R.id.itemremove1);
        itemremove2=findViewById(R.id.itemremove2);

        parentLl=findViewById(R.id.parentll);
        item1Ll=findViewById(R.id.item1ll);
        item2Ll=findViewById(R.id.item2ll);

        pcodeEdt=findViewById(R.id.promoCodeEdt);
        promoCodeTv=findViewById(R.id.promoCodeTv);
        pcodeBtn=findViewById(R.id.pcodeApplyBtn);

        subTotal=findViewById(R.id.subTotalTv);
        taxandFees=findViewById(R.id.taxandFeesTv);
        delivery=findViewById(R.id.deliveryChTv);
        totalTv=findViewById(R.id.totalTv);

        checkoutBtn=findViewById(R.id.checkoutBtn);

        taxandFees.setText("150");
        delivery.setText("40");

        pcodeEdt.setText(null);
        backIV.setOnClickListener(v -> startActivity(new Intent(this, DetailsActivity.class)));

        int imageResource = getIntent().getIntExtra("imageResource1", 0);
        if (imageResource != 0) {
            itemImg.setImageResource(imageResource);
        } else {
            // Handle case where imageResource is not provided or invalid
            Toast.makeText(this, "Image resource not found", Toast.LENGTH_SHORT).show();
        }

        int secImgResource = getIntent().getIntExtra("secImg",0);
        if (secImgResource!=0){
            chitemImg.setImageResource(secImgResource);
        }
        bundle = getIntent().getExtras();

        if (bundle != null) {

            itemTitle.setText(bundle.getString("itemTitle"));
            itemPrice.setText(bundle.getString("itemVal"));
            itemCount.setText(bundle.getString("count"));
            count= Integer.parseInt(itemCount.getText().toString());

            chitemCount.setText(bundle.getString("count"));
            chCount=Integer.parseInt(chitemCount.getText().toString());

            chitemTitle.setText(bundle.getString("secTitle"));
            chitemPrice.setText(bundle.getString("secPrice"));

            // Set up click listener for plusIV
            plusIV.setOnClickListener(v -> {
                count++;
                itemCount.setText(String.valueOf(count));
                updateCalculation();
            });

            // Set up click listener for minusIV
            minusIV.setOnClickListener(v -> {
                if (count > 0) {
                    count--;
                }
                itemCount.setText(String.valueOf(count));
                updateCalculation();
            });

            // Set up click listener for plusIV
            chplusIV.setOnClickListener(v -> {
                chCount++;
                chitemCount.setText(String.valueOf(chCount));
                updateCalculation();
            });

            // Set up click listener for minusIV
            chminusIV.setOnClickListener(v -> {
                if (chCount > 0) {
                    chCount--;
                }
                chitemCount.setText(String.valueOf(chCount));
                updateCalculation();
            });

            updateCalculation();
        }

        itemremove1.setOnClickListener(v -> {
            if (parentLl != null && item1Ll != null) {
                parentLl.removeView(item1Ll);
                updateCalculation();
            }
        });

        itemremove2.setOnClickListener(v -> {
            if (parentLl != null && item2Ll != null) {
                parentLl.removeView(item2Ll);
                updateCalculation();
            }
        });

        // Add TextWatcher to pcodeEdt to automatically convert text to uppercase
        pcodeEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed here
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Convert text to uppercase
                String uppercaseText = s.toString().toUpperCase();
                if (!uppercaseText.equals(s.toString())) {
                    s.replace(0, s.length(), uppercaseText);
                }
            }
        });

        pcodeBtn.setOnClickListener(v -> {
            String pcEdt=pcodeEdt.getText().toString().trim();
            if (!pcEdt.isEmpty()){
                promoCodeTv.setText(pcEdt);
                Toast.makeText(this, "Promo Code Applied", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Enter the code/Check the code", Toast.LENGTH_SHORT).show();
            }
        });

        checkoutBtn.setOnClickListener(v -> {
            String promoCode = promoCodeTv.getText().toString().trim();
            if (!promoCode.isEmpty()) {
                Intent intent = new Intent(this, CheckOutActivity.class);
                intent.putExtra("total", totalTv.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Need to apply promo code", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateCalculation(){
        int price = 0, count = 0, chPrice = 0, chCount = 0;
        if (item1Ll.getParent() != null) {
            price = Integer.parseInt(itemPrice.getText().toString());
            count = Integer.parseInt(itemCount.getText().toString());
        }
        if (item2Ll.getParent() != null) {
            chPrice = Integer.parseInt(chitemPrice.getText().toString());
            chCount = Integer.parseInt(chitemCount.getText().toString());
        }
        int delFees = Integer.parseInt(delivery.getText().toString());
        int tax = Integer.parseInt(taxandFees.getText().toString());

        int subtotal = (price * count) + (chPrice * chCount);
        int total = subtotal + delFees + ((subtotal + tax) / 100);

        subTotal.setText(String.valueOf(subtotal));
        totalTv.setText(String.valueOf(total));

    }
}