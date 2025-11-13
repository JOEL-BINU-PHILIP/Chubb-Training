package com.smartpay.exception;

public class PaymentGatewayTimeoutException extends Exception {
    public PaymentGatewayTimeoutException(String message) {
        super(message);
    }
}