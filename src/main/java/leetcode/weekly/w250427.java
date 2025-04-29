package leetcode.weekly;

import java.util.Arrays;

public class w250427 {

    int[] ans;
    Boolean[][] visited;
    int n;
    int[] pow;
    int k;
    public int[] concatenatedDivisibility(int[] nums, int k) {
        Arrays.sort(nums);
        n = nums.length;
        this.k = k;
        ans = new int[n];
        visited = new Boolean[(1 << n)][k];
        pow = new int[n];
        for (int i = 0; i < n; i++) {
            int length = String.valueOf(nums[i]).length();
            pow[i] = (int) Math.pow(10, length);
            pow[i] %= k;
        }
        boolean can = dfs((1 << n) - 1, 0, nums);
        if (!can) {
            return new int[]{};
        }
        return ans;
    }

    /**
     * 这道题是真的秀，需要注意的地方太多了：
     * ①(x * pow[i] + nums[i]) % k 用了mod的定律 (a*b) mod k = [(a mod k) * b] mod k = [(a mod k) * (b mod k)] mod k
     * ②visited数组，太秀了。不太好理解其实
     * @param s
     * @param x
     * @param nums
     * @return
     */
    private boolean dfs(int s, int x, int[] nums) {
        if (s == 0) {
            return x == 0;
        }
        if (visited[s][x] != null) {
            return visited[s][x];
        }
        for (int i = 0; i < n; i++) {
            if ((s & (1 << i)) > 0 && dfs(s ^ (1 << i), (x * pow[i] + nums[i]) % k, nums)) {
                ans[n - Integer.bitCount(s)] = nums[i];
                return visited[s][x] = true;
            }
        }
        return visited[s][x] = false;
    }
}
