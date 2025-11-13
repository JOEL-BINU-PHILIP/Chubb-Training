package com.smartpay.core;

public class Beneficiary {
    private String name;
    private String accountId;
    private String accountType;

    public Beneficiary(String name, String accountId, String accountType) {
        this.name = name;
        this.accountId = accountId;
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "Beneficiary [Name: " + name + ", Account: " + accountId + ", Type: " + accountType + "]";
    }
}