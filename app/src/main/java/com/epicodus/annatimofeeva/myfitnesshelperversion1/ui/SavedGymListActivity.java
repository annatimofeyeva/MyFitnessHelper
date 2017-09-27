package com.epicodus.annatimofeeva.myfitnesshelperversion1.ui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.Constants;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.adapters.FirebaseGymViewHolder;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.models.Gym;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedGymListActivity extends AppCompatActivity {

    private DatabaseReference mGymReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gyms);
        ButterKnife.bind(this);

        mGymReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GYMS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Gym, FirebaseGymViewHolder>
                (Gym.class, R.layout.gym_list_item, FirebaseGymViewHolder.class,
                        mGymReference) {

            @Override
            protected void populateViewHolder(FirebaseGymViewHolder viewHolder,
                                              Gym model, int position) {
                viewHolder.bindGym(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}