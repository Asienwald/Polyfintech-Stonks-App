package com.example.stonks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.stonks.fragments.HomePageFragement;
import com.example.stonks.fragments.JobsPageFragment;
import com.example.stonks.fragments.NewsPageFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            HomePageFragement home_page_fragment = new HomePageFragement();
            NewsPageFragment news_page_fragment = new NewsPageFragment();
            JobsPageFragment jobs_page_fragment = new JobsPageFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // fragmentTransaction.replace(R.id.home_fragment,home_page_fragment).commit();
            // fragmentTransaction.add(R.id.home_fragment, news_page_fragment).commit();
            fragmentTransaction.add(R.id.home_fragment, jobs_page_fragment).commit();
        }
    }
}