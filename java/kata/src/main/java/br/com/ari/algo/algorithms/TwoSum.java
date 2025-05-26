package br.com.ari.algo.algorithms;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    private TwoSum() {}

    public static int[] solve(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (seen.containsKey(diff)) {
                return new int[]{ seen.get(diff), i };
            }

            seen.put(nums[i], i);
        }

        return new int[2];
    }

}
