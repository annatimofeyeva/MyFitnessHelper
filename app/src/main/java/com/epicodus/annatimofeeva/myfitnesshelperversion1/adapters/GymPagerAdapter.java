package com.epicodus.annatimofeeva.myfitnesshelperversion1.adapters;

/**
 * Created by anya on 9/25/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.models.Gym;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.ui.GymDetailFragment;

import java.util.ArrayList;

public class GymPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Gym> mGyms;

    public GymPagerAdapter(FragmentManager fm, ArrayList<Gym> gyms) {
        super(fm);
        mGyms = gyms;
    }

    @Override
    public Fragment getItem(int position) {
        return GymDetailFragment.newInstance(mGyms.get(position));
    }

    @Override
    public int getCount() {
        return mGyms.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGyms.get(position).getName();
    }
}