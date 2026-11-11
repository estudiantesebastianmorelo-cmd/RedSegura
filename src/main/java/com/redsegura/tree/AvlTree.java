package com.redsegura.tree;

public class AvlTree<T extends Comparable<T>> {
    private Node<T> root;
    private int stepCounter = 0; 

    public int getStepCounter() { return stepCounter; }
    public void resetSteps() { stepCounter = 0; }

    private int height(Node<T> N) {
        return (N == null) ? 0 : N.height;
    }

    private int getBalance(Node<T> N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    private Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node<T> insertRec(Node<T> node, T data) {
        if (node == null) return new Node<>(data);

        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = insertRec(node.left, data);
        else if (cmp > 0) node.right = insertRec(node.right, data);
        else return node; // No duplicados

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Rotación LL
        if (balance > 1 && data.compareTo(node.left.data) < 0)
            return rightRotate(node);

        // Rotación RR
        if (balance < -1 && data.compareTo(node.right.data) > 0)
            return leftRotate(node);

        // Rotación LR
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Rotación RL
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public boolean search(T data) {
        stepCounter = 0;
        Node<T> current = root;
        while (current != null) {
            stepCounter++;
            int cmp = data.compareTo(current.data);
            if (cmp == 0) return true;
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return false;
    }
}