package dsa;

public class MyBST<T extends Comparable<T>> {
    private static class Node<T> {
        T data;
        Node<T> left, right;
        Node(T data) { this.data = data; }
    }

    private Node<T> root;

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> node, T value) {
        if (node == null) return new Node<>(value);
        if (value.compareTo(node.data) < 0) node.left = insertRec(node.left, value);
        else if (value.compareTo(node.data) > 0) node.right = insertRec(node.right, value);
        return node;
    }

    public boolean contains(T value) {
        return containsRec(root, value);
    }

    private boolean containsRec(Node<T> node, T value) {
        if (node == null) return false;
        if (value.compareTo(node.data) == 0) return true;
        return value.compareTo(node.data) < 0
                ? containsRec(node.left, value)
                : containsRec(node.right, value);
    }

    // ✅ DSA-compliant inOrderList
    public MyList<T> inOrderList() {
        MyList<T> result = new MyArrayList<>();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(Node<T> node, MyList<T> result) {
        if (node != null) {
            inOrderRec(node.left, result);
            result.add(node.data);
            inOrderRec(node.right, result);
        }
    }

 // Inside MyBST.java
    public void delete(T value) {
        root = deleteRec(root, value);
    }

    private Node<T> deleteRec(Node<T> node, T value) {
        if (node == null) return null;

        if (value.compareTo(node.data) < 0) {
            node.left = deleteRec(node.left, value);
        } else if (value.compareTo(node.data) > 0) {
            node.right = deleteRec(node.right, value);
        } else {
            // Node with only one child or no child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Node with two children: get inorder successor
            node.data = minValue(node.right);
            node.right = deleteRec(node.right, node.data);
        }
        return node;
    }

    private T minValue(Node<T> node) {
        T minv = node.data;
        while (node.left != null) {
            minv = node.left.data;
            node = node.left;
        }
        return minv;
    }

}
