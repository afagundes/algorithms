package br.com.ari.algo.structures;

import java.util.*;

public class ArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final String EMPTY_LIST_MESSAGE = "Empty list";

    private T[] arr;
    private int size;
    private int capacity;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
    }

    public ArrayList(Collection<T> collection) {
        this(collection.size());
        this.addAll(collection);
    }

    public ArrayList(ArrayList<T> list) {
        this.size = list.size;
        this.capacity = list.capacity;
        this.arr = copy(list.arr);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(T... values) {
        this.size = values.length;
        this.capacity = values.length;
        this.arr = copy(values);
    }

    public void add(T value) {
        if (size == capacity) {
            resize();
        }

        this.arr[size++] = value;
    }

    public void addAll(Collection<T> collection) {
        for (T item : collection) {
            add(item);
        }
    }

    public void prepend(T value) {
        if (size == capacity) {
            resize();
        }

        for (int i = size; i > 0; i--) {
            this.arr[i] = this.arr[i - 1];
        }

        this.arr[0] = value;
        this.size++;
    }

    public void insertAt(int index, T value) {
        if (size == capacity) {
            resize();
        }

        for (int i = size; i > index; i--) {
            this.arr[i] = this.arr[i - 1];
        }

        this.arr[index] = value;
        this.size++;
    }

    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        this.arr[index] = value;
    }

    public T get(int index) {
        if (this.isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
        }

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return this.arr[index];
    }

    public T getFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
        }

        return this.arr[0];
    }

    public T getLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
        }

        return this.arr[this.size - 1];
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
        }

        T value = this.arr[0];

        for (int i = 0; i < size - 1; i++) {
            this.arr[i] = this.arr[i + 1];
        }

        this.size--;
        this.arr[this.size] = null;

        return value;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
        }

        this.size--;

        T value = this.arr[this.size];
        this.arr[this.size] = null;

        return value;
    }

    public T removeAt(int index) {
        if (this.isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
        }

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T value = this.arr[index];

        for (int i = index; i < size - 1; i++) {
            this.arr[i] = this.arr[i + 1];
        }

        this.size--;
        this.arr[this.size] = null;

        return value;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    // Bubble sort simples
    @SuppressWarnings("unchecked")
    public void sort(Comparator<T> comparator) {
        T[] copy = (T[]) Arrays.copyOf(arr, size, arr.getClass());
        Arrays.sort(copy, comparator);
        System.arraycopy(copy, 0, arr, 0, size);
    }

    public int find(T item) {
        return find(item, null, false);
    }

    public int find(T item, Comparator<T> comparator, boolean sorted) {
        // Linear search - O(n)
        if (comparator == null || !sorted) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(arr[i], item)) {
                    return i;
                }
            }

            return -1;
        }

        // Binary search - O(log n) - way better
        return binarySearch(item, comparator);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof ArrayList<?> other)) return false;
        if (this.size != other.size) return false;

        boolean arrEquals = true;

        for (int i = 0; i < this.size; i++) {
            if (!Objects.equals(this.arr[i], other.arr[i])) {
                arrEquals = false;
                break;
            }
        }

        return arrEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(arr), size, capacity);
    }

    private int binarySearch(T item, Comparator<T> comparator) {
        int left = 0;
        int right = size;

        while (left < right) {
            int mid = (int) Math.floor(left + (right - left) / 2.0);
            T value = arr[mid];

            if (Objects.equals(value, item)) {
                return mid;
            }

            if (comparator.compare(value, item) > 0) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return -1;
    }

    @SuppressWarnings("unchecked")
    public void resize() {
        this.capacity *= 2;

        T[] copy = (T[]) new Object[this.capacity];
        if (size >= 0) System.arraycopy(this.arr, 0, copy, 0, size);

        this.arr = copy;
    }

    @SuppressWarnings("unchecked")
    private T[] copy(T[] values) {
        T[] copy = (T[]) new Object[values.length];
        System.arraycopy(values, 0, copy, 0, values.length);

        return copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator(this);
    }

    public class MyIterator implements Iterator<T> {
        private final ArrayList<T> list;
        private int current;

        public MyIterator(ArrayList<T> list) {
            this.current = 0;
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }

            return list.get(current++);
        }

        @Override
        public void remove() {
            if (current == 0) {
                throw new IllegalStateException("Iterator not positioned on next element");
            }

            this.current--;
            this.list.removeAt(current);
        }
    }

}
