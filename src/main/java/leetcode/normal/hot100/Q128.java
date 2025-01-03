package leetcode.normal.hot100;

import java.util.HashSet;
import java.util.Set;

public class Q128 {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> numSet = new HashSet<>();
        for (int i : nums) {
            numSet.add(i);
        }
        int res = 1;
        for (int i : numSet) {
            if (numSet.contains(i - 1)) {
                continue;
            } else {
                int cur = i;
                while (numSet.contains(cur + 1)) {
                    cur++;
                }
                res = Math.max(res, cur - i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Q128().longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4
                , 6, 0, 1}));

        int[] nums = null;
        int[] nums2 = new int[0];
        // 这个是可以为null的
        System.out.println(nums2.length);

    }
}
