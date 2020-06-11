package com.example.stonks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.example.stonks.fragments.HomePageFragment;
import com.example.stonks.fragments.MyWalletFragment;
import com.example.stonks.fragments.JobsPageFragment;
import com.example.stonks.fragments.NewsPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private MyWalletFragment my_wallet_fragment;
    private NewsPageFragment news_page_fragment;
    private JobsPageFragment jobs_page_fragment;
    private HomePageFragment home_page_fragement;
    private PopupWindow popup;
    private BottomNavigationView bottom_navigation;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation  = findViewById(R.id.naviboi);
        bottom_navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:{
                    transfer("home",null);
                    break;
                }
                case R.id.wallet:{
                    View poppy = getLayoutInflater().inflate(R.layout.wallet_drawer,null);
                    popup = new PopupWindow(poppy);
                    popup.setOutsideTouchable(true);
                    popup.setFocusable(true);
                    popup.showAsDropDown(bottom_navigation,0,-400);
                    popup.update(bottom_navigation.getWidth(),220);
                    popup.setOnDismissListener(() -> bottom_navigation.setBackgroundResource(R.drawable.bottom_navigation_menu));
                    bottom_navigation.setBackgroundResource(R.drawable.stack);
                    ImageButton menu_transfer = poppy.findViewById(R.id.menu_transfer);
                    ImageButton menu_history = poppy.findViewById(R.id.menu_history);
                    ImageButton menu_stats = poppy.findViewById(R.id.menu_stats);
                    menu_transfer.setOnClickListener((View v)-> transfer("wallet",0));
                    menu_history.setOnClickListener((View v)-> transfer("wallet",1));
                    menu_stats.setOnClickListener((View v)-> transfer("wallet",2));
                }
            }
            return true;
        });
        if(savedInstanceState == null){
            home_page_fragement = new HomePageFragment();
            fragmentTransaction(home_page_fragement);
        }

    }
    private void transfer(String fragment, Integer position){
       switch (fragment){
           case "home":
               home_page_fragement = new HomePageFragment();
               fragmentTransaction(home_page_fragement);
               break;
           case "wallet":
               popup.dismiss();
               my_wallet_fragment = new MyWalletFragment(position);
               fragmentTransaction(my_wallet_fragment);
               break;
       }
    }
    private void fragmentTransaction(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         fragmentTransaction.replace(R.id.home_fragment,fragment).commit();
    }
}