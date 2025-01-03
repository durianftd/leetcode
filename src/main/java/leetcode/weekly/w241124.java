package leetcode.weekly;

public class w241124 {

    public static void main(String[] args) {
        w241124 w = new w241124();
        long l = w.shiftDistance("abab", "baba", new int[]{100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{1, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        System.out.println(l);
    }
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        long res = 0;
        long[][] map = preMap(nextCost, previousCost);
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            res += map[cs - 'a'][ct -'a'];
        }
        return res;
    }

    private long[][] preMap(int[] next, int[] pre) {
        long[][] res = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j) {
                    continue;
                }
                res[i][j] = getCost(next, pre, i, j);
            }
        }
        return res;
    }

    private long getCost(int[] cost, int[] pre, int i, int j) {
        int k = i;
        long n = 0, m = 0;
        if (i < j) {
            for (; i < j; i++) {
                n += cost[i];
            }
            for (; k + 26 > j; k--) {
                m += pre[(k + 26) % 26];
            }
        } else {
            for (; i - 26 < j; i++) {
                n += cost[i % 26];
            }
            for (; k > j; k--) {
                m += pre[k];
            }
        }
        return Math.min(n, m);
    }
}
