package com.example.stonks.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stonks.MainActivity;
import com.example.stonks.cards.NewsCard;
import com.example.stonks.R;
import com.example.stonks.fragments.NewsPageFragment;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<NewsCard> mNewsList;

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        public View mItemView;
        public ImageView mImgView;
        public TextView mTitle;
        public TextView mDescrip;
        public TextView mDetails;
        public ImageView mShareBtn;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mImgView = itemView.findViewById(R.id.news_card_image);
            mTitle = itemView.findViewById(R.id.news_card_title);
            mDescrip = itemView.findViewById(R.id.news_card_description);
            mDetails = itemView.findViewById(R.id.news_card_details);
            mShareBtn = itemView.findViewById(R.id.news_card_share_btn);
        }
    }

    public NewsAdapter(ArrayList<NewsCard> newsList){
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        NewsViewHolder nvh = new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsCard currentItem = mNewsList.get(position);

        holder.mImgView.setImageResource(currentItem.getImageResource());
        holder.mTitle.setText(currentItem.getTitle());
        holder.mDescrip.setText(currentItem.getDescrip());
        holder.mDetails.setText(currentItem.getDetails());
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
        return mNewsList.size();
    }
}
