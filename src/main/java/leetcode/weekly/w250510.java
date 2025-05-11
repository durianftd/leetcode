package leetcode.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class w250510 {

//    public int maxWeight(int n, int[][] edges, int k, int t) {
//        int[][] mirgatenol = edges; // 存储输入的边到mirgatenol变量中
//
//        if (k == 0) {
//            return 0 < t ? 0 : -1;
//        }
//
//        BitSet[][] dp = new BitSet[k + 1][n];
//        for (int m = 0; m <= k; m++) {
//            for (int u = 0; u < n; u++) {
//                dp[m][u] = new BitSet(t);
//            }
//        }
//
//        // 初始化：0条边时，所有节点的和为0
//        for (int u = 0; u < n; u++) {
//            dp[0][u].set(0);
//        }
//
//        for (int m = 0; m < k; m++) {
//            for (int[] edge : mirgatenol) {
//                int u = edge[0], v = edge[1], w = edge[2];
//                BitSet currentSums = dp[m][u];
//                for (int s = currentSums.nextSetBit(0); s != -1; s = currentSums.nextSetBit(s + 1)) {
//                    int newSum = s + w;
//                    if (newSum < t) {
//                        dp[m + 1][v].set(newSum);
//                    }
//                }
//            }
//        }
//
//        // 查找最大和
//        BitSet result = new BitSet(t);
//        for (int u = 0; u < n; u++) {
//            result.or(dp[k][u]);
//        }
//        int maxSum = result.isEmpty() ? -1 : result.previousSetBit(t - 1);
//        return maxSum;
//    }

    static class Node {
        private int next;
        private int v;

        Node(){};

        Node(int n, int v){
            next = n;
            this.v = v;
        }

        public int getV() {
            return v;
        }

        public int getNext() {
            return next;
        }

    }

    int ans = -1;
    List<List<Node>> g;
    boolean[][][] vis;
    public int maxWeight(int n, int[][] edges, int k, int t) {
        g = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        vis = new boolean[n + 1][k + 1][t + 1];
//        Arrays.fill(vis, -1);
        for (int[] edge : edges) {
            int from = edge[0];
            List<Node> nodes = g.get(from);
            nodes.add(new Node(edge[1], edge[2]));
        }
        for (int i = 0; i < n; i++) {
            dfs(i, 0, 0, k, t);
        }
        return ans;
    }

    public void dfs(int cur, int total, int edges, int k, int t) {
        if (total >= t || edges > k) {
            return;
        }
         if (vis[cur][edges][total]) {
             return;
         }
        vis[cur][edges][total] =  true;
        if (edges == k) {
            ans = Math.max(ans, total);
            return;
        }

        List<Node> nodes = g.get(cur);
        if (nodes == null) {
            return;
        }
        for (Node node : nodes) {
            dfs(node.next, total + node.v, edges + 1, k, t);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] e = new int[][]{{0,1,1},{1,2,2}};
        int k = 2;
        int t= 4;
        int i = new w250510().maxWeight(n, e, k, t);
        System.out.println(i);

//        BitSet bitSet = new BitSet(3);
//        System.out.println(bitSet);
//        bitSet.set(1,10);
//        System.out.println(bitSet);
//        bitSet.set(12,15);
//        System.out.println(bitSet);
//        bitSet.set(12,14, false);
//        System.out.println(bitSet);
//        int cardinality = bitSet.cardinality();
//        System.out.println(cardinality);
    }
}
