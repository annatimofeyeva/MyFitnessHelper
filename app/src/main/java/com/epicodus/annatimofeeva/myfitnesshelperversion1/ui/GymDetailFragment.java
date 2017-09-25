package com.epicodus.annatimofeeva.myfitnesshelperversion1.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GymDetailFragment extends Fragment {


    public GymDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gym_detail, container, false);
    }

}
