// com/smartpay/payment/NetBankingPayment.java
package com.smartpay.payment;

import com.smartpay.exception.*;
import com.smartpay.util.Refundable;
import com.smartpay.util.Retryable;

public class NetBankingPayment implements PaymentProcessor, Refundable, Retryable {

    private String lastTransactionId;
    private double lastAmount;

    @Override
    public void processPayment(double amount) throws TransactionFailedException {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("NetBanking amount must be positive.");
            }

            // Scenario 4: Invalid Credentials (Simulating wrong password)
            if (amount == 666) { // Special trigger
                throw new InvalidCredentialsException("Invalid bank password or OTP.");
            }

            System.out.println("Processing NetBanking payment of $" + amount + "...");
            this.lastTransactionId = "NB_TXN_" + System.currentTimeMillis();
            this.lastAmount = amount;
            System.out.println("Payment successful via NetBanking. Transaction ID: " + this.lastTransactionId);
        
        } catch (InvalidAmountException | InvalidCredentialsException e) {
            throw new TransactionFailedException("NetBanking transaction failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void retryPayment() throws TransactionFailedException {
        System.out.println("Retrying NetBanking payment for $" + this.lastAmount + "...");
        processPayment(this.lastAmount);
    }

    @Override
    public void refundTransaction(String transactionId, double amount) {
        if (this.lastTransactionId != null && this.lastTransactionId.equals(transactionId)) {
            System.out.println("Refunding $" + amount + " for NetBanking transaction " + transactionId);
        } else {
            System.out.println("Refund failed: Transaction ID not found.");
        }
    }
}