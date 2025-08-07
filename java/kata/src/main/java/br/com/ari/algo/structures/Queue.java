package br.com.ari.algo.structures;

import java.util.NoSuchElementException;

public class Queue<T> {

    private static class Node<T> {
        T value;
        Node<T> next;
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void enqueue(T value) {
        Node<T> node = new Node<>();
        node.value = value;

        this.size++;

        if (this.tail == null) {
            this.head = this.tail = node;
            return;
        }

        this.tail.next = node;
        this.tail = node;
    }

    public T dequeue() {
        if (this.head == null) {
            throw new NoSuchElementException("Empty queue");
        }

        var node = this.head;
        this.head = this.head.next;
        this.size--;

        if (this.head == null) {
            this.tail = null;
        }

        return node.value;
    }

    public T peek() {
        if (this.head == null) {
            throw new NoSuchElementException("Empty queue");
        }

        return this.head.value;
    }

    public int size() {
        return size;
    }

}
