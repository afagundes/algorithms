package br.com.ari.algo.algorithms;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MergeSortedArraysTest {

    @Test
    void testMergeSortedArrays() {
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {2, 4, 6, 8, 10};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int[] result = MergeSortedArrays.merge(array1, array2);
        Assertions.assertThat(result).isEqualTo(expected);
    }
    
}
