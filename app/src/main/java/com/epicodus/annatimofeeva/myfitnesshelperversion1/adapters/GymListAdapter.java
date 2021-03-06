package com.epicodus.annatimofeeva.myfitnesshelperversion1.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.epicodus.annatimofeeva.myfitnesshelperversion1.Constants;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.R;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.models.Gym;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.ui.GymDetailActivity;
import com.epicodus.annatimofeeva.myfitnesshelperversion1.ui.GymDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GymListAdapter extends RecyclerView.Adapter<GymListAdapter.GymViewHolder>  {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

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



    public class GymViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int mOrientation;

        @Bind(R.id.gymImageView)
        ImageView mGymImageView;
        @Bind(R.id.gymNameTextView)
        TextView mNameTextView;
        @Bind(R.id.categoryTextView)
        TextView mCategoryTextView;
        @Bind(R.id.ratingTextView)
        TextView mRatingTextView;
        private Context mContext;

        public GymViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            // Determines the current orientation of the device:
            mOrientation = itemView.getResources().getConfiguration().orientation;

            // Checks if the recorded orientation matches Android's landscape configuration.
            // if so, we create a new DetailFragment to display in our special landscape layout:
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            // Determines the position of the restaurant clicked:
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, GymDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_RESTAURANTS, Parcels.wrap(mGyms));
                mContext.startActivity(intent);
            }
        }

        public void bindGym(Gym gym) {
            Picasso.with(mContext)
                    .load(gym.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mGymImageView);
            mNameTextView.setText(gym.getName());
            mCategoryTextView.setText(gym.getCategories().get(0));
            mRatingTextView.setText("Rating: " + gym.getRating() + "/5");
        }

        private void createDetailFragment(int position) {
            // Creates new RestaurantDetailFragment with the given position:
            GymDetailFragment detailFragment = GymDetailFragment.newInstance(mGyms, position);
            // Gathers necessary components to replace the FrameLayout in the layout with the RestaurantDetailFragment:
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            //  Replaces the FrameLayout with the RestaurantDetailFragment:
            ft.replace(R.id.gymDetailContainer, detailFragment);
            // Commits these changes:
            ft.commit();
        }

    }
}//end of class