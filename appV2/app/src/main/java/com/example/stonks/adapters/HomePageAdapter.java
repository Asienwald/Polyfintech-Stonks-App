package com.example.stonks.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.stonks.fragments.History;
import com.example.stonks.fragments.MyWallet;

import java.util.ArrayList;

public class HomePageAdapter extends FragmentStateAdapter {
    private static final int PAGE_NUM = 2;
    private MyWallet my_wallet = new MyWallet();
    private History history = new History();
    ArrayList<Fragment> fragment_list = new ArrayList<>();

    public HomePageAdapter(Fragment fragment){
        super(fragment);
        fragment_list.add(my_wallet);
        fragment_list.add(history);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getItemCount() {
        return PAGE_NUM;
    }
}
