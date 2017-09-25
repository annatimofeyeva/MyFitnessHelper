package com.epicodus.annatimofeeva.myfitnesshelperversion1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.models.Gym;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anya on 9/17/17.
 */

public class GymListAdapter extends RecyclerView.Adapter<GymListAdapter.GymViewHolder> {
    private ArrayList<Gym> mGyms = new ArrayList<>();
    private Context mContext;

    public GymListAdapter(Context context, ArrayList<Gym> gyms) {
        mContext = context;
        mGyms = gyms;
    }

    @Override
    public GymListAdapter.GymViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gym_list_item, parent, false);
        GymViewHolder viewHolder = new GymViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GymListAdapter.GymViewHolder holder, int position) {
        holder.bindGym(mGyms.get(position));
    }

    @Override
    public int getItemCount() {
        return mGyms.size();
    }

    public class GymViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.gymImageView) ImageView mGymImageView;
        @Bind(R.id.gymNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public GymViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindGym(Gym gym) {

            Picasso.with(mContext).load(gym.getImageUrl()).into(mGymImageView);


            mNameTextView.setText(gym.getName());
            mCategoryTextView.setText(gym.getCategories().get(0));
            mRatingTextView.setText("Rating: " + gym.getRating() + "/5");
        }
    }
}//end of class