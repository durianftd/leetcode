package leetcode.normal.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1, target = -nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }
            while (left < right) {
                int total = nums[left] + nums[right];
                if (total == -nums[i]) {
                    List<Integer> sub = new ArrayList<>();
                    sub.add(nums[i]);
                    sub.add(nums[left]);
                    sub.add(nums[right]);
                    ans.add(sub);
                } else if (total > -nums[i]) {
                    right--;
                    continue;
                } else {
                    left++;
                    continue;
                }
                while (left < right && nums[left + 1] == nums[left]) {
                    left++;
                }
                left++;
                while (right - 1 > left && nums[right - 1] == nums[right]) {
                    right--;
                }
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> lists = new Q15().threeSum(nums);
        System.out.println(lists);
    }
}
