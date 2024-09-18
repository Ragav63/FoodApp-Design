package com.example.foodapp;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CheckOutActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView addressTv, totalPay;
    LinearLayout mcardLl, paypalLl, appleLl;
    ImageView cardIV,backIV;
    AppCompatButton ordersucessBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_out);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addressTv=findViewById(R.id.address);
        totalPay=findViewById(R.id.totalPayTv);

        mcardLl=findViewById(R.id.mcardll);
        paypalLl=findViewById(R.id.paypalll);
        appleLl=findViewById(R.id.applell);

        cardIV=findViewById(R.id.cardImgV);
        backIV=findViewById(R.id.backIV);

        ordersucessBtn=findViewById(R.id.confOrderBtn);

        String total = getIntent().getStringExtra("total");

        mcardLl.setOnClickListener(v -> {
            cardIV.setImageResource(R.drawable.mastercardsymbol512px);
            mcardLl.setBackgroundResource(R.drawable.whitecircle_bg);
            paypalLl.setBackgroundResource(R.drawable.whitecirclestroke_bg); // Remove background of other views
            appleLl.setBackgroundResource(R.drawable.whitecirclestroke_bg); // Remove background of other views
        });

        paypalLl.setOnClickListener(v -> {
            cardIV.setImageResource(R.drawable.paypalsymbol512px);
            paypalLl.setBackgroundResource(R.drawable.whitecircle_bg);
            mcardLl.setBackgroundResource(R.drawable.whitecirclestroke_bg); // Remove background of other views
            appleLl.setBackgroundResource(R.drawable.whitecirclestroke_bg); // Remove background of other views
        });

        appleLl.setOnClickListener(v -> {
            cardIV.setImageResource(R.drawable.rupay512px);
            appleLl.setBackgroundResource(R.drawable.whitecircle_bg);
            mcardLl.setBackgroundResource(R.drawable.whitecirclestroke_bg); // Remove background of other views
            paypalLl.setBackgroundResource(R.drawable.whitecirclestroke_bg); // Remove background of other views
        });


        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        totalPay.setText(total);

        ordersucessBtn.setOnClickListener(v -> startActivity(new Intent(this, OrderSucessActivity.class)));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        double latitude=12.9482, longitude=77.5972;
        LatLng location=new LatLng(latitude,longitude);
        googleMap.addMarker(new MarkerOptions().position(location).title("Wilson Garden"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,12));
        fetchAddress(latitude,longitude);
    }

    private void fetchAddress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                String locationName = address.getAddressLine(0);
                addressTv.setText(locationName);
            } else {
                addressTv.setText("Location not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            addressTv.setText("Error fetching location");
        }
    }
}