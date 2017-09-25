package com.epicodus.annatimofeeva.myfitnesshelperversion1.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.adapters.GymPagerAdapter;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.models.Gym;

import org.parceler.Parcels;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GymDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    private GymPagerAdapter adapterViewPager;
    ArrayList<Gym> mGyms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_detail);
        ButterKnife.bind(this);

        mGyms = Parcels.unwrap(getIntent().getParcelableExtra("gyms"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new GymPagerAdapter(getSupportFragmentManager(), mGyms);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}

