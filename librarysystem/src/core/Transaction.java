package core; // Declares the package name as 'core'

import java.time.LocalDate; // Imports LocalDate for handling date values

public class Transaction { // Represents a book lending transaction
    private String bookISBN; // ISBN of the borrowed book
    private String borrowerId; // ID of the borrower
    private LocalDate borrowDate; // Date when the book was borrowed
    private LocalDate returnDate; // Date when the book was returned 
    private String status; // Status of the transaction: "BORROWED" or "RETURNED"

    // Constructor to initialize all fields of the transaction
    public Transaction(String bookISBN, String borrowerId, LocalDate borrowDate, LocalDate returnDate, String status) {
        this.bookISBN = bookISBN;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // ===== Getters =====
    public String getBookISBN() { return bookISBN; } // Returns the book's ISBN
    public String getBorrowerId() { return borrowerId; } // Returns the borrower's ID
    public LocalDate getBorrowDate() { return borrowDate; } // Returns the borrow date
    public LocalDate getReturnDate() { return returnDate; } // Returns the return date
    public String getStatus() { return status; } // Returns the transaction status

    // ===== Setters =====
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; } // Sets the return date
    public void setStatus(String status) { this.status = status; } // Updates the transaction status

    @Override
    public String toString() {
        // Returns a formatted string representation of the transaction
        return String.format("📄 ISBN: %s | Borrower: %s | Borrowed: %s | Returned: %s | Status: %s",
                bookISBN, borrowerId, borrowDate,
                (returnDate != null ? returnDate : "N/A"),
                status);
    }
}