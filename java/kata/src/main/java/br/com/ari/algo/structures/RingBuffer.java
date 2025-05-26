package br.com.ari.algo.structures;

import java.util.Arrays;

public class RingBuffer<T> {

    private static final int DEFAULT_SIZE = 10;

    private final T[] buffer;
    private final boolean lockUnread;
    private final boolean[] available;

    private int writeIndex;
    private int readIndex;

    public RingBuffer() {
        this(DEFAULT_SIZE, false);
    }

    public RingBuffer(int size) {
        this(size, false);
    }

    @SuppressWarnings("unchecked")
    public RingBuffer(int size, boolean lockUnread) {
        this.buffer = (T[]) new Object[size];
        this.writeIndex = 0;
        this.readIndex = 0;
        this.lockUnread = lockUnread;

        this.available = new boolean[size];
        Arrays.fill(this.available, true);

    }

    public void write(T value) {
        final int index = writeIndex % buffer.length;

        if (this.lockUnread && !this.canWrite(index)) {
            throw new IllegalStateException("Element at index " + index + " is locked until read");
        }

        this.buffer[index] = value;
        this.available[index] = false;
        this.writeIndex++;
    }

    public T read() {
        final int index = readIndex % buffer.length;
        this.available[index] = true;
        this.readIndex++;

        return this.buffer[index];
    }

    public int size() {
        return this.buffer.length;
    }

    private boolean canWrite(int index) {
        return !lockUnread || this.available[index];
    }

}
