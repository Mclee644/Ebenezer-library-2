package core; // Declares the package name as 'core'

/**
 * A simple custom dynamic array for storing Strings.
 * Replaces java.util.List and ArrayList.
 */
public class MyStringList { // Defines a custom list class for storing strings
    private String[] data; // Internal array to hold string elements
    private int size; // Tracks the number of elements currently in the list

    public MyStringList() {
        // Initializes the internal array with a default capacity of 10
        data = new String[10];
        size = 0;
    }

    public void add(String value) {
        // Adds a new string to the list, resizing if necessary
        if (size == data.length) {
            resize(); // Doubles the array size when full
        }
        data[size++] = value; // Adds the value and increments the size
    }

    public void remove(String value) {
        // Removes the first occurrence of the specified string
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                // Shift elements left to fill the gap
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                size--; // Decrease the size after removal
                break; // Exit after removing the first match
            }
        }
    }

    public int size() {
        // Returns the current number of elements in the list
        return size;
    }

    public String get(int index) {
        // Returns the string at the specified index
        if (index < 0 || index >= size) {
            // Throws an exception if index is out of bounds
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return data[index];
    }

    private void resize() {
        // Doubles the capacity of the internal array
        String[] newData = new String[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i]; // Copies existing elements to the new array
        }
        data = newData; // Replaces the old array with the new one
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