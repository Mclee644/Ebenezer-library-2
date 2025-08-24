package core; // Declares the package name as 'core'

import dsa.MyArrayList; // Imports a custom implementation of a dynamic array
import dsa.MyList; // Imports a custom list interface

/**
 * Represents a borrower in the library system.
 */
public class Borrower implements Comparable<Borrower> { // Class definition with Comparable interface for sorting by ID
    private String name; // Name of the borrower
    private String id; // Unique ID of the borrower
    private MyList<String> borrowedISBNs; // List of ISBNs for books currently borrowed
    private double fines; // Total fines owed by the borrower
    private String contactInfo; // Contact information (e.g., phone or email)

    // Constructor to initialize borrower details
    public Borrower(String name, String id, String contactInfo) {
        this.name = name;
        this.id = id;
        this.borrowedISBNs = new MyArrayList<>(); // Initializes the list of borrowed books
        this.fines = 0.0; // Initializes fines to zero
        this.contactInfo = contactInfo;
    }

    // ===== Getters =====
    public String getName() { return name; } // Returns the borrower's name
    public String getId() { return id; } // Returns the borrower's ID
    public MyList<String> getBorrowedISBNs() { return borrowedISBNs; } // Returns the list of borrowed ISBNs
    public double getFines() { return fines; } // Returns the total fines
    public String getContactInfo() { return contactInfo; } // Returns contact information

    // ===== Operations =====
    public void borrowBook(String isbn) {
        borrowedISBNs.add(isbn); // Adds a book's ISBN to the borrowed list
    }

    public void returnBook(String isbn) {
        // Searches for the ISBN in the borrowed list and removes it
        for (int i = 0; i < borrowedISBNs.size(); i++) {
            if (borrowedISBNs.get(i).equals(isbn)) {
                borrowedISBNs.remove(i); // Removes the book from the list
                return; // Exits after removing the book
            }
        }
    }

    public void updateFines(double amount) {
        fines += amount; // Adds the specified amount to the borrower's fines
    }

    @Override
    public String toString() {
        // Returns a formatted string representing the borrower's details
        return String.format("👤 %s | ID: %s | Borrowed: %s | Fines: %.2f | Contact: %s",
                name, id, borrowedISBNs.toString(), fines, contactInfo);
    }

    @Override
    public int compareTo(Borrower other) {
        // Compares borrowers by ID, ignoring case sensitivity
        return this.id.compareToIgnoreCase(other.id);
    }
}