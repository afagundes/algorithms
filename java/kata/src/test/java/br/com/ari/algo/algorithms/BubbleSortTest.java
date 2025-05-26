package br.com.ari.algo.algorithms;

import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class BubbleSortTest {

    @Test
    void testBubbleSort() {
        int[] arr = new int[] { 99, 55, 43, 12, 14, 21, 19, 8, -1, -150, 1000, 995, 994 };
        System.out.println("Before: " + print(arr));

        int[] expected = new int[arr.length];
        System.arraycopy(arr, 0, expected, 0, arr.length);
        Arrays.sort(expected);

        BubbleSort.sort(arr);
        System.out.println("After: " + print(arr));

        for (int i = 0; i < arr.length; i++) {
            assertThat(arr[i]).isEqualTo(expected[i]);
        }
    }

    private String print(int[] arr) {
        return "[ " + Strings.join(Arrays.stream(arr).boxed().toList()).with(", ") + " ]";
    }

}
