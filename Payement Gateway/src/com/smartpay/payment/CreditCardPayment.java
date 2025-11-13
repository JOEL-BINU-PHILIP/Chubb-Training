package com.smartpay.payment;

import com.smartpay.exception.*;
import com.smartpay.util.Refundable;

// Inheritance & Abstraction
public class CreditCardPayment implements PaymentProcessor, Refundable {

    private String transactionId;
    private double lastAmount;

    @Override
    public void processPayment(double amount) throws TransactionFailedException {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Credit card payment amount must be positive.");
            }

            // Simulate Gateway Timeout
            if (amount > 10000) {
                throw new PaymentGatewayTimeoutException("Gateway timed out for large transaction.");
            }

            this.transactionId = "CC_TXN_" + System.currentTimeMillis();
            this.lastAmount = amount;
            System.out.println("Processing Credit Card payment of $" + amount + "...");
            System.out.println("Payment successful. Transaction ID: " + this.transactionId);

        } catch (InvalidAmountException | PaymentGatewayTimeoutException e) {
            // Wrap specific exceptions in the general TransactionFailedException
            throw new TransactionFailedException("Credit Card payment failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void refundTransaction(String transactionId, double amount) {
        if (this.transactionId != null && this.transactionId.equals(transactionId)) {
            System.out.println("Refunding $" + amount + " for Credit Card transaction " + transactionId);
        } else {
            System.out.println("Refund failed: Transaction ID not found.");
        }
    }
}