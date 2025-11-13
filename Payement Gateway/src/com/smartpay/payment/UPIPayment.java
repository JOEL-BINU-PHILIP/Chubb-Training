// com/smartpay/payment/UPIPayment.java
package com.smartpay.payment;

import com.smartpay.exception.*;
import com.smartpay.util.Retryable;

// Inheritance & Abstraction
public class UPIPayment implements PaymentProcessor, Retryable {
    
    private double lastAmount;
    
    @Override
    public void processPayment(double amount) throws TransactionFailedException {
        try {
   
            if (amount <= 0) {
                throw new InvalidAmountException("UPI amount must be positive. Payment of $" + amount + " rejected.");
            }

            this.lastAmount = amount;

            if (amount == 420) { 
                throw new InvalidCredentialsException("Invalid UPI PIN entered.");
            }

            System.out.println("Processing UPI payment of $" + amount + "...");
            System.out.println("Payment successful via UPI.");

        } catch (InvalidAmountException | InvalidCredentialsException e) {
            throw new TransactionFailedException("UPI transaction failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void retryPayment() throws TransactionFailedException {
        if (this.lastAmount > 0) {
            System.out.println("Retrying payment for $" + this.lastAmount + "...");
            processPayment(this.lastAmount); 
        } else {
            System.out.println("No previous UPI payment to retry.");
        }
    }
}