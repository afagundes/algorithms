package br.com.ari.algo.algorithms;

public class BinarySearch {

    private BinarySearch() {
    }

    public static int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            final int mid = (int) Math.floor(left + (right - left) / 2.0);
            final int value = arr[mid];

            if (value == target) {
                return mid;
            }

            if (value > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

}
