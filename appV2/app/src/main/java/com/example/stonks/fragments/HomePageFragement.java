package com.example.stonks.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.stonks.R;
import com.example.stonks.adapters.HomePageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomePageFragement extends Fragment {
    private ViewPager2 view_pager;
    private HomePageAdapter home_page_adapter;
    private static final String[] titles = {"My Wallet","History"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_page_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        home_page_adapter = new HomePageAdapter(this);
        view_pager = view.findViewById(R.id.pager);
        view_pager.setAdapter(home_page_adapter);
        TabLayout tab_layout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tab_layout, view_pager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();
    }
}
