package com.example.stonks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private CardView card_transfer;
    private CardView card_loan;
    private CardView card_invest;
    private CardView card_manage;
    private CardView card_budget;
    private CardView card_analyse;
    private CardView card_incentives;
    private CardView card_settings;

    private Map<CardView, Class> card_to_intent_dict = new HashMap<CardView, Class>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the wanted cardviews first
        card_transfer = findViewById(R.id.card_transfer);
        card_loan = findViewById(R.id.card_loan);
        card_invest = findViewById(R.id.card_invest);
        card_manage = findViewById(R.id.card_manage);
        card_budget = findViewById(R.id.card_budget);
        card_analyse = findViewById(R.id.card_analyse);
        card_incentives = findViewById(R.id.card_incentives);
        card_settings = findViewById(R.id.card_settings);

        // put it into the intent mapping
        card_to_intent_dict.put(card_transfer, PaymentActivity.class);
        card_to_intent_dict.put(card_manage, ManageActivity.class);
        card_to_intent_dict.put(card_incentives, IncentivesActivity.class);

        // add onclick function to bring to specified class to each card view
        InitCardsOnClick();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Log Out?")
                .setMessage("Log out and return to Login Page?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    private void InitCardsOnClick(){

        for(Map.Entry<CardView, Class> entry : card_to_intent_dict.entrySet()){
            CardView _card_view = entry.getKey();
            final Class _activity = entry.getValue();

            _card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, _activity));
                }
            });
        }
    }
}
