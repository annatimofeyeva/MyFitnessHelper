package com.epicodus.annatimofeeva.myfitnesshelperversion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

//import javax.security.auth.callback.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.Callback;


public class GymsActivity extends AppCompatActivity {



    public static final String TAG = GymsActivity.class.getSimpleName();

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] gyms = new String[] {"Anytime Fitness", "The Seattle Gym", "Seattle Fitness Inc", "Seattle Authentic Club",
            "24 Hours Fitness", "Gold's Gym"
    };

    private String[] description = new String[] {"We offer personal training and classes when you’re here, and the right tools to keep you on track when you’re not",
    "The mission of TheSeattleGYM is to enhance the quality of life in the communities we serve and to instill the value and importance of health and fitness within our health club.",
    "At Seattle Fitness, it is our mission to provide members the opportunity to experience fitness at its finest. Our goal is to share our knowledge and experience in a professional and friendly manner, contributing to your overall health and well-being.",
    "We are dedicated to bring out the best in our members everyday by continuing to offer the very best in qualified professional fitness staff, excellent customer service, exciting fitness programs, and ever-evolving and improving fitness facilities and equipment.",
    "Your workout should always be the high point of your day. Our Seattle gym is designed to excite and motivate, with amazing studio classes, innovative training programs, and ample workout space to help you get into your zone and get moving.",
    "Today Gold’s Gym is the most recognized name in fitness serving more than 3 million members in 38 states and 22 countries around the world. Always at the forefront of the fitness revolution, Gold’s Gym has continually evolved its profile by equipping gyms with the best amenities and the latest in cardio and strength training equipment as well as the most dynamic group exercise programs including Zumba, yoga, group cycling, mixed martial arts, muscle endurance training, and Pilates."
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyms);

        ButterKnife.bind(this);


        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);


        MyGymsArrayAdapter adapter = new MyGymsArrayAdapter(this, android.R.layout.simple_list_item_1, gyms, description);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String gym = ((TextView) view).getText().toString();
                Toast.makeText(GymsActivity.this, gym, Toast.LENGTH_LONG).show();
            }
        });


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the Gyms near: " + location);

        getGyms(location);

    }
        private void getGyms(String location) {
            final YelpService yelpService = new YelpService();
            yelpService.findGyms(location, new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }


                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



            });
        }



    }

