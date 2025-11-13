package com.paymentapp.model;

import java.util.Objects;

// Implements Comparable for default sorting by accountholdername
public class Account implements Comparable<Account> {

    private String accountholdername;
    private long accountno;
    private String transcode;
    private String country;
    private String ifsccode;
    private double balance;

    public Account(String accountholdername, long accountno, String transcode, String country, String ifsccode, double balance) {
        this.accountholdername = accountholdername;
        this.accountno = accountno;
        this.transcode = transcode;
        this.country = country;
        this.ifsccode = ifsccode;
        this.balance = balance;
    }

    // --- Getters ---
    public String getAccountholdername() {
        return accountholdername;
    }

    public long getAccountno() {
        return accountno;
    }

    public double getBalance() {
        return balance;
    }

    // --- Comparable Implementation (Sort by Name) ---
    @Override
    public int compareTo(Account other) {
        // Default sorting is based on accountholdername
        return this.accountholdername.compareTo(other.accountholdername);
    }

    // --- equals() and hashCode() Implementation ---

    /**
     * Two Account objects are considered equal if their
     * accountholdername AND accountno are the same.
     */
    @Override
    public boolean equals(Object obj) {
        // 1. Check if it's the same object reference
        if (this == obj) {
            return true;
        }
        // 2. Check if the object is null or of a different class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // 3. Cast and compare the fields
        Account account = (Account) obj;
        return accountno == account.accountno &&
               Objects.equals(accountholdername, account.accountholdername);
    }

    /**
     * The hashCode must be based on the same fields used in equals()
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountholdername, accountno);
    }

    // --- toString() for easy printing ---
    @Override
    public String toString() {
        return String.format("Account[Name=%-15s, No=%d, Balance=%.2f, Ifsc=%s, Country=%s]",
                accountholdername, accountno, balance, ifsccode, country);
    }
}