package main;

import core.*;
import dsa.MyList;
import java.util.Scanner;

public class MainMenu {

    private static Inventory inventory = new Inventory();
    private static BorrowerRegistry registry = new BorrowerRegistry();
    private static LendingTracker tracker;
    private static OverdueMonitor overdueMonitor;
    private static BookSearch bookSearch;
    private static BookSorter bookSorter = new BookSorter();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Load saved books & borrowers
        MyList<Book> books = FileManager.loadBooks();
        for (int i = 0; i < books.size(); i++) inventory.addBook(books.get(i));

        MyList<Borrower> borrowers = FileManager.loadBorrowers();
        for (int i = 0; i < borrowers.size(); i++) registry.addBorrower(borrowers.get(i));

        // Initialize tracker, overdue monitor, search, reports
        tracker = new LendingTracker(registry, inventory);       // Use registry & inventory
        overdueMonitor = new OverdueMonitor(registry, 1.0);      // Pass registry & fineRate
        bookSearch = new BookSearch(inventory.getBookTree());    // Already returns MyList<Book>
        new ReportGenerator(tracker, registry, inventory);

        boolean running = true;
        while (running) {
            System.out.println("\n=== 📚 Ebenezer Library Management System ===");
            System.out.println("1. List All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Register Borrower");
            System.out.println("5. Lend Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Borrowers");
            System.out.println("8. View Transactions");
            System.out.println("9. Check Overdue Books");
            System.out.println("10. Search Book by Title");
            System.out.println("11. Sort Books by Title (A–Z)");
            System.out.println("12. Save & Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1": // List all books
                    inventory.listBooksSorted();   // Use the method we defined
                    break;

                case "2": // Add book
                    System.out.print("Title: "); String title = sc.nextLine();
                    System.out.print("Author: "); String author = sc.nextLine();
                    System.out.print("ISBN: "); String isbn = sc.nextLine();
                    System.out.print("Category: "); String category = sc.nextLine();
                    System.out.print("Year: "); int year = Integer.parseInt(sc.nextLine());
                    System.out.print("Publisher: "); String publisher = sc.nextLine();
                    System.out.print("Shelf: "); String shelf = sc.nextLine();
                    inventory.addBook(new Book(title, author, isbn, category, year, publisher, shelf));
                    break;

                case "3": // Remove book
                    System.out.print("ISBN to remove: "); String removeIsbn = sc.nextLine();
                    inventory.removeBook(removeIsbn);
                    break;

                case "4": // Register borrower
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Borrower ID: "); String id = sc.nextLine();
                    System.out.print("Contact info: "); String contact = sc.nextLine();
                    registry.addBorrower(new Borrower(name, id, contact));
                    break;

                case "5": // Lend book
                    System.out.print("Borrower ID: "); String borrowerId = sc.nextLine();
                    System.out.print("Book ISBN: "); String borrowIsbn = sc.nextLine();
                    tracker.borrowBook(borrowerId, borrowIsbn);
                    break;

                case "6": // Return book
                    System.out.print("Borrower ID: "); String returnId = sc.nextLine();
                    System.out.print("Book ISBN: "); String returnIsbn = sc.nextLine();
                    tracker.returnBook(returnId, returnIsbn);
                    break;

                case "7": // View borrowers
                    registry.listBorrowers();
                    break;

                case "8": // View transactions
                    tracker.listTransactions();
                    break;

                case "9": // Check overdue books
                    overdueMonitor.checkOverdues();
                    break;

                case "10": // Search book by title
                    System.out.print("Enter title: "); String searchTitle = sc.nextLine();
                    MyList<Book> results = bookSearch.searchByTitle(searchTitle);
                    if (results.size() == 0) System.out.println("⚠ No books found.");
                    else {
                        System.out.println("\n📚 Search Results:");
                        for (int i = 0; i < results.size(); i++) System.out.println(results.get(i));
                    }
                    break;

                case "11": // Sort books by title
                    MyList<Book> allBooks = inventory.getBookTree();   // Already MyList<Book>
                    MyList<Book> sortedBooks = bookSorter.sortByTitle(allBooks);
                    System.out.println("\n📚 Books Sorted by Title:");
                    for (int i = 0; i < sortedBooks.size(); i++) System.out.println(sortedBooks.get(i));
                    break;

                case "12": // Save & exit
                    FileManager.saveBooks(inventory.getBookTree());
                    FileManager.saveBorrowers(registry.getAllBorrowers());
                    System.out.println("💾 Data saved. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("⚠ Invalid choice.");
            }
        }

        sc.close();
    }
}
