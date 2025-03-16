package leetcode.normal.hot100;

public class Q121 {
    public int maxProfit(int[] prices) {
        int min = 999999, ans = 0;
        for (int i : prices) {
            if (i > min) {
                ans = Math.max(ans, i - min);
            }
            min = Math.min(min, i);
        }
        return ans;
    }
}
