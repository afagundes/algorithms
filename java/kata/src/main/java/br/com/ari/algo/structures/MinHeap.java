package br.com.ari.algo.structures;

public class MinHeap {

    private static final int BUFFER_SIZE = 5;

    private int length;
    private int[] data;

    public MinHeap() {
        this.length = 0;
        this.allocate();
    }

    public int getLength() {
        return this.length;
    }

    public void insert(int value) {
        if (this.length >= this.data.length) {
            this.allocate();
        }

        this.data[this.length] = value;
        this.heapifyUp(this.length);
        this.length++;
    }

    public int delete() {
        if (this.length == 0) {
            return -1;
        }

        int out = this.data[0];
        this.length--;

        if (this.length == 0) {
            return out;
        }

        this.data[0] = this.data[this.length];
        this.heapifyDown(0);

        return out;
    }

    public int peek() {
        if (this.length == 0) {
            return -1;
        }

        return this.data[0];
    }

    private void allocate() {
        if (this.data == null || this.length == 0) {
            this.data = new int[BUFFER_SIZE];
            return;
        }

        int newSize = (int) Math.floor(this.data.length * 1.5);
        int[] newData = new int[newSize];

        System.arraycopy(this.data, 0, newData, 0, this.length);
        this.data = newData;
    }

    private void heapifyUp(int index) {
        if (index <= 0) {
            return;
        }

        int parentIndex = this.getParentIndex(index);
        int parentValue = this.data[parentIndex];
        int value = this.data[index];

        if (parentValue > value) {
            this.data[index] = parentValue;
            this.data[parentIndex] = value;
            this.heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        int leftIndex = this.getLeftChildIndex(index);
        int rightIndex = this.getRightChildIndex(index);

        if (index >= this.length || leftIndex >= this.length) {
            return;
        }

        int leftValue = this.data[leftIndex];
        int rightValue = this.data[rightIndex];
        int value = this.data[index];

        if (leftValue >= rightValue && value > rightValue) {
            this.data[index] = rightValue;
            this.data[rightIndex] = value;
            this.heapifyDown(rightIndex);
        } else if (rightValue > leftValue && value > leftValue) {
            this.data[index] = leftValue;
            this.data[leftIndex] = value;
            this.heapifyDown(leftIndex);
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

}
