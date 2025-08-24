package core; // Declares the package name as 'core'

import dsa.MyList; // Imports custom list interface
import java.time.LocalDate; // Imports LocalDate for handling dates

/** Tracks overdue books */
public class OverdueMonitor { // Class responsible for monitoring overdue book transactions

    private LendingTracker tracker; // Reference to the lending tracker
    private BorrowerRegistry registry; // Reference to the borrower registry
    private double finePerDay; // Fine amount charged per day for overdue books

    // Constructor initializes registry and fine rate; tracker must be set separately
    public OverdueMonitor(BorrowerRegistry registry, double finePerDay) {
        this.registry = registry;
        this.finePerDay = finePerDay;
    }

    public void checkOverdues() {
        // Checks all transactions to identify overdue books
        MyList<Transaction> allTx = tracker.getAllTransactions(); // Retrieves all lending transactions
        LocalDate today = LocalDate.now(); // Gets the current date
        for (int i = 0; i < allTx.size(); i++) {
            Transaction tx = allTx.get(i);
            if (tx.getStatus().equalsIgnoreCase("BORROWED")) {
                // Optional: apply fines logic here
                // Could calculate days overdue and update borrower's fines
            }
        }
        System.out.println("✅ Overdue check completed."); // Confirmation message
    }

    public void recordBorrow(String borrowerId, String isbn) {
        // Placeholder method to record a borrow event
    }

    public void recordReturn(String borrowerId, String isbn) {
        // Placeholder method to record a return event
    }
}