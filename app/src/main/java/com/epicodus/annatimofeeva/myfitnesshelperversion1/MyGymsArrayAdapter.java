package com.epicodus.annatimofeeva.myfitnesshelperversion1;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by anya on 9/16/17.
 */

public class MyGymsArrayAdapter extends ArrayAdapter {

    private Context mContext;
    private String[] mGyms;
    private String[] mDescription;


    public MyGymsArrayAdapter(Context mContext, int resource, String[] mGyms, String[] mDescription) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mGyms= mGyms;
        this.mDescription = mDescription;
    }
    @Override
    public Object getItem(int position) {
        String gym = mGyms[position];
        String training = mDescription[position];
        return String.format("%s \nAbout Gym: %s", gym, training);
    }


    @Override
    public int getCount() {
        return mGyms.length;
    }


} //end of class




