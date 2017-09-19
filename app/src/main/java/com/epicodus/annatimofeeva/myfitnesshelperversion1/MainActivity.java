package com.epicodus.annatimofeeva.myfitnesshelperversion1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;




public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.findGymsButton) Button mfindGymsButton;
    @Bind(R.id.aboutButton) Button maboutButton;
    @Bind(R.id.zipCode) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;





    //private Button mfindGymsButton;
   // private Button maboutAppButon;
    //private EditText mLocationEditText;
    //private TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/Windsong.ttf");
        mAppNameTextView.setTypeface(ostrichFont);


        //mfindGymsButton.setOnClickListener(this);
        //maboutButton.setOnClickListener(this);
        mfindGymsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                if (location.length() == 0) {
                    AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
                    alert.setTitle("No zip code entered");
                    alert.setMessage("Please enter valid zip code");
                    alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, GymsActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                }
            }
        });
        maboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                intent.putExtra("about", "The information about application will be provide later");
                startActivity(intent);
            }
        });


    } //on Create
/*
    @Override

    public void onClick(View view) {



        //Toast.makeText(MainActivity.this, "FirstActivity", Toast.LENGTH_SHORT).show();
        if(view == mfindGymsButton){

        String location = mLocationEditText.getText().toString();
        Intent intent = new Intent(MainActivity.this, GymsActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
        }
        if(view ==  maboutButton) {

            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        intent.putExtra("about", "The information about application will be provide later");
        startActivity(intent);
        }


    }*/

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


}
