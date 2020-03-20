package com.example.stonks;

import android.util.Log;

public class Account {

    // in the future this value will be fetched when user logins
    // but lets initialise it to 100 for now
    private static double stonks = 100;

    public static double getStonks(){
        return stonks;
    }

    public static void addStonks(double _amount){
        stonks += _amount;
        Log.d("kw account", "Stonks Added! Stonks Now: " + stonks);
    }

    public static void deductStonks(double _amount){
        stonks -= _amount;
        Log.d("kw account", "Stonks Deducted! Stonks Now: " + stonks);
    }
}
