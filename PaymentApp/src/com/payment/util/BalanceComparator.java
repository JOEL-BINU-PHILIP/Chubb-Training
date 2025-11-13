package com.payment.util;

import com.paymentapp.model.Account;
import java.util.Comparator;

public class BalanceComparator implements Comparator<Account> {

    @Override
    public int compare(Account a1, Account a2) {
        // Use Double.compare for safe comparison
        return Double.compare(a1.getBalance(), a2.getBalance());
    }
}