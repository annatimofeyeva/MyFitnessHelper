package com.epicodus.annatimofeeva.myfitnesshelperversion1.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.Constants;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements OnClickListener {

    public static final String TAG = LoginActivity.class.getSimpleName();

    @Bind(R.id.findGymsButton) Button mFindGymsButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedGymsButton) Button   mSavedGymsButton;
    @Bind(R.id.aboutAppButton) Button maboutAppButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/Walkway_UltraBold.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

        mFindGymsButton.setOnClickListener(this);
        mSavedGymsButton.setOnClickListener(this);
        maboutAppButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "LogOut successful.",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onClick(View view) {

        if (view == maboutAppButton) {

            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            intent.putExtra("about", "The information about application will be provide later");
            startActivity(intent);
        }

        if (view ==  mSavedGymsButton) {
            Intent intent = new Intent(MainActivity.this, SavedGymListActivity.class);
            startActivity(intent);
        }

        if(view == mFindGymsButton) {
            Intent intent = new Intent(MainActivity.this, GymsActivity.class);
            startActivity(intent);
        }

    }

}




