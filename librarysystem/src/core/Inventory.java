package core;

import dsa.MyList;
import dsa.MyArrayList;
import dsa.MyBST;
import dsa.MyStack;
import dsa.CustomHashMap;

/** Manages all books in the library */
public class Inventory {

    private MyBST<Book> bookTree = new MyBST<>();
    private MyList<Book> recentBooks = new MyArrayList<>();
    private MyStack<Book> removedBooks = new MyStack<>();

    /** Add a book */
    public void addBook(Book book) {
        bookTree.insert(book);
        recentBooks.add(book);
        System.out.println("✅ Book added: " + book.getTitle());
    }

    /** Remove a book by ISBN */
    public boolean removeBook(String isbn) {
        Book target = searchBook(isbn);
        if (target != null) {
            bookTree.delete(target);
            removedBooks.push(target);
            System.out.println("✅ Book removed: " + target.getTitle());
            return true;
        }
        System.out.println("⚠ Book not found.");
        return false;
    }

    /** Search a book by ISBN */
    public Book searchBook(String isbn) {
        MyList<Book> sorted = bookTree.inOrderList();
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i).getISBN().equalsIgnoreCase(isbn)) return sorted.get(i);
        }
        return null;
    }

    /** List all books sorted by ISBN (in-order BST) */
    public MyList<Book> listBooksSorted() {
        MyList<Book> sorted = bookTree.inOrderList();
        for (int i = 0; i < sorted.size(); i++) System.out.println(sorted.get(i));
        return sorted;
    }

    /** List books grouped by category */
    public void listBooksByCategory() {
        CustomHashMap<MyList<Book>> booksByCategory = getBooksByCategory();
        if (booksByCategory.keySet().size() == 0) {
            System.out.println("📂 No books in inventory.");
            return;
        }
        for (int i = 0; i < booksByCategory.keySet().size(); i++) {
            String category = booksByCategory.keySet().get(i);
            MyList<Book> books = booksByCategory.get(category);
            System.out.println("\n📚 Category: " + category);
            for (int j = 0; j < books.size(); j++) System.out.println("  - " + books.get(j));
        }
    }

    /** Get books grouped by category (for reports) */
    public CustomHashMap<MyList<Book>> getBooksByCategory() {
        CustomHashMap<MyList<Book>> map = new CustomHashMap<>();
        MyList<Book> allBooks = bookTree.inOrderList();

        for (int i = 0; i < allBooks.size(); i++) {
            Book b = allBooks.get(i);
            String category = b.getCategory();
            if (!map.containsKey(category)) {
                map.put(category, new MyArrayList<>());
            }
            map.get(category).add(b);
        }

        return map;
    }

    /** Return all books as a list */
    public MyList<Book> getBookTree() {
        return bookTree.inOrderList();
    }
}
