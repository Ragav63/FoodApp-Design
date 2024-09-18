package com.example.foodapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderSucessActivity extends AppCompatActivity {

    TextView currDateTime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_sucess);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currDateTime=findViewById(R.id.dateTimeTv);

        // Get current date and time
        Calendar calendar = Calendar.getInstance();

        // Format the current date and time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedCurrentDateTime = formatter.format(calendar.getTime());

        // Add one hour to the current time
        calendar.add(Calendar.HOUR_OF_DAY, 1);

        // Calculate the duration
        long durationMillis = calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        long durationHours = (durationMillis / (1000 * 60 * 60)) % 24;
        long durationMinutes = (durationMillis / (1000 * 60)) % 60;

        // Combine the formatted date and time with the calculated duration
        String displayText = formattedCurrentDateTime + " (within" + durationHours + " hr " + durationMinutes + " min you will get the package).";

        // Set the combined string to the TextView
        currDateTime.setText(displayText);
    }
}