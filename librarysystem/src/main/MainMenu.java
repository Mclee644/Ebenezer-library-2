package main;

import core.*;
import java.util.Scanner;

import dsa.MyList;

public class MainMenu {

    private static Inventory inventory = new Inventory();
    private static BorrowerRegistry registry = new BorrowerRegistry();
    private static LendingTracker tracker;
    private static OverdueMonitor overdueMonitor;
    private static BookSearch bookSearch;
    private static BookSorter bookSorter = new BookSorter();
    private static Scanner sc = new Scanner(System.in);

    public static boolean running = true;
    
    public static void main(String[] args) {
    	
    	String heading = """
    			
    			📚 EBENEZER LIBRARY MANAGEMENT SYSTEM
    			
    			""";

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

        do{
            System.out.println(heading);
           
            //input
            prompt();
            String choice = sc.nextLine();
            
            logic(choice);
            
           // sc.close();            
        }while (running);

       
    } 
    public static void prompt() {
    	    	//System.out.println("1. Book Inventory, 2. Book Search & Sorting, 3. Borrower Registry, 4. Lending Tracker, 5. Overdue Monitoring, 6. File logging, 7. Reports & Data Analysis");
    	System.out.println("1. Book Inventory");
        System.out.println("2. Book Search & Sorting");
        System.out.println("3. Borrower Registry");
        System.out.println("4. Lending Tracker");
        System.out.println("5. Overdue Monitoring");
        System.out.println("6. File logging");
        System.out.println("7. Reports & Data Analysis");
        System.out.print("INPUT: ");
    }	
   public static void prompt(String message) {
	   
	   if(message == "BOOK INVENTORY") {
		   System.out.println("\t "+ message);
		   System.out.println("1. ADD BOOK | 2. REMOVE BOOK | 3. LIST BOOKS");
		   
	   }
	   else if(message=="1. SEARCH | 2. SORTING") {
		   
		   System.out.println("\t "+ message);
	   }else if(message=="1. BINARY SEARCH | 2. LINEAR SEARCH") {		   
		   System.out.println("\t "+ message);
	   }else if (message =="1. BINARY SEARCH | 2. lINEAR SEARCH") {
		   System.out.println("\t "+ message);
		   
	   }
	   System.out.print("INPUT: ");
       
   }
    
    public static void logic(String choice) {
    	//Scanner miniScanner = new Scanner(System.in);
    	
    	switch (choice) {

        case "1": // Book Inventory
           // inventory.listBooksSorted(); 
        	
        	prompt("BOOK INVENTORY"); //MINI PROMPT
        	String miniChoice = sc.nextLine();
        	
        	
            switch(miniChoice) {
            case "1": //ADD BOOK
            	System.out.print("Title: "); String title = sc.nextLine();
            	System.out.print("Author: "); String author = sc.nextLine();
            	System.out.print("ISBN: "); String isbn = sc.nextLine();
            	System.out.print("Category: "); String category = sc.nextLine();
            	System.out.print("Year: "); int year = Integer.parseInt(sc.nextLine());
            	System.out.print("Publisher: "); String publisher = sc.nextLine();
            	System.out.print("Shelf: "); String shelf = sc.nextLine();
            	inventory.addBook(new Book(title, author, isbn, category, year, publisher, shelf));          
            	break;
            case "2": //REMOVE BOOK
            	System.out.print("ISBN to remove: "); String removeIsbn = sc.nextLine();
            	inventory.removeBook(removeIsbn);
            	break;
            	case "3": //LIST BOOKS
            		inventory.ReadCSVBooks();
            		break;
            		
            	default:
            		//PENDING
            		break;
            }
            
            break;

        case "2": // Book Search & Sorting
        	prompt("1. SEARCH | 2. SORTING"); //MINI PROMPT
        	miniChoice = sc.nextLine();
        	
        	switch(miniChoice) {
        	case "1": //SEARCH
        		
        		prompt("1. BINARY SEARCH | 2. lINEAR SEARCH"); //MINI PROMPT
        		String miniChoiceLite = sc.nextLine();
        		
        		switch(miniChoiceLite) {
        		case "1": // BINARY SEARCH
        			
        			         			
        			break;
        		case "2": // LINEAR SEARCH
        			
        			break;
        			
        		default:
        			//PENDING
        			break;
        		}
        		
        		break;
        	case "2": //CUSTOME SORT
        		
        		break;
        	
        	default:
        		//PENDING
        		break;
        	}
        	// Sort books by title
        	MyList<Book> allBooks = inventory.getBookTree();   // Already MyList<Book>
        	MyList<Book> sortedBooks = bookSorter.sortByTitle(allBooks);
        	System.out.println("\n📚 Books Sorted by Title:");
        	for (int i = 0; i < sortedBooks.size(); i++) System.out.println(sortedBooks.get(i));
        	
        	
        	// Search book by title
        	System.out.print("Enter title: "); String searchTitle = sc.nextLine();
        	MyList<Book> results = bookSearch.searchByTitle(searchTitle);
        	if (results.size() == 0) System.out.println("⚠ No books found.");
        	else {
        		System.out.println("\n📚 Search Results:");
        		for (int i = 0; i < results.size(); i++) System.out.println(results.get(i));
        	}
        	
            break;

        case "3": // Borrower Registry
        	registry.listBorrowers();
        	
        	 // Register borrower
        	System.out.print("Name: "); String name = sc.nextLine();
        	System.out.print("Borrower ID: "); String id = sc.nextLine();
        	System.out.print("Contact info: "); String contact = sc.nextLine();
        	registry.addBorrower(new Borrower(name, id, contact));
        	
        	//Return book
        	System.out.print("Borrower ID: "); String returnId = sc.nextLine();
        	System.out.print("Book ISBN: "); String returnIsbn = sc.nextLine();
        	tracker.returnBook(returnId, returnIsbn);
            break;

        case "4":// Lending Tracker
        	System.out.print("Borrower ID: "); String borrowerId = sc.nextLine();
        	System.out.print("Book ISBN: "); String borrowIsbn = sc.nextLine();
        	tracker.borrowBook(borrowerId, borrowIsbn);
        	
        	// View transactions
            tracker.listTransactions();
            break;

        case "5": // Overdue Monitoring
            
            // Check overdue books
        	overdueMonitor.checkOverdues();
            break;

        case "6": // File logging
        	FileManager.saveBooks(inventory.getBookTree());
        	FileManager.saveBorrowers(registry.getAllBorrowers());
        	System.out.println("💾 Data saved. Goodbye!");
        	//running = false;
            break;

        case "7": //  Reports & Data Analysis
            break;
        case "q":
        	running = false;
        	break;
        default:
        	System.out.println("⚠ Invalid choice.");
            
    	}
    	
    }

   
}
    



  


