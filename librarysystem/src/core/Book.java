
package core; // Declares the package name as 'core'

/**
 * Represents a single book in the library.
 * Implements Comparable so it can be ordered by ISBN in the BST.
 */
public class Book implements Comparable<Book> { // Book class implements Comparable interface for sorting by ISBN
    private String title; // Title of the book
    private String author; // Author of the book
    private String isbn; // ISBN identifier
    private String category; // Category or genre of the book
    private int year; // Year of publication
    private String publisher; // Publisher of the book
    private String shelfLocation; // Physical location of the book in the library

    // Constructor to initialize all fields of the Book object
    public Book(String title, String author, String isbn,
                String category, int year, String publisher, String shelfLocation) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.year = year;
        this.publisher = publisher;
        this.shelfLocation = shelfLocation;
    }

    // ===== Getters =====
    public String getTitle() { return title; } // Returns the title
    public String getAuthor() { return author; } // Returns the author
    public String getISBN() { return isbn; } // Returns the ISBN
    public String getCategory() { return category; } // Returns the category
    public int getYear() { return year; } // Returns the year of publication
    public String getPublisher() { return publisher; } // Returns the publisher
    public String getShelfLocation() { return shelfLocation; } // Returns the shelf location

    @Override
    public String toString() {
        // Returns a formatted string representation of the book
        return String.format("📖 %s by %s | ISBN: %s | %s | %d | %s | Shelf: %s",
                title, author, isbn, category, year, publisher, shelfLocation);
    }

    @Override
    public int compareTo(Book other) {
        // Compares books by ISBN, ignoring case sensitivity
        return this.isbn.compareToIgnoreCase(other.isbn);
    }
}
