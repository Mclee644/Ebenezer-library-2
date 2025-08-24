package core; // Declares the package as 'core'

import dsa.MyList; // Imports the custom list interface
import dsa.MyArrayList; // Imports the custom array list implementation

public class BookSorter { // Defines a class responsible for sorting books

    public MyList<Book> sortByTitle(MyList<Book> books) { // Method to sort books by title
        MyList<Book> sorted = new MyArrayList<>(); // Creates a new list to hold sorted books

        // Copies all books from the input list to the new list
        for (int i = 0; i < books.size(); i++) sorted.add(books.get(i));

        // Implements bubble sort algorithm to sort books by title (case-insensitive)
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                // Compares adjacent book titles and swaps if out of order
                if (sorted.get(j).getTitle().compareToIgnoreCase(sorted.get(j + 1).getTitle()) > 0) {
                    Book temp = sorted.get(j); // Temporarily stores the book at position j
                    sorted.set(j, sorted.get(j + 1)); // Moves the next book to position j
                    sorted.set(j + 1, temp); // Places the stored book in position j+1
                }
            }
        }

        return sorted; // Returns the sorted list
    }
}