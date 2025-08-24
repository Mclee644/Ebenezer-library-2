package dsa; // Declares the package name as 'dsa'

public class MyStack<T> { // Generic stack implementation using linked nodes

    private static class Node<T> { // Internal node class for stack elements
        T data; // Data stored in the node
        Node<T> next; // Reference to the next node below in the stack
        Node(T data) { this.data = data; } // Constructor to initialize node data
    }

    private Node<T> top; // Pointer to the top of the stack
    private int size; // Tracks the number of elements in the stack

    public void push(T value) {
        // Adds a new element to the top of the stack
        Node<T> n = new Node<>(value); // Creates a new node
        n.next = top; // Links new node to current top
        top = n; // Updates top to the new node
        size++; // Increments size
    }

    public T pop() {
        // Removes and returns the top element of the stack
        if (isEmpty()) return null; // Returns null if stack is empty
        T val = top.data; // Stores the data to return
        top = top.next; // Moves top to the next node
        size--; // Decrements size
        return val; // Returns the popped value
    }

    public T peek() {
        // Returns the top element without removing it
        return (isEmpty()) ? null : top.data;
    }

    public boolean isEmpty() { return size == 0; } // Checks if the stack is empty

    public int size() { return size; } // Returns the number of elements in the stack
}