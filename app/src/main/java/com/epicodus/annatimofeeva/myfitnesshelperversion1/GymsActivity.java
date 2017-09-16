package com.epicodus.annatimofeeva.myfitnesshelperversion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GymsActivity extends AppCompatActivity {
    private String[] gyms = new String[] {"Anytime Fitness", "The Seattle Gym", "Seattle Fitness Inc", "Seattle Authentic Club",
            "24 Hours Fitness", "Mode of Fitness", "Rival Fitness", "ZUM Fitness", "NW Fitness Gym", "Gold's Gym"
    };
    private TextView mLocationTextView;
    private ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyms);

        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, gyms);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String gym = ((TextView)view).getText().toString();
                Toast.makeText(GymsActivity.this, gym, Toast.LENGTH_LONG).show();
            }
        });


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the Gyms near: " + location);

    }
}
