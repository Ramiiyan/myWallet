
package com.sample.mywallet;

import java.io.Serializable;

public class Balance implements Serializable {
    public static final String EXTRA ="com.sample.mywallet.BALANCE_EXTRA";


    private double currentBankBalance;
    private double currentWalletBalance;


    public double getCurrentBankBalance() {
        return currentBankBalance;
    }

    public void setCurrentBankBalance(double currentBankBalance) {
        this.currentBankBalance = currentBankBalance;
    }

    public double getCurrentWalletBalance() {
        return currentWalletBalance;
    }

    public void setCurrentWalletBalance(double currentWalletBalance) {
        this.currentWalletBalance = currentWalletBalance;
    }
}

