package br.com.ari.algo.structures;

import java.util.*;

public class LinkedList<T> implements List<T> {

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {}

    @SafeVarargs
    public LinkedList(T... args) {
        this.addAll(Arrays.stream(args).toList());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        var node = this.head;

        do {
            if ((o == null && node.value == null) || (node.value != null && node.value.equals(o))) {
                return true;
            }

            node = node.next;
        } while (node != null);

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        var arr = new Object[size];
        var node = this.head;

        for (int i = 0; i < size && node != null; i++, node = node.next) {
            arr[i] = node.value;
        }

        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new IllegalArgumentException("Null array");
        }

        var arr = a.length < size ? new Object[size] : a;
        var node = this.head;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = node == null ? null : node.value;

            if (node != null) {
                node = node.next;
            }
        }

        return (T1[]) arr;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        var node = this.head;

        while (node != null) {
            if (Objects.equals(node.value, o)) {
                if (this.head == node) {
                    this.head = node.next;
                    this.head.prev = null;
                }
                else if (this.tail == node) {
                    this.tail = node.prev;
                    this.tail.next = null;
                }
                else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }

                this.size--;

                return true;
            }

            node = node.next;
        }

        return false;
    }

    // O(n^2)
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index == 0 || this.tail == null) {
            addAll(c);
            return true;
        }

        if (index >= size) {
            throw new IllegalArgumentException("Index out of bound");
        }

        Node<T> node = this.head;
        for (int i = 0; i <= index; i++) {
            if (index == i) {
                break;
            }

            node = node.next;
        }

        for (T value : c) {
            Node<T> newNode = new Node<>();
            newNode.value = value;
            newNode.next = node;
            newNode.prev = node.prev;

            node.prev.next = newNode;
            node.prev = newNode;
            this.size++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            var node = this.head;

            do {
                if (node.value == o || node.value.equals(o)) {
                    if (node.prev == null) {
                        this.head = node.next;
                    }
                    else if (node.next == null) {
                        this.tail = node.prev;
                    }
                    else {
                        node.next.prev = node.prev;
                        node.prev.next = node.next;
                    }

                    this.size--;
                }

                node = node.next;
            } while (node != null);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void clear() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public T get(int index) {
        var node = find(index);
        return node.value;
    }

    @Override
    public T set(int index, T element) {
        var node = find(index);

        Node<T> newNode = new Node<>();
        newNode.value = element;
        newNode.prev = node.prev;
        newNode.next = node.next;

        if (index == 0) {
            this.head = newNode;
            this.head.next.prev = newNode;
        }
        else if (index == size - 1) {
            this.tail = newNode;
            this.tail.prev.next = newNode;
        }
        else {
            newNode.next.prev = newNode;
            newNode.prev.next = newNode;
        }

        node.next = null;
        node.prev = null;

        return element;
    }

    @Override
    public void add(int index, T element) {
        var node = find(index);

        Node<T> newNode = new Node<>();
        newNode.value = element;
        newNode.prev = node.prev;
        newNode.next = node;

        node.prev = newNode;

        if (index == 0) {
            this.head = newNode;
        }
        else if (index == size - 1) {
            this.tail = newNode;
        }

        this.size++;
    }

    @Override
    public T remove(int index) {
        var node = find(index);

        if (index == 0) {
            this.head = node.next;
            this.head.prev = null;
        }
        else if (index == size - 1) {
            this.tail = node.prev;
            this.tail.next = null;
        }
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        this.size--;

        return node.value;
    }

    @Override
    public int indexOf(Object o) {
        var node = this.head;

        int i = 0;
        for (; i < size; i++) {
            if (Objects.equals(o, node.value)) {
                break;
            }

            node = node.next;
        }

        return i;
    }

    @Override
    public int lastIndexOf(Object o) {
        var node = this.head;

        int index = -1;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, node.value)) {
                index = i;
            }

            node = node.next;
        }

        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> newList = new LinkedList<>();
        var node = find(fromIndex);

        for (int i = 0; i < (toIndex - fromIndex); i++) {
            newList.add(node.value);
            node = node.next;
        }

        return newList;
    }

    @Override
    public void addLast(T t) {
        Node<T> node = new Node<>();
        node.value = t;

        this.size++;

        if (this.tail == null) {
            this.head = this.tail = node;
            return;
        }

        node.prev = this.tail;
        this.tail.next = node;
        this.tail = node;
    }

    @Override
    public T removeLast() {
        var node = this.tail;

        this.tail = node.prev;
        this.tail.next = null;

        node.prev = null;
        node.next = null;

        this.size--;

        return node.value;
    }

    private Node<T> find(int index) {
        if (this.size == 0) {
            throw new IllegalArgumentException("Empty list");
        }

        if (index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        var node = this.head;

        for (int i = 1; i <= index; i++) {
            node = node.next;
        }

        return node;
    }

    public class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }

            T value = current.value;
            current = current.next;
            currentIndex++;

            return value;
        }

        @Override
        public void remove() {
            LinkedList.this.remove(currentIndex - 1);
        }
    }

}
