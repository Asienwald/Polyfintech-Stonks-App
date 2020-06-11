package com.example.stonks.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stonks.R;
import com.example.stonks.adapters.JobsAdapter;
import com.example.stonks.adapters.NewsAdapter;
import com.example.stonks.cards.JobsCard;
import com.example.stonks.cards.NewsCard;
import com.example.stonks.itemdecoration.VerticalSpaceItemDecoration;

import java.util.ArrayList;

public class JobsPageFragment extends Fragment {

    private RecyclerView mJobsRV;
    private RecyclerView.Adapter mJobsAdapter;
    private RecyclerView.LayoutManager mJobsLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.jobs_page_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // create the list of news (handled somewhere else when database setup
        ArrayList<JobsCard> jobsList = new ArrayList<>();
        jobsList.add(new JobsCard("Help blue_button fish thank", "Kar Wei | Posted 05/06/2020", "3.07 STKS"));
        jobsList.add(new JobsCard("Help blue_button sweet thank", "Kar Wei | Posted 05/06/2020", "3.07 STKS"));
        jobsList.add(new JobsCard("Help blue_button xmm thank", "Kar Wei | Posted 05/06/2020", "3.07 STKS"));

        mJobsRV = view.findViewById(R.id.jobs_rv);
        mJobsRV.setHasFixedSize(true);
        mJobsLayoutManager = new LinearLayoutManager(this.getContext());
        mJobsAdapter = new JobsAdapter(jobsList);

        // item deco to add space between views
        VerticalSpaceItemDecoration dividerItemDecoration = new VerticalSpaceItemDecoration(100);

        mJobsRV.setLayoutManager(mJobsLayoutManager);
        mJobsRV.setAdapter(mJobsAdapter);
        mJobsRV.addItemDecoration(dividerItemDecoration);
    }
}
