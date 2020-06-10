package com.example.stonks.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stonks.R;
import com.example.stonks.cards.JobsCard;
import com.example.stonks.cards.NewsCard;

import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {
    private ArrayList<JobsCard> mJobsList;

    public static class JobsViewHolder extends RecyclerView.ViewHolder{

        public View mItemView;
        public TextView mTitle;
        public TextView mDetails;
        public TextView mStonks;
        public ImageView mStarBtn;
        public ImageView mShareBtn;


        public JobsViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mTitle = itemView.findViewById(R.id.job_card_title);
            mDetails = itemView.findViewById(R.id.job_card_details);
            mStonks = itemView.findViewById(R.id.jobs_stonks);
            mShareBtn = itemView.findViewById(R.id.job_share_btn);
            mStarBtn = itemView.findViewById(R.id.job_star_btn);
        }
    }

    public JobsAdapter(ArrayList<JobsCard> jobsList){
        mJobsList = jobsList;
    }

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs_card, parent, false);
        JobsViewHolder nvh = new JobsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder holder, int position) {
        JobsCard currentItem = mJobsList.get(position);

        holder.mTitle.setText(currentItem.getTitle());
        holder.mDetails.setText(currentItem.getDetails());
        holder.mStonks.setText(currentItem.getStonks());
        holder.mStarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add staring capabilities here
                Log.d("ONCLICK", currentItem.getTitle() + "has been starred!");
            }
        });
        holder.mShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add sharing capabilities here
                Log.d("ONCLICK", currentItem.getDetails());
            }
        });
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add opening news page func here
                Log.d("ONCLICK", currentItem.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mJobsList.size();
    }
}
