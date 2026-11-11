package com.redsegura.tree;

public class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> left;
    public Node<T> right;
    public int height;

    public Node(T data) {
        this.data = data;
        this.height = 1;
    }
}