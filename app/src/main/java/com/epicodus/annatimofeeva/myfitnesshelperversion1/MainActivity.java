package com.epicodus.annatimofeeva.myfitnesshelperversion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mfindGymsButton;
    private Button maboutAppButon;
    private EditText mLocationEditText;
    


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationEditText = (EditText) findViewById(R.id.zipCode);
        mfindGymsButton = (Button) findViewById(R.id.findGymsButton);
        maboutAppButon = (Button) findViewById(R.id.aboutAppButon);

        mfindGymsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this, "FirstActivity", Toast.LENGTH_SHORT).show();

                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, GymsActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);

            }
        });

        maboutAppButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this, "SecondActivity", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                intent.putExtra("about", "The information about application will be provide later");
                startActivity(intent);

            }
        });



    } //end of OnCreate
}
