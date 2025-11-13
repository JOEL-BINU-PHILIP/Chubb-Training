package com.smartpay.payment;

import com.smartpay.exception.TransactionFailedException;


public interface PaymentProcessor {
    void processPayment(double amount) throws TransactionFailedException;
}