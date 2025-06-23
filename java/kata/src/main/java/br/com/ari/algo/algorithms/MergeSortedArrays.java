package br.com.ari.algo.algorithms;

public class MergeSortedArrays {

    private MergeSortedArrays() {
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int resultIdx = 0;

        int i = 0;
        int j = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                result[resultIdx++] = arr1[i++];
            } else {
                result[resultIdx++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[resultIdx++] = arr1[i++];
        }

        while (j < arr2.length) {
            result[resultIdx++] = arr2[j++];
        }

        return result;
    }

}
