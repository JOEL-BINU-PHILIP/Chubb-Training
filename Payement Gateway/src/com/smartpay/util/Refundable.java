package com.smartpay.util;

public interface Refundable {
    void refundTransaction(String transactionId, double amount);
}