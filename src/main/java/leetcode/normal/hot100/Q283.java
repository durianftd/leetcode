package leetcode.normal.hot100;

import java.util.Arrays;

public class Q283 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int zeroIndex = 0, nonZeroIndex = 0;

        //官方题解确实更简单， [xxxxxxxxl00000r=======],l左边都是处理好的列表，l到r之间都是0，r右边是待处理的元素。我的这种做法是更通用的考虑吧，因为没有想的很仔细，只是大概处理一下。
        // 否则会有背题的嫌疑。但是，用笔在纸上画出技术方案，其实思路更加清晰。
        while (zeroIndex < nums.length && nonZeroIndex < nums.length) {
            if (nums[zeroIndex] != 0) {
                zeroIndex++;
            } else if (nums[nonZeroIndex] == 0) {
                nonZeroIndex++;
            } else if (zeroIndex < nonZeroIndex){
                int temp = nums[zeroIndex];
                nums[zeroIndex] = nums[nonZeroIndex];
                nums[nonZeroIndex] = temp;
                zeroIndex++;
                nonZeroIndex++;
            } else {
                nonZeroIndex++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        Q283 q = new Q283();
        q.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
