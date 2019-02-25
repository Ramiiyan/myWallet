/*
package com.sample.mywallet;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    protected  EditText currentBankBal,currentWalletBal;
    protected Button confirm,back;

    public static final String SHARED_PERFS = "sharedPrefs";
    public static final String BANK_BALANCE = "bankBalance";
    public static final String WALLET_BALANCE = "walletBalance";

   // Balance balance =new Balance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        currentBankBal =findViewById(R.id.currentBankBal);
        currentWalletBal =findViewById(R.id.currentWalletBal);
        confirm =findViewById(R.id.confirm);
        back = findViewById(R.id.back);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentBankBal.getText().length()==0 || currentWalletBal.getText().length() == 0){
                    alertMsg("Fill the Balance!");
                }else {
                    //setCurrentBankBalance(Double.parseDouble(currentBankBal.getText().toString()));
                    //setCurrentWalletBalance(Double.parseDouble(currentWalletBal.getText().toString()));
                    alertMsg("Bank:"+currentBankBal.getText()+"\nWallet:"+currentWalletBal.getText());
                    setData();

                }
            }
        });
        getData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("CURRENT_BANK_BALANCE",currentBankBal.getText().toString());
                intent.putExtra("CURRENT_WALLET_BALANCE",currentWalletBal.getText().toString());
                startActivity(intent);
            }

        });
            //getter setter way
            //Intent intent = new Intent(Settings.this,MainActivity.class);
            //intent.putExtra(Balance.EXTRA,balance);
            //startActivity(intent);


    }
    public void setData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PERFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(BANK_BALANCE,currentBankBal.getText().toString());
        editor.putString(WALLET_BALANCE,currentWalletBal.getText().toString());
        editor.apply();
        Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();
    }
    public void getData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PERFS,MODE_PRIVATE);
        String bank = sharedPreferences.getString(BANK_BALANCE, "");
        String wallet = sharedPreferences.getString(WALLET_BALANCE, "");
       // balance.setCurrentBankBalance(Double.parseDouble(bank));
       // balance.setCurrentWalletBalance(Double.parseDouble(wallet));
        alertMsg("BankBal:"+bank +"\nWalletBal:"+ wallet);

    }

    public void alertMsg(String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(Settings.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
*/
