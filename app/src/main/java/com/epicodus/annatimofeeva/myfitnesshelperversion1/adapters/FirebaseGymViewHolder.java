package com.epicodus.annatimofeeva.myfitnesshelperversion1.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.Constants;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.models.Gym;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.ui.GymDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseGymViewHolder extends RecyclerView.ViewHolder  {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;


    View mView;
    Context mContext;
    public ImageView mGymImageView;

    public FirebaseGymViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

    }

    public void bindGym(Gym gym) {

        mGymImageView = (ImageView) mView.findViewById(R.id.gymImageView);
        //ImageView restaurantImageView = (ImageView) mView.findViewById(R.id.gymImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.gymNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.with(mContext)
                .load(gym.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mGymImageView);

        nameTextView.setText(gym.getName());
        categoryTextView.setText(gym.getCategories().get(0));
        ratingTextView.setText("Rating: " + gym.getRating() + "/5");
    }
}