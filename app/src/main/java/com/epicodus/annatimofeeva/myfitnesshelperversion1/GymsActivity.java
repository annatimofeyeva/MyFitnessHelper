package com.epicodus.annatimofeeva.myfitnesshelperversion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GymsActivity extends AppCompatActivity {
    private TextView mLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyms);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);

    }
}
