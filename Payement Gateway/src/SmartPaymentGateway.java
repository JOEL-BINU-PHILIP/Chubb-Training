import com.smartpay.auth.LoginManager;
import com.smartpay.auth.LogoutManager;
import com.smartpay.core.Beneficiary;
import com.smartpay.core.SessionManager;
import com.smartpay.exception.*; 
import com.smartpay.payment.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SmartPaymentGateway {

    private final List<Beneficiary> beneficiaries = new ArrayList<>();
    private final LoginManager loginManager = new LoginManager();
    private final LogoutManager logoutManager = new LogoutManager();
    private final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SmartPaymentGateway gateway = new SmartPaymentGateway();
        gateway.run();
    }

    public void run() {
        System.out.println("--- Welcome to the SmartPayment Gateway System ---");
        while (true) {
            printMenu();
            try {
                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {
                    case 1:
                        handleLogin();
                        break;
                    case 2:
                        handleAddBeneficiary();
                        break;
                    case 3:
                        handleMakePayment();
                        break;
                    case 4:
                        logoutManager.logout();
                        break;
                    case 0:
                        System.out.println("Thank you for using SmartPayment Gateway. Exiting.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                sc.nextLine(); // Clear the invalid input
            }
            System.out.println("-------------------------------------------------");
        }
    }

    private void printMenu() {
        if (SessionManager.isLoggedIn()) {
            System.out.println("\nUser: " + SessionManager.getCurrentUser().getUsername());
            System.out.println("1. Login (already logged in)");
            System.out.println("2. Add Beneficiary");
            System.out.println("3. Make Payment");
            System.out.println("4. Logout");
        } else {
            System.out.println("\nUser: Not Logged In");
            System.out.println("1. Login");
            System.out.println("2. Add Beneficiary (Requires Login)");
            System.out.println("3. Make Payment (Requires Login)");
            System.out.println("4. Logout (Not Logged In)");
        }
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void handleLogin() {
        if (SessionManager.isLoggedIn()) {
            System.out.println("You are already logged in as " + SessionManager.getCurrentUser().getUsername());
            return;
        }
        try {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            
            loginManager.login(username, password);
            
        } catch (InvalidLoginException e) {
            System.out.println("Login Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during login: " + e.getMessage());
        }
    }

    private void handleAddBeneficiary() {
        if (!SessionManager.isLoggedIn()) {
            System.out.println("Error: You must be logged in to add a beneficiary.");
            return;
        }
        try {
            System.out.print("Enter beneficiary name: ");
            String name = sc.nextLine();
            System.out.print("Enter account ID (e.g., VPA or Acct Number): ");
            String accountId = sc.nextLine();
            System.out.print("Enter account type (e.g., UPI, Bank): ");
            String type = sc.nextLine();

            // Negative Test: Prevent null or empty names
            if (name == null || name.trim().isEmpty() || accountId == null || accountId.trim().isEmpty()) {
                throw new IllegalArgumentException("Beneficiary details cannot be empty.");
            }

            Beneficiary newBene = new Beneficiary(name, accountId, type);
            beneficiaries.add(newBene);
            System.out.println("Successfully added beneficiary: " + newBene.getName());
        
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding beneficiary: " + e.getMessage());
        }
    }

    private Beneficiary findBeneficiary(String name) throws BeneficiaryNotFoundException {
        // Negative Test: Handle null search
        if (name == null || name.trim().isEmpty()) {
            throw new BeneficiaryNotFoundException("Beneficiary name cannot be null or empty.");
        }
        
        for (Beneficiary bene : beneficiaries) {
            if (bene.getName().equalsIgnoreCase(name)) {
                return bene;
            }
        }
        // Throws the custom exception if the loop finishes without finding a match
        throw new BeneficiaryNotFoundException("Beneficiary '" + name + "' not found in your list.");
    }

    private void handleMakePayment() {
        if (!SessionManager.isLoggedIn()) {
            System.out.println("Error: You must be logged in to make a payment.");
            return;
        }
        
        PaymentProcessor processor = null;
        try {
            System.out.print("Enter beneficiary name: ");
            String beneName = sc.nextLine();
            // This call can throw BeneficiaryNotFoundException
            Beneficiary beneficiary = findBeneficiary(beneName);
            System.out.println("Paying to: " + beneficiary);

            System.out.print("Enter amount to pay: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            // *** MODIFIED: Removed "Wallet" from the prompt ***
            System.out.print("Enter payment method (UPI, CreditCard, NetBanking): ");
            String method = sc.nextLine();

            processor = createPaymentProcessor(method);

            if (processor == null) {
                throw new NullPointerException("Invalid payment method selected: " + method);
            }

            // This single line executes the correct logic based on the object's type
            processor.processPayment(amount);
            
        } catch (TransactionFailedException e) {
            // This is the main exception handler. We inspect the *cause*
            // to provide a specific, user-friendly message.
            System.out.println("Error: Transaction Failed.");
            System.out.println("Reason: " + e.getMessage());

            // Handle specific scenarios
            if (e.getCause() instanceof InvalidAmountException) {
                System.out.println("Action: Please enter a valid, positive amount.");
            } else if (e.getCause() instanceof InvalidCredentialsException) {
                System.out.println("Action: Please re-check your PIN, password, or OTP and try again.");
                System.out.println("LOG: (Masked) User credentials failure."); 
            } else if (e.getCause() instanceof PaymentGatewayTimeoutException) {
                System.out.println("Action: The gateway is busy. Please try again after some time.");
            }
            
        } catch (BeneficiaryNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Action: Please add the beneficiary first (Option 2).");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid amount. Please enter a numeric value (e.g., 500.0).");
            sc.nextLine(); // Clear the bad input
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Action: Please choose from the available payment methods.");
        } catch (Exception e) {
            System.out.println("An unexpected system error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Factory Method to create payment objects
    private PaymentProcessor createPaymentProcessor(String method) {
        if (method == null) return null;
        
        switch (method.trim().toUpperCase()) {
            case "UPI":
                return new UPIPayment();
            case "CREDITCARD":
                return new CreditCardPayment();
            case "NETBANKING":
                return new NetBankingPayment();
            default:
                return null; // This will be caught as a NullPointerException
        }
    }
}