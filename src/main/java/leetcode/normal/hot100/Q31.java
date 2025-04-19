package leetcode.normal.hot100;

import java.util.Arrays;

public class Q31 {

    // 这道理最快是O（n）
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int index = n - 1;
        int minIndex = -1;
        while (index >= 1) {
            if (nums[index - 1] < nums[index]) {
                minIndex = index - 1;
                break;
            }
            index--;
        }
        if (minIndex == -1) {
            //TODO 这里其实翻转数组就行
            Arrays.sort(nums);
            return;
        }
        int minVal = 999999999, valIndex = nums[minIndex + 1];
        for (int i = minIndex + 1; i < n; i++) {
            if (nums[i] > nums[minIndex]) {
                minVal = Math.min(minVal, nums[i]);
                valIndex = nums[i] == minVal ? i : valIndex;
            }
        }
        swap(minIndex, valIndex, nums);

        //      *
        //          *
        //*             *
        //                  *
        //                      *
        //i1   i2  i3  i4  i5   i6
        //交换i1 和i3,然后翻转i2 ~ i6
        //todo 这里其实也是翻转数组就行
        Arrays.sort(nums, minIndex + 1, n);
    }

    private void swap(int i, int j, int[] nums) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
