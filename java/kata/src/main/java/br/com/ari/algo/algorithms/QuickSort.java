package br.com.ari.algo.algorithms;

public class QuickSort {

    private QuickSort() {}

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        final int pivot = partition(arr, low, high);

        sort(arr, low, pivot - 1);
        sort(arr, pivot + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        final int pivot = arr[high];
        int index = low - 1;

        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, ++index);
            }
        }

        swap(arr, high, ++index);

        return index;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
