package com.example.stonks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class ManageActivity extends AppCompatActivity {
    private TextView tv_topup;
    private EditText et_amount;
    private Button btn_topup;

    final double EXCHANGE_RATE = 0.69f;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        DigitsKeyListener listen = DigitsKeyListener.getInstance();

        tv_topup = findViewById(R.id.tv_stonks);
        double stonks = Account.getStonks();

        tv_topup.setText(Double.toString(stonks));

        et_amount = findViewById(R.id.et_amount);
        btn_topup = findViewById(R.id.btn_topup);
        et_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                double stonks = Double.parseDouble(et_amount.getText().toString());
                double price = stonks * EXCHANGE_RATE;
                btn_topup.setText("TOP UP! ("+ decimalFormat.format(price) + "SGD)");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topUp();
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d("Heck","I pressed something!");
        return super.onKeyUp(keyCode, event);
    }

    private void topUp(){
        boolean legit = isLegit();
        if (legit){
            double stonksAdded = Double.parseDouble(et_amount.getText().toString());
            Account.addStonks(stonksAdded);
            startActivity(new Intent(ManageActivity.this,ConfirmationActivity.class));
        }
    }

    private boolean isLegit(){
        if (et_amount.getText().toString().equals("")){
            Toast.makeText(this.getApplicationContext(),"Enter something into the input field!",Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            return true;
        }
    }

}
