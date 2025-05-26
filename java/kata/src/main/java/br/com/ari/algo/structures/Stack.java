package br.com.ari.algo.structures;

import java.util.NoSuchElementException;

public class Stack<T> {

    private static class Node<T> {
        T value;
        Node<T> prev;
    }

    private Node<T> head;
    private int size;

    public void push(T value) {
        Node<T> node = new Node<>();
        node.value = value;

        this.size++;

        if (this.head == null) {
            this.head = node;
            return;
        }

        node.prev = this.head;
        this.head = node;
    }

    public T pop() {
        if (this.head == null) {
            throw new NoSuchElementException("Empty stack");
        }

        var node = this.head;
        this.head = this.head.prev;
        this.size--;

        return node.value;
    }

    public T peek() {
        if (this.head == null) {
            throw new NoSuchElementException("Empty stack");
        }

        return this.head.value;
    }

    public int size() {
        return size;
    }

}
