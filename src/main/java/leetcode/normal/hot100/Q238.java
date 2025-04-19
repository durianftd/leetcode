package leetcode.normal.hot100;

public class Q238 {

    /**
     * 前后缀分解，很巧的O（1）空间
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] suf = new int[n];
        suf[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] * nums[i + 1];
        }
        // System.out.println(Arrays.toString(suf));
        // int[] ans = new int[n];
        int pre = 1;
        for (int i = 0; i < n; i++) {
            suf[i] = pre * suf[i];
            pre *= nums[i];
        }
        return suf;
    }
}
