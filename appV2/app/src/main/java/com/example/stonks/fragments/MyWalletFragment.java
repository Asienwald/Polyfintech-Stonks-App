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
import com.example.stonks.adapters.MyWalletAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MyWalletFragment extends Fragment {
    private ViewPager2 view_pager;
    private MyWalletAdapter my_wallet_adapter;
    int position;
    private static final String[] titles = {"My Wallet","History"};
    public MyWalletFragment(int position){
        this.position = position;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_wallet_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        my_wallet_adapter = new MyWalletAdapter(this);
        view_pager = view.findViewById(R.id.pager);
        view_pager.setAdapter(my_wallet_adapter);
        TabLayout tab_layout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tab_layout, view_pager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();
        view_pager.setCurrentItem(position+1);
        TabLayout.Tab tab = tab_layout.getTabAt(position);
        assert tab != null;
        tab.select();

    }
}
