package leetcode;

import java.util.Arrays;

public class Exe300 {

    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int s = erFenAndPut(nums, i, ans);
            if (s == ans) {
                ans++;
            }
            nums[s] = nums[i];
        }
        return ans;
    }

    private int erFenAndPut(int[] nums, int i, int ans) {
        int left = 0, right = ans - 1;
        while (left <= right) {
            int m = left + (right - left) / 2;
            if (nums[m] >= nums[i]) {
                right = m - 1;
            } else {
                left = m + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
//        int[] nums = new int[]{10,9,2,5};
        int i = new Exe300().lengthOfLIS(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(i);
    }
}
