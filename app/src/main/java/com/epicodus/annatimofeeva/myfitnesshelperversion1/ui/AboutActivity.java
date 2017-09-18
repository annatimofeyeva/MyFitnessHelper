package com.epicodus.annatimofeeva.myfitnesshelperversion1.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;

public class AboutActivity extends AppCompatActivity {
    private TextView mAboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mAboutTextView = (TextView) findViewById(R.id.aboutTextView);

        Intent intent = getIntent();
        String about = intent.getStringExtra("about");
        mAboutTextView.setText("Sorry, the page is under Construction");
    }
}
