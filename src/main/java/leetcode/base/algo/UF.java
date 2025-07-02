package leetcode.base.algo;

import java.util.Arrays;

public class UF {

    private int[] parent;
    private int[] size;
    private int n;
    public UF(int n) {
        this.n = n;
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        this.size = new int[n];
        Arrays.fill(size, 1);
    }

    public int findRoot(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findRoot(parent[x]);
    }

    public void merge(int i, int j) {
        int r1 = findRoot(i);
        int r2 = findRoot(j);
        if (r1 == r2) {
            return;
        }
        if (size[r1] <= size[r2]) {
            parent[r1] = r2;
            size[r2] += size[r1];
        } else {
            parent[r2] = r1;
            size[r1] += size[r2];
        }
        n--;
    }

    public int getN() {
        return n;
    }


}
