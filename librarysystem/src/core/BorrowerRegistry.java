package core;

import dsa.MyList;
import dsa.CustomHashMap;
import dsa.MyBST;
import dsa.MyStack;

public class BorrowerRegistry {

    private CustomHashMap<Borrower> borrowerMap = new CustomHashMap<>();
    private MyBST<Borrower> borrowerTree = new MyBST<>();
    private MyStack<Borrower> removedBorrowers = new MyStack<>();

    /** Add a borrower */
    public void addBorrower(Borrower borrower) {
        if (borrowerMap.containsKey(borrower.getId())) {
            System.out.println("⚠ Borrower with this ID already exists.");
            return;
        }
        borrowerMap.put(borrower.getId(), borrower);
        borrowerTree.insert(borrower);
        System.out.println("✅ Borrower added successfully.");
    }

    /** Remove a borrower */
    public boolean removeBorrower(String id) {
        Borrower removed = borrowerMap.get(id);
        if (removed != null) {
            borrowerMap.remove(id);
            borrowerTree.delete(removed);
            removedBorrowers.push(removed);
            System.out.println("✅ Borrower removed successfully.");
            return true;
        }
        System.out.println("⚠ Borrower not found.");
        return false;
    }

    /** Find borrower by ID */
    public Borrower findBorrowerById(String id) {
        return borrowerMap.get(id);
    }

    /** List borrowers */
    public void listBorrowers() {
        MyList<Borrower> sorted = borrowerTree.inOrderList();
        if (sorted.size() == 0) {
            System.out.println("📂 No borrowers registered.");
            return;
        }
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println(sorted.get(i));
        }
    }

    /** Return all borrowers as DSA list (for reports) */
    public MyList<Borrower> getAllBorrowers() {
        return borrowerTree.inOrderList();  // Returns a MyList<Borrower>
    }

    /** Getters */
    public CustomHashMap<Borrower> getBorrowerMap() { return borrowerMap; }
    public MyBST<Borrower> getBorrowerTree() { return borrowerTree; }
    public MyStack<Borrower> getRemovedBorrowers() { return removedBorrowers; }
}
