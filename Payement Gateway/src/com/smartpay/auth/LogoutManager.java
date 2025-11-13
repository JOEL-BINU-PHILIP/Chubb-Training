package com.smartpay.auth;

import com.smartpay.core.SessionManager;

public class LogoutManager {
    
    public void logout() {
        if (SessionManager.isLoggedIn()) {
            String username = SessionManager.getCurrentUser().getUsername();
            SessionManager.logout();
            System.out.println("User " + username + " logged out successfully.");
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}