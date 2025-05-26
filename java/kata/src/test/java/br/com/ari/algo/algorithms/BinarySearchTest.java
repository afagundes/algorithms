package br.com.ari.algo.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTest {

    @Test
    void testBinarySearch() {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        assertThat(BinarySearch.search(arr, 5)).isEqualTo(4);
        assertThat(BinarySearch.search(arr, 1)).isZero();
        assertThat(BinarySearch.search(arr, 8)).isEqualTo(7);
        assertThat(BinarySearch.search(arr, 10)).isEqualTo(9);
        assertThat(BinarySearch.search(arr, 11)).isEqualTo(-1);
    }

}
