package com.smartpay.util;

import com.smartpay.exception.TransactionFailedException;

public interface Retryable {
    void retryPayment() throws TransactionFailedException;
}