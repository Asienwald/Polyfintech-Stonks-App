package com.example.stonks.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stonks.adapters.NewsAdapter;
import com.example.stonks.cards.NewsCard;
import com.example.stonks.R;
import com.example.stonks.itemdecoration.VerticalSpaceItemDecoration;

import java.util.ArrayList;

public class NewsPageFragment extends Fragment {

    private RecyclerView mNewsRV;
    private RecyclerView.Adapter mNewsAdapter;
    private RecyclerView.LayoutManager mNewsLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_page_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // create the list of news (handled somewhere else when database setup
        ArrayList<NewsCard> newsList = new ArrayList<>();
        newsList.add(new NewsCard(R.drawable.mbs, "Blockchain in Europe", "yesssssssssss succ", "Kar Wei | 10/06/2020"));
        newsList.add(new NewsCard(R.drawable.mbs, "Test 34", "yesssssssssss succ meeeeeee", "Jess | 08/06/2020"));
        newsList.add(new NewsCard(R.drawable.mbs, "Test 696969", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "Heng Woon | 02/06/2020"));

        mNewsRV = view.findViewById(R.id.news_rv);
        mNewsRV.setHasFixedSize(true);
        mNewsLayoutManager = new LinearLayoutManager(this.getContext());
        mNewsAdapter = new NewsAdapter(newsList);

        // item deco to add space between views
        VerticalSpaceItemDecoration dividerItemDecoration = new VerticalSpaceItemDecoration(100);

        mNewsRV.setLayoutManager(mNewsLayoutManager);
        mNewsRV.setAdapter(mNewsAdapter);
        mNewsRV.addItemDecoration(dividerItemDecoration);
    }
}
