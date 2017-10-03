package com.epicodus.annatimofeeva.myfitnesshelperversion1.ui;

import android.content.ContentUris;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.icu.util.Calendar;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mAboutTextView;
    private TextView mWebTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mAboutTextView = (TextView) findViewById(R.id.aboutTextView);
        mWebTextView = (TextView) findViewById(R.id.webTextView);

//        Intent intent = getIntent();
//        String about = intent.getStringExtra("about");
//        mAboutTextView.setText("Sorry, the page is under Construction");

        mAboutTextView.setOnClickListener(this);
        mWebTextView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:support@myhikes.com"));
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }

        if (v == mWebTextView) {
            Uri webpage = Uri.parse("https://www.myfitnesspal.com/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);

            }
        }

    }






