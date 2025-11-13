package com.smartpay.exception;

// For system login (username/password)
public class InvalidLoginException extends Exception {
    public InvalidLoginException(String message) {
        super(message);
    }
}