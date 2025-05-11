package leetcode.weekly;

import java.util.Arrays;
import java.util.Comparator;

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

    public static void main(String[] args) {
        int[] ints = new w250427().pathExistenceQueries(5, new int[]{1, 8, 3, 4, 2}, 3, new int[][]{{0, 3}, {2, 4}});
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 第4题
     * @param n
     * @param nums
     * @param maxDiff
     * @param queries
     * @return
     */
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, Comparator.comparingInt(i -> nums[i]));

        // rank[i] 表示 nums[i] 是 nums 中的第几小，或者说节点 i 在 idx 中的下标
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[idx[i]] = i;
        }

        // 双指针，从第 i 小的数开始，向左一步，最远能跳到第 left 小的数
        int mx = 32 - Integer.numberOfLeadingZeros(n);
        int[][] pa = new int[n][mx];
        int left = 0;
        for (int i = 0; i < n; i++) {
            while (nums[idx[i]] - nums[idx[left]] > maxDiff) {
                left++;
            }
            pa[i][0] = left;
        }

        // 倍增
        for (int i = 0; i < mx - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                pa[x][i + 1] = pa[p][i];
            }
        }

        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];
            if (l == r) { // 不用跳
                continue;
            }
            l = rank[l];
            r = rank[r];
            if (l > r) {
                int tmp = l;
                l = r;
                r = tmp; // 保证 l 在 r 左边
            }
            // 从 r 开始，向左跳到 l
            int res = 0;
            for (int k = mx - 1; k >= 0; k--) {
                if (pa[r][k] > l) {
                    res |= 1 << k;
                    r = pa[r][k];
                }
            }
            ans[qi] = pa[r][0] > l ? -1 : res + 1; // 再跳一步就能到 l
        }
        return ans;
    }

//    作者：灵茶山艾府
//    链接：https://leetcode.cn/problems/path-existence-queries-in-a-graph-ii/solutions/3663266/pai-xu-shuang-zhi-zhen-bei-zeng-pythonja-ckht/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
