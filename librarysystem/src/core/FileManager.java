package core; // Declares the package name as 'core'

import dsa.MyList; // Imports custom list interface
import dsa.MyArrayList; // Imports custom array list implementation
import java.util.List; // Imports Java's List interface
import java.util.ArrayList; // Imports Java's ArrayList implementation

/**
 * FileManager for saving/loading books and borrowers
 */
public class FileManager { // Defines a utility class for file operations

    private static final String DATA_FOLDER = "data"; // Folder where data files are stored
    private static final String BOOK_FILE = DATA_FOLDER + "/books.txt"; // Path to book data file
    private static final String BORROWER_FILE = DATA_FOLDER + "/borrowers.txt"; // Path to borrower data file

    // --- Utility methods from your version ---
    private static void ensureDataFolderExists() {
        // Ensures the data folder exists; creates it if missing
        java.io.File dir = new java.io.File(DATA_FOLDER);
        if (!dir.exists()) dir.mkdirs();
    }

    private static void ensureFileExists(String filePath) {
        // Ensures the specified file exists; creates it if missing
        ensureDataFolderExists();
        java.io.File file = new java.io.File(filePath);
        if (!file.exists()) {
            try { file.createNewFile(); } catch (java.io.IOException e) { e.printStackTrace(); }
        }
    }

    public static void writeToFile(String filePath, List<String> lines) {
        // Writes a list of strings to the specified file, one line per string
        ensureFileExists(filePath);
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (java.io.IOException e) { e.printStackTrace(); }
    }

    public static List<String> readFromFile(String filePath) {
        // Reads lines from the specified file and returns them as a list of strings
        ensureFileExists(filePath);
        List<String> lines = new ArrayList<>();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) lines.add(line);
        } catch (java.io.IOException e) { e.printStackTrace(); }
        return lines;
    }

    // --- Methods for Books ---
    public static MyList<Book> loadBooks() {
        // Loads book data from file and returns a list of Book objects
        MyList<Book> books = new MyArrayList<>();
        for (String line : readFromFile(BOOK_FILE)) {
            String[] parts = line.split("\\|"); // Splits each line into fields
            if (parts.length == 7) {
                books.add(new Book(
                    parts[0], parts[1], parts[2], parts[3],
                    Integer.parseInt(parts[4]), parts[5], parts[6]
                ));
            }
        }
        return books;
    }

    public static void saveBooks(MyList<Book> books) {
        // Saves a list of Book objects to file in a pipe-delimited format
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            lines.add(String.join("|",
                b.getTitle(), b.getAuthor(), b.getISBN(),
                b.getCategory(), String.valueOf(b.getYear()),
                b.getPublisher(), b.getShelfLocation()
            ));
        }
        writeToFile(BOOK_FILE, lines);
    }

    // --- Methods for Borrowers ---
    public static MyList<Borrower> loadBorrowers() {
        // Loads borrower data from file and returns a list of Borrower objects
        MyList<Borrower> borrowers = new MyArrayList<>();
        for (String line : readFromFile(BORROWER_FILE)) {
            String[] parts = line.split("\\|"); // Splits each line into fields
            if (parts.length == 3) {
                borrowers.add(new Borrower(parts[0], parts[1], parts[2]));
            }
        }
        return borrowers;
    }

    public static void saveBorrowers(MyList<Borrower> borrowers) {
        // Saves a list of Borrower objects to file in a pipe-delimited format
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < borrowers.size(); i++) {
            Borrower b = borrowers.get(i);
            lines.add(String.join("|", b.getName(), b.getId(), b.getContactInfo()));
        }
        writeToFile(BORROWER_FILE, lines);
    }
}