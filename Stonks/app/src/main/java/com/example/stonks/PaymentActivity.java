package com.example.stonks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.abhinay.input.CurrencyEditText;

public class PaymentActivity extends AppCompatActivity {

    private TextView tv_stonks;

    private EditText et_recipient;
    private CurrencyEditText et_amount;

    private Button btn_transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tv_stonks = findViewById(R.id.tv_stonks);
        et_recipient = findViewById(R.id.et_recipient);
        et_amount = findViewById(R.id.et_amount);
        btn_transfer = findViewById(R.id.btn_transfer);


        double _stonks = Account.getStonks();
        tv_stonks.setText(Double.toString(_stonks));

        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecuteTransfer();
            }
        });
    }


    private void ExecuteTransfer(){
        Log.d("kw", "ExecuteTransfer: btn pressed!");
        boolean _valid_fields = ValidateFields();

        if(_valid_fields){
            // do stuff
            TransferStonks();
            // Toast.makeText(this, "Transfer Processing...", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(PaymentActivity.this, ConfirmationActivity.class));
        }
    }

    private void TransferStonks(){
        double _stonks_to_transfer = et_amount.getCleanDoubleValue();

        if(ValidateStonksAmount()){
            // do transferring shit here (TBA)

            // minus stonks i have
            Account.deductStonks(_stonks_to_transfer);
        }

    }

    // region Validation Code
    private boolean ValidateFields(){
        boolean _valid_user = ValidateUsername();
        boolean _valid_amount = ValidateStonksAmount();

        if(_valid_user && _valid_amount){
            return true;
        }
        return false;
    }

    // in future validate if user exists
    // but for now just make sure it aint empty
    private boolean ValidateUsername(){
        String _recipient_user = et_recipient.getText().toString();

        if(_recipient_user.matches("")){
            Toast.makeText(this, "Username cannot be empty!", Toast.LENGTH_SHORT).show();
        }
        else{
            return true;
        }
        return false;
    }

    private boolean ValidateStonksAmount(){

        try {
            // make sure number is always positive
            double _stonks_to_transfer = Math.abs(et_amount.getCleanDoubleValue());
            double _stonks_avail = Account.getStonks();

            Log.d("kw", "stonks to transfer: " + _stonks_to_transfer);
            Log.d("kw", "stonks to avail: " + _stonks_avail);
            if(_stonks_to_transfer > _stonks_avail){
                Toast.makeText(this, "Not enough Stonks!", Toast.LENGTH_SHORT).show();
            }else{
                return true;
            }
        }catch (NumberFormatException e){
            Toast.makeText(this, "Amount cannot be empty!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    //endregion
}
