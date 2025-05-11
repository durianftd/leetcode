package leetcode;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;

public class Exe1920 {

    public int[] buildArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x < 0) { // 已搬家
                continue;
            }
            int cur = i;
            while (nums[cur] != i) {
                int nxt = nums[cur];
                nums[cur] = ~nums[nxt]; // 把下一个数搬过来，同时做标记（取反）
                cur = nxt;
            }
            nums[cur] = ~x; // 对于这一组的最后一个数，把起点 x=nums[i] 搬过来
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = ~nums[i]; // 复原
        }
        return nums;
    }

    // -3 -2 -1 || 0 1 2
    // ~取反符号，是对补码进行取反，~0 = -1 ~-1 = 0，一对一对出现
    public static void main(String[] args) {
        int[] ints = new int[]{0,2,1,5,3,4};
        int[] ints1 = new Exe1920().buildArray(ints);
        System.out.println(Arrays.toString(ints1));


        int zero = 0;
        System.out.println(~zero);
        System.out.println(~NumberUtils.INTEGER_MINUS_ONE);

        System.out.println(~-2);
        System.out.println(~1);
        System.out.println(~Integer.MAX_VALUE == Integer.MIN_VALUE);
    }
//
//    作者：灵茶山艾府
//    链接：https://leetcode.cn/problems/build-array-from-permutation/solutions/857713/mo-ni-by-endlesscheng-a7hu/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
