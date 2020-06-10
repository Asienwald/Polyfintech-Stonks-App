package com.example.stonks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.stonks.fragments.HomePageFragement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            HomePageFragement home_page_fragment = new HomePageFragement();
            getSupportFragmentManager().beginTransaction().add(R.id.home_fragment,home_page_fragment).commit();
        }
    }
}