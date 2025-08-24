package core; // Declares the package name as 'core'

import dsa.MyList; // Imports custom list interface
import dsa.MyArrayList; // Imports custom array list implementation

/** Handles lending and returning of books */
public class LendingTracker { // Class responsible for tracking book lending and returns

    private MyList<Transaction> transactions = new MyArrayList<>(); // Stores all lending transactions
    private BorrowerRegistry registry; // Reference to the borrower registry
    private Inventory inventory; // Reference to the book inventory

    // Constructor initializes registry and inventory references
    public LendingTracker(BorrowerRegistry registry, Inventory inventory) {
        this.registry = registry;
        this.inventory = inventory;
    }

    public boolean borrowBook(String borrowerId, String isbn) {
        // Attempts to borrow a book for a given borrower ID and ISBN
        Borrower b = registry.findBorrowerById(borrowerId); // Finds borrower by ID
        Book book = inventory.searchBook(isbn); // Searches for book by ISBN
        if (b == null || book == null) {
            // If borrower or book not found, print warning and return false
            System.out.println("⚠ Borrower or book not found.");
            return false;
        }
        // Creates a new transaction with current date and status "BORROWED"
        Transaction tx = new Transaction(isbn, borrowerId, java.time.LocalDate.now(), null, "BORROWED");
        transactions.add(tx); // Adds transaction to the list
        System.out.println("✅ Book borrowed: " + book.getTitle() + " by " + b.getName());
        return true; // Indicates successful borrowing
    }

    public boolean returnBook(String borrowerId, String isbn) {
        // Attempts to return a book for a given borrower ID and ISBN
        for (int i = 0; i < transactions.size(); i++) {
            Transaction tx = transactions.get(i);
            // Finds matching transaction with status "BORROWED"
            if (tx.getBorrowerId().equalsIgnoreCase(borrowerId) &&
                tx.getBookISBN().equalsIgnoreCase(isbn) &&
                tx.getStatus().equalsIgnoreCase("BORROWED")) {

                tx.setStatus("RETURNED"); // Updates status to "RETURNED"
                tx.setReturnDate(java.time.LocalDate.now()); // Sets return date to today
                System.out.println("✅ Book returned: " + isbn);
                return true; // Indicates successful return
            }
        }
        System.out.println("⚠ Transaction not found."); // If no matching transaction found
        return false; // Indicates failure to return
    }

    public MyList<Transaction> getAllTransactions() {
        // Returns the list of all transactions
        return transactions;
    }

    public void listTransactions() {
        // Prints all transactions to the console
        for (int i = 0; i < transactions.size(); i++) System.out.println(transactions.get(i));
    }
}