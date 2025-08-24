package dsa; // Declares the package name as 'dsa'

public interface MyList<T> { // Generic interface defining a custom list structure

    void add(T value); // Adds an element to the list

    void remove(int index); // Removes the element at the specified index

    T get(int index); // Retrieves the element at the specified index

    int size(); // Returns the number of elements in the list

    void set(int index, T value);  // Updates the element at the specified index with a new value
}