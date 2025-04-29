package leetcode.normal.hot100;

import java.util.Arrays;

public class Q416 {

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        return sum % 2 == 0;
    }
}
