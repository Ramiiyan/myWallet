package com.sample.mywallet;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    protected TextView walletBal, bankBal, totalBal;
    protected EditText buyByCash, buyByCard, transferToBank, transferToWallet;
    protected Button checkBal, transferCredit, settings;
    protected Double walletBalance,bankBalance;
    private String getWalletBal, getBankBal, showTotalBal;

    public static final String MY_PERFS = "mypref";
    public static final String CURRENT_BANK_BALANCE = "currentBankBalance";
    public static final String CURRENT_WALLET_BALANCE = "currentWalletBalance";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            double newBank =savedInstanceState.getDouble("bankBal");
            double newWallet =savedInstanceState.getDouble("walletBal");
            String msg ="NEW BANK :"+newBank+"NEW WALLET :"+newWallet;
            Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
            alertMsg(msg);
        }
        walletBal = findViewById(R.id.walletBal);
        bankBal = findViewById(R.id.bankBal);
        totalBal = findViewById(R.id.totalBal);
        buyByCash = findViewById(R.id.buyByCash);
        buyByCard = findViewById(R.id.buyByCard);
        transferToBank = findViewById(R.id.transferToBank);
        transferToWallet = findViewById(R.id.transferToWallet);
        checkBal = findViewById(R.id.checkBal);
        transferCredit = findViewById(R.id.transferCredit);
        settings = findViewById(R.id.settings);

        sharedPreferences = getSharedPreferences(MY_PERFS, Context.MODE_PRIVATE);

        /*if(sharedPreferences.contains(CURRENT_BANK_BALANCE)){
            bankBal.setText(sharedPreferences.getString(CURRENT_BANK_BALANCE,""));
        }
        if(sharedPreferences.contains(CURRENT_WALLET_BALANCE)){
            walletBal.setText(sharedPreferences.getString(CURRENT_WALLET_BALANCE,""));
        }*/

        // getter setter way to pass variable to another activity (not Working!!!)
        /**final Balance balance = (Balance) getIntent().getSerializableExtra(Balance.EXTRA);
         **
         **showWalletBal = Double.toString(balance.getCurrentWalletBalance());
         **showBankBal = Double.toString(balance.getCurrentBankBalance());
         **showTotalBal = Double.toString((balance.getCurrentBankBalance() + balance.getCurrentWalletBalance()));*/

        //get values from Setting...
        //getBankBal= getIntent().getStringExtra("CURRENT_BANK_BALANCE");
        //getWalletBal= getIntent().getStringExtra("CURRENT_WALLET_BALANCE");

        System.out.println("CURRENT BANK BAL:"+bankBal.getText().toString());
        System.out.println("CURRENT WALLET BAL:"+walletBal.getText().toString());
        retriveData();
        getBankBal=bankBal.getText().toString();
        getWalletBal =walletBal.getText().toString();

        walletBalance = Double.parseDouble(getWalletBal);
        bankBalance = Double.parseDouble(getBankBal);


       // double total = Double.parseDouble(getBankBal)+Double.parseDouble(getWalletBal);
        if(getBankBal==null){
            bankBal.setText("0.0");
        }
        if (getWalletBal ==null) {
            walletBal.setText("0.0");
        }
        System.out.println("CURRENT BANK BAL:"+getBankBal);
        System.out.println("CURRENT WALLET BAL:"+getWalletBal);


        //totalBal.setText(Double.toString(total));
        checkBal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String purchaseByCard = buyByCard.getText().toString();
                String purchaseByCash = buyByCash.getText().toString();
                if(purchaseByCard.isEmpty()){
                    System.out.println("Purchase By Card NULL");
                    purchaseByCard ="0.0";
                }
                if(purchaseByCash.isEmpty()){
                    System.out.println("Purchase By Cash NULL");
                    purchaseByCash ="0.0";
                }
                double cardCredit =Double.parseDouble(getBankBal)-Double.parseDouble(purchaseByCard);
                System.out.println("Purchase By Card :"+purchaseByCard);
                System.out.println("Bank Balance  :"+cardCredit);
                double cashCredit =Double.parseDouble(getWalletBal)-Double.parseDouble(purchaseByCash);
                System.out.println("Purchase By Cash:"+purchaseByCash);
                System.out.println("Wallet Balance  :"+cashCredit);
                saveData(cardCredit,cashCredit);
                retriveData();

            }
        });

        transferCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String depositBank =transferToBank.getText().toString();
                String depositWallet =transferToWallet.getText().toString();
                if(depositBank.isEmpty()){
                    System.out.println("bank is empty");
                    depositBank="0.0";
                }
                if(depositWallet.isEmpty()){
                    System.out.println("Wallet is empty");
                    depositWallet="0.0";
                }
                double depositbankMoney=Double.parseDouble(getBankBal)+Double.parseDouble(depositBank);
                System.out.println("DEPOSIT BANK :"+depositBank);
                System.out.println("DEPOSIT BANK MONEY :"+depositbankMoney);
                double depositWalletMoney=Double.parseDouble(getWalletBal)+Double.parseDouble(depositWallet);
                System.out.println("DEPOSIT Wallet :"+depositWallet);
                System.out.println("DEPOSIT Wallet MONEY :"+depositWalletMoney);
                saveData(depositbankMoney,depositWalletMoney);
                retriveData();

            }
        });

        /*settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });*/
    }

    protected void onSaveInstantState(Bundle outState){
        outState.putDouble("bankBal",bankBalance);
        outState.putDouble("walletBal",walletBalance);
        super.onSaveInstanceState(outState);
    }
    /*private void openSettings() {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
    }*/
    public void saveData(double bankAmount,double walletAmount){
         String Bank   =Double.toString(bankAmount);
         String Wallet =Double.toString(walletAmount);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CURRENT_BANK_BALANCE,Bank);
        editor.putString(CURRENT_WALLET_BALANCE,Wallet);
        editor.commit();
        Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();
    }
    public void retriveData(){
        String bank = sharedPreferences.getString(CURRENT_BANK_BALANCE, "");
        String wallet = sharedPreferences.getString(CURRENT_WALLET_BALANCE, "");
       // balance.setCurrentBankBalance(Double.parseDouble(bank));
       // balance.setCurrentWalletBalance(Double.parseDouble(wallet));
        double Total=Double.parseDouble(bank)+Double.parseDouble(wallet);
        String displayTotal=Double.toString(Total);

        bankBal.setText(bank);
        walletBal.setText(wallet);
        totalBal.setText(displayTotal);

        alertMsg("BankBal:"+ bank +"\nWalletBal:"+ wallet);

    }
    public void alertMsg(String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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