package com.smartpay.core;

public class SessionManager {
    
    private static User loggedInUser = null;

    // Private constructor to prevent instantiation
    private SessionManager() {}

    public static void login(User user) {
        loggedInUser = user;
    }

    public static void logout() {
        loggedInUser = null;
    }

    public static boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public static User getCurrentUser() {
        return loggedInUser;
    }
}