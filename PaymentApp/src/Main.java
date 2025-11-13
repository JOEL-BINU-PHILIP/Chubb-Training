

// --- Imports from your PaymentApp ---
import com.paymentapp.model.Account;
import com.payment.util.AccountNoComparator;
import com.payment.util.BalanceComparator;
import com.app.dto.NEFTProcessFund;
// --- !!! IMPORTS FROM YOUR BankingApp !!! ---
// This is the "connection". These lines will only work
// if you connected the projects in the Java Build Path.
import com.app.dto.Customer;
import com.app.process.ProcessPayment;
import com.app.process.CustomerOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("=== Testing PaymentApp (Account, etc.) ===");
        System.out.println("=========================================");
        
        // 1. Create a list of Account objects (from PaymentApp)
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Vijay", 98765L, "T04", "IN", "IFSC004", 1500.75));
        accounts.add(new Account("Aarav", 55555L, "T05", "UK", "IFSC005", 9200.00));
        accounts.add(new Account("Priya", 11111L, "T06", "IN", "IFSC006", 500.50));

        System.out.println("\n--- Sorting by Name (Comparable) ---");
        Collections.sort(accounts);
        printList(accounts);

        System.out.println("\n--- Sorting by Account Number (Comparator) ---");
        accounts.sort(new AccountNoComparator());
        printList(accounts);


        System.out.println("\n=============================================");
        System.out.println("=== Testing Connection to BankingApp ===");
        System.out.println("=============================================");

        // 2. Create objects from your BankingApp
        //    (This proves the connection works!)
        
        System.out.println("Creating objects from BankingApp...");
        
        Customer bankCustomer = new Customer();
        ProcessPayment paymentService = new NEFTProcessFund();
        CustomerOperations ops = new CustomerOperations();

        System.out.println("Successfully created BankingApp objects:");
        System.out.println(" - " + bankCustomer.getClass().getName());
        System.out.println(" - " + paymentService.getClass().getName());
        System.out.println(" - " + ops.getClass().getName());
        
        System.out.println("\n--- Projects are connected! ---");
    }

    // Helper method to print the list
    public static void printList(List<Account> list) {
        for (Account acc : list) {
            System.out.println(acc);
        }
    }
}