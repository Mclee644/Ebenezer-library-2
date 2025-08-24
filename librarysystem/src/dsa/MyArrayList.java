package dsa; // Declares the package name as 'dsa'

public class MyArrayList<T> implements MyList<T> { // Custom generic array list implementing MyList interface
    private Object[] data; // Internal array to store elements
    private int size; // Number of elements currently in the list

    public MyArrayList() {
        // Initializes the internal array with a default capacity of 10
        data = new Object[10];
        size = 0;
    }

    @Override
    public void add(T value) {
        // Adds a new element to the end of the list
        if (size == data.length) resize(); // Resizes array if full
        data[size++] = value; // Inserts value and increments size
    }

    @Override
    public void remove(int index) {
        // Removes the element at the specified index
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(); // Validates index
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1]; // Shifts elements left to fill the gap
        }
        size--; // Decrements size after removal
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        // Retrieves the element at the specified index
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(); // Validates index
        return (T) data[index]; // Casts and returns the element
    }

    /** Set element at index */
    public void set(int index, T value) {
        // Updates the element at the specified index
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(); // Validates index
        data[index] = value; // Sets the new value
    }

    @Override
    public int size() {
        // Returns the number of elements in the list
        return size;
    }

    private void resize() {
        // Doubles the capacity of the internal array
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) newData[i] = data[i]; // Copies existing elements
        data = newData; // Replaces old array with new one
    }

    @Override
    public String toString() {
        // Returns a string representation of the list
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]); // Appends each element
            if (i < size - 1) sb.append(", "); // Adds comma between elements
        }
        sb.append("]");
        return sb.toString();
    }
}