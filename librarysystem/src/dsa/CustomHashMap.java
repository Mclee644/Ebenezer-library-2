package dsa; // Declares the package name as 'dsa'

public class CustomHashMap<V> { // Custom implementation of a hash map using separate chaining

    // Node for linked list in each bucket
    static class Node<V> {
        String key; // Key for the entry
        V value; // Value associated with the key
        Node<V> next; // Pointer to the next node in the chain

        Node(String key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<V>[] buckets; // Array of buckets to hold linked lists of nodes
    private int capacity; // Total number of buckets
    private int size; // Number of key-value pairs stored

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        capacity = 16; // Default initial capacity
        buckets = new Node[capacity]; // Initializes bucket array
        size = 0; // Starts with zero entries
    }

    private int getIndex(String key) {
        // Computes the index for a key using its hash code
        return Math.abs(key.hashCode()) % capacity;
    }

    /** Add or update a key-value pair */
    public void put(String key, V value) {
        int index = getIndex(key); // Determines bucket index
        Node<V> head = buckets[index]; // Gets the head of the linked list at that bucket

        // Searches for the key in the chain
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value; // Updates value if key already exists
                return;
            }
            head = head.next;
        }

        // Inserts new node at the beginning of the chain
        Node<V> newNode = new Node<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++; // Increments size after successful insertion
    }

    /** Get value by key */
    public V get(String key) {
        int index = getIndex(key); // Determines bucket index
        Node<V> head = buckets[index]; // Gets the head of the chain

        // Searches for the key in the chain
        while (head != null) {
            if (head.key.equals(key)) return head.value; // Returns value if found
            head = head.next;
        }
        return null; // Returns null if key not found
    }

    /** Remove key-value pair */
    public void remove(String key) {
        int index = getIndex(key); // Determines bucket index
        Node<V> head = buckets[index]; // Gets the head of the chain
        Node<V> prev = null; // Tracks previous node for removal

        // Searches for the key in the chain
        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null) prev.next = head.next; // Bypass the node
                else buckets[index] = head.next; // Update head if first node is removed
                size--; // Decrements size
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    /** Check if key exists */
    public boolean containsKey(String key) {
        return get(key) != null; // Returns true if key is found
    }

    /** Get all values as MyArrayList */
    public MyList<V> values() {
        MyList<V> vals = new MyArrayList<>(); // Initializes list to hold values
        for (Node<V> bucket : buckets) {
            Node<V> head = bucket;
            while (head != null) {
                vals.add(head.value); // Adds each value to the list
                head = head.next;
            }
        }
        return vals; // Returns list of values
    }

    /** Get all keys as MyArrayList */
    public MyList<String> keySet() {
        MyList<String> keys = new MyArrayList<>(); // Initializes list to hold keys
        for (Node<V> bucket : buckets) {
            Node<V> head = bucket;
            while (head != null) {
                keys.add(head.key); // Adds each key to the list
                head = head.next;
            }
        }
        return keys; // Returns list of keys
    }

    /** Get total number of key-value pairs */
    public int size() {
        return size; // Returns the number of entries in the map
    }
}