package com.payment.util;

import com.paymentapp.model.Account;
import java.util.Comparator;

public class AccountNoComparator implements Comparator<Account> {

    @Override
    public int compare(Account a1, Account a2) {
        // Use Long.compare for safe comparison
        return Long.compare(a1.getAccountno(), a2.getAccountno());
    }
}