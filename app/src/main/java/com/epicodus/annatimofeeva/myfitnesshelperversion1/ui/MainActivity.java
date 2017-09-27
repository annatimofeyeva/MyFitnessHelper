package com.epicodus.annatimofeeva.myfitnesshelperversion1.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.Constants;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  implements OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;
    private ValueEventListener mSearchedLocationReferenceListener;


    public static final String TAG = MainActivity.class.getSimpleName();


    private Button mfindGymsButton;
    private Button maboutAppButon;
    private EditText mLocationEditText;
    private TextView mAppNameTextView;
    private Button mSavedGymButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        mSearchedLocationReference.addValueEventListener(new ValueEventListener() { //attach listener

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }

        });



//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/Windsong.ttf");
        mAppNameTextView.setTypeface(ostrichFont);
        mLocationEditText = (EditText) findViewById(R.id.zipCode);
        mfindGymsButton = (Button) findViewById(R.id.findGymsButton);
        maboutAppButon = (Button) findViewById(R.id.aboutAppButon);
        mSavedGymButton = (Button) findViewById(R.id.savedGymsButton);

        mfindGymsButton.setOnClickListener(this);

        maboutAppButon.setOnClickListener(this);
        mSavedGymButton.setOnClickListener(this);

    } //end of onCreate

    @Override

    public void onClick(View view) {

        //Toast.makeText(MainActivity.this, "FirstActivity", Toast.LENGTH_SHORT).show();
        if (view == mfindGymsButton) {

            String location = mLocationEditText.getText().toString();
//            if(!(location).equals("")) {
//                addToSharedPreferences(location);
//            }
            saveLocationToFirebase(location);

            Intent intent = new Intent(MainActivity.this, GymsActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
        if (view == maboutAppButon) {

            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            intent.putExtra("about", "The information about application will be provide later");
            startActivity(intent);
        }

            if (view == mSavedGymButton) {
                Intent intent = new Intent(MainActivity.this, SavedGymListActivity.class);
                startActivity(intent);
            }

        }


    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }

//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }

}
//@Override
//    public void onClick(View view) {
//
//        //Toast.makeText(MainActivity.this, "SecondActivity", Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
//        intent.putExtra("about", "The information about application will be provide later");
//        startActivity(intent);
//
//    }



