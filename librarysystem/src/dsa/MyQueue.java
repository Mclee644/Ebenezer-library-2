package dsa; // Declares the package name as 'dsa'

public class MyQueue<T> { // Generic queue implementation using linked nodes

    private static class Node<T> { // Internal node class for queue elements
        T data; // Data stored in the node
        Node<T> next; // Reference to the next node in the queue
        Node(T data) { this.data = data; } // Constructor to initialize node data
    }

    private Node<T> front, rear; // Pointers to the front and rear of the queue
    private int size; // Tracks the number of elements in the queue

    public void enqueue(T value) {
        // Adds a new element to the rear of the queue
        Node<T> n = new Node<>(value); // Creates a new node
        if (rear != null) rear.next = n; // Links the new node to the current rear
        rear = n; // Updates rear to the new node
        if (front == null) front = n; // If queue was empty, front also points to new node
        size++; // Increments size
    }

    public T dequeue() {
        // Removes and returns the front element of the queue
        if (isEmpty()) return null; // Returns null if queue is empty
        T val = front.data; // Stores the data to return
        front = front.next; // Moves front to the next node
        if (front == null) rear = null; // If queue becomes empty, rear is also null
        size--; // Decrements size
        return val; // Returns the dequeued value
    }

    public boolean isEmpty() { return size == 0; } // Checks if the queue is empty

    public int size() { return size; } // Returns the number of elements in the queue
}