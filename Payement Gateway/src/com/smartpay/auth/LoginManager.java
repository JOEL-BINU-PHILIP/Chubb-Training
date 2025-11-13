
package com.smartpay.auth;

import com.smartpay.core.User;
import com.smartpay.core.SessionManager;
import com.smartpay.exception.InvalidLoginException;

public class LoginManager {

    private final User validUser = new User("Joel", "123");

    public void login(String username, String password) throws InvalidLoginException {
        // Negative test: Handle null or empty inputs
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            throw new InvalidLoginException("Username and password cannot be empty.");
        }

        if (validUser.getUsername().equals(username) && validUser.getPassword().equals(password)) {
            // On successful login, update the session
            SessionManager.login(validUser);
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            throw new InvalidLoginException("Invalid username or password.");
        }
    }
}