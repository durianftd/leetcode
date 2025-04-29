package leetcode;

/**
 * https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/
 */
public class Exe1483_beizeng {
    private int[][] pa;
    public Exe1483_beizeng(int n, int[] parent) {
        int binaryLen = Integer.toBinaryString(n).length();
        pa = new int[n][binaryLen];
        for (int i = 0; i < n; i++) {
            pa[i][0] = parent[i];
        }

        for (int index = 0; index < binaryLen - 1; index++) {
            for (int i = 0; i < n; i++) {
                int p = pa[i][index];
                pa[i][index + 1] = p == -1 ? -1 : pa[p][index];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int x = Integer.toBinaryString(k).length();
        for (int i = 0; i < x; i++) {
            if (((1 << i) & k) > 0) {
                node = pa[node][i];
            }
            if (node == -1) {
                break;
            }
        }
        return node;
    }
}
