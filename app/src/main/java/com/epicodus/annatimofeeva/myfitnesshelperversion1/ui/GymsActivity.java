package com.epicodus.annatimofeeva.myfitnesshelperversion1.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.Constants;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.adapters.GymListAdapter;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.models.Gym;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.services.YelpService;
import java.io.IOException;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.Callback;


public class GymsActivity extends AppCompatActivity {

    public static final String TAG = GymsActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private GymListAdapter mAdapter;

//    @Bind(R.id.locationTextView) TextView mLocationTextView;
//    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Gym> mGyms = new ArrayList<>();

//    private String[] gyms = new String[] {"Anytime Fitness", "The Seattle Gym", "Seattle Fitness Inc", "Seattle Authentic Club",
//            "24 Hours Fitness", "Gold's Gym"
//    };
//
//    private String[] description = new String[] {"We offer personal training and classes when you’re here, and the right tools to keep you on track when you’re not",
//    "The mission of TheSeattleGYM is to enhance the quality of life in the communities we serve and to instill the value and importance of health and fitness within our health club.",
//    "At Seattle Fitness, it is our mission to provide members the opportunity to experience fitness at its finest. Our goal is to share our knowledge and experience in a professional and friendly manner, contributing to your overall health and well-being.",
//    "We are dedicated to bring out the best in our members everyday by continuing to offer the very best in qualified professional fitness staff, excellent customer service, exciting fitness programs, and ever-evolving and improving fitness facilities and equipment.",
//    "Your workout should always be the high point of your day. Our Seattle gym is designed to excite and motivate, with amazing studio classes, innovative training programs, and ample workout space to help you get into your zone and get moving.",
//    "Today Gold’s Gym is the most recognized name in fitness serving more than 3 million members in 38 states and 22 countries around the world. Always at the forefront of the fitness revolution, Gold’s Gym has continually evolved its profile by equipping gyms with the best amenities and the latest in cardio and strength training equipment as well as the most dynamic group exercise programs including Zumba, yoga, group cycling, mixed martial arts, muscle endurance training, and Pilates."
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyms);

        ButterKnife.bind(this);

//        mListView = (ListView) findViewById(R.id.listView);
//        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
//
//
//        MyGymsArrayAdapter adapter = new MyGymsArrayAdapter(this, android.R.layout.simple_list_item_1, gyms, description);
//        mListView.setAdapter(adapter);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String gym = ((TextView) view).getText().toString();
//                Toast.makeText(GymsActivity.this, gym, Toast.LENGTH_LONG).show();
//            }
//        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
//        mLocationTextView.setText("Here are all the Gyms near: " + location);
        getGyms(location);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (mRecentAddress != null) {
            getGyms(mRecentAddress);
        }
        Log.d("Shared Pref Location", mRecentAddress);
    }
        private void getGyms(String location) {
            final YelpService yelpService = new YelpService();
            yelpService.findGyms(location, new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        String jsonData = response.body().string();
//                        if(response.isSuccessful()) {
//                            Log.v(TAG, jsonData);
//                            mGyms = yelpService.processResults(response);
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }

                @Override
                public void onResponse(Call call, Response response) {
                    mGyms = yelpService.processResults(response);

                    GymsActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {



                            mAdapter = new GymListAdapter(getApplicationContext(), mGyms);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager =
                                    new LinearLayoutManager(GymsActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);






//                            String[] restaurantNames = new String[mGyms.size()];
//                            for (int i = 0; i < restaurantNames.length; i++) {
//                                restaurantNames[i] = mGyms.get(i).getName();
//                            }
//                            ArrayAdapter adapter = new ArrayAdapter(GymsActivity.this, android.R.layout.simple_list_item_1, restaurantNames);
//                            mListView.setAdapter(adapter);

//                            for (Gym gym : mGyms) {
//                                Log.d(TAG, "Name: " + gym.getName());
//                                Log.d(TAG, "Phone: " + gym.getPhone());
//                                Log.d(TAG, "Website: " + gym.getWebsite());
//                                Log.d(TAG, "Image url: " + gym.getImageUrl());
//                                Log.d(TAG, "Rating: " + Double.toString(gym.getRating()));
//                                Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", gym.getAddress()));
//                                Log.d(TAG, "Categories: " + gym.getCategories().toString());
//                            }
                        }
                    });
                }





            });
        }



    }

