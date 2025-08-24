package core;

import dsa.MyList;
import dsa.MyArrayList;

/** Provides search functionality for books using DSA lists */
public class BookSearch {

    private MyList<Book> allBooks;

    public BookSearch(MyList<Book> allBooks) {
        this.allBooks = allBooks;
    }

    /** Search books by title (partial match) */
    public MyList<Book> searchByTitle(String title) {
        MyList<Book> results = new MyArrayList<>();
        String term = title.toLowerCase();
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getTitle().toLowerCase().contains(term)) {
                results.add(allBooks.get(i));
            }
        }
        return results;
    }

    /** Search books by author (partial match) */
    public MyList<Book> searchByAuthor(String author) {
        MyList<Book> results = new MyArrayList<>();
        String term = author.toLowerCase();
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getAuthor().toLowerCase().contains(term)) {
                results.add(allBooks.get(i));
            }
        }
        return results;
    }

    /** Search by ISBN (exact match) */
    public Book searchByISBN(String isbn) {
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getISBN().equalsIgnoreCase(isbn)) {
                return allBooks.get(i);
            }
        }
        return null;
    }
}
