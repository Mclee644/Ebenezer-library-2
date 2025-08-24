package core; // Declares the package name as 'core'

import dsa.MyList; // Imports custom list interface
import dsa.CustomHashMap; // Imports custom hash map implementation

/**
 * Generates reports using DSA-only structures.
 */
public class ReportGenerator { // Class responsible for generating library reports

    private LendingTracker lendingTracker; // Reference to lending tracker for transaction data
    private BorrowerRegistry borrowerRegistry; // Reference to borrower registry
    private Inventory inventory; // Reference to inventory for book data

    // Constructor initializes all dependencies needed for reporting
    public ReportGenerator(LendingTracker lendingTracker, BorrowerRegistry borrowerRegistry, Inventory inventory) {
        this.lendingTracker = lendingTracker;
        this.borrowerRegistry = borrowerRegistry;
        this.inventory = inventory;
    }

    /** Most borrowed books (last month simplified) */
    public void mostBorrowedBooksThisMonth() {
        // Retrieves all transactions
        MyList<Transaction> allTx = lendingTracker.getAllTransactions();
        CustomHashMap<Integer> counts = new CustomHashMap<>(); // Stores borrow counts by book hash

        // Counts how many times each book was borrowed
        for (int i = 0; i < allTx.size(); i++) {
            Transaction t = allTx.get(i);
            int hash = t.getBookISBN().hashCode(); // Uses hash of ISBN as key
            counts.put(String.valueOf(hash), counts.get(String.valueOf(hash)) == null ? 1 : counts.get(String.valueOf(hash)) + 1);
        }

        System.out.println("📊 Most Borrowed Books (DSA simplified):");

        // Retrieves all books and displays borrow count for each
        MyList<Book> allBooks = inventory.listBooksSorted();
        for (int i = 0; i < allBooks.size(); i++) {
            Book b = allBooks.get(i);
            int count = counts.get(String.valueOf(b.getISBN().hashCode())) == null ? 0 : counts.get(String.valueOf(b.getISBN().hashCode()));
            System.out.println(b.getTitle() + " - " + count + " times");
        }
    }

    /** Borrowers with highest fines */
    public void borrowersWithHighestFines() {
        // Retrieves all borrowers
        MyList<Borrower> borrowers = borrowerRegistry.getAllBorrowers();
        System.out.println("💰 Borrowers with Highest Fines:");

        // Displays each borrower's name, ID, and fine amount
        for (int i = 0; i < borrowers.size(); i++) {
            Borrower b = borrowers.get(i);
            System.out.printf("%s (ID: %s) - Fine: %.2f%n", b.getName(), b.getId(), b.getFines());
        }
    }

    /** Inventory distribution by category */
    public void inventoryDistribution() {
        // Retrieves books grouped by category
        CustomHashMap<MyList<Book>> booksByCategory = inventory.getBooksByCategory();
        System.out.println("📚 Inventory Distribution:");

        // Iterates through each category and prints the number of books
        MyList<String> categories = booksByCategory.keySet();
        for (int i = 0; i < categories.size(); i++) {
            String cat = categories.get(i);
            MyList<Book> list = booksByCategory.get(cat);
            System.out.printf("%s: %d books%n", cat, list.size());
        }
    }
}