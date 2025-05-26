package br.com.ari.algo.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TwoSumTest {

    @Test
    void testTwoSum() {
        int[] nums = new int[]{ 2,7,11,15 };
        int target = 9;

        int[] result = TwoSum.solve(nums, target);

        assertThat(result).isEqualTo(new int[]{ 0, 1 });
    }

}
