package br.com.ari.algo.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuickSortTest {

    @Test
    void testQuickSort() {
        int[] arr = new int[] { 10, 2, 14, 9, 13, 8, 7, 14, 1 };
        int[] expected = new int[] { 1, 2, 7, 8, 9, 10, 13, 14, 14 };

        QuickSort.sort(arr);

        assertThat(arr).hasSize(expected.length);

        for (int i = 0; i < arr.length; i++) {
            assertThat(arr[i]).isEqualTo(expected[i]);
        }
    }

}
