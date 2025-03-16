package leetcode.normal.hot100;

public class Q994 {

    public static void main(String[] args) {
        int[][] grid = new int[][]
                {{2,1,1},{1,1,0},{0,1,1}}
        ;
        int i = new Q994().orangesRotting(grid);
        System.out.println(i);
    }

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] != 0) {
                    boolean curB = grid[r][c] == 2;
                    grid[r][c] = 0;
                    if (r - 1 >= 0 && grid[r-1][c] == 1) {
                        uf.union(r * nc + c, (r-1) * nc + c, curB, false);
                    }
                    if (r - 1 >= 0 && grid[r-1][c] == 2) {
                        uf.union(r * nc + c, (r-1) * nc + c, curB, true);
                    }

                    if (r + 1 < nr && grid[r+1][c] == 1) {
                        uf.union(r * nc + c, (r+1) * nc + c, curB, false);
                    }
                    if (r + 1 < nr && grid[r+1][c] == 2) {
                        uf.union(r * nc + c, (r+1) * nc + c, curB, true);
                    }

                    if (c - 1 >= 0 && grid[r][c-1] == 1) {
                        uf.union(r * nc + c, r * nc + c - 1, curB, false);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == 2) {
                        uf.union(r * nc + c, r * nc + c - 1, curB, true);
                    }

                    if (c + 1 < nc && grid[r][c+1] == 1) {
                        uf.union(r * nc + c, r * nc + c + 1,  curB, false);
                    }
                    if (c + 1 < nc && grid[r][c+1] == 2) {
                        uf.union(r * nc + c, r * nc + c + 1, curB, true);
                    }
                }
            }
        }
        return uf.getMinute();
    }


    public static class UnionFind {
        // 连通分量个数
        private int count;
        // 存储一棵树
        private int[] parent;
        // 记录树的“重量”
        private int[] size;

        private int[] badNum;

        public UnionFind(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            this.count = n;
            parent = new int[m * n];
            size = new int[m * n];
            badNum = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] != 0) {
                        parent[i * n + j] = i * n + j;
                        size[i * n + j] = 1;
                        if (grid[i][j] == 2) {
                            badNum[i * n + j] = 1;
                        }
                        ++count;
                    }

                }
            }
        }

        public int getMinute() {
            int ans = -1;
            for (int i = 0; i < parent.length; i++) {
                if (badNum[i] < size[i]) {
                    ans = Math.max(size[i] - badNum[i], ans);
                } else if (badNum[i] == size[i]) {
                    return -1;
                }
            }
            return ans;
        }


        //注意笔试的时候，可能给的参数中的数字是从1~N。
        //需要把这些参数全部-1，才可以
        public void union(int p, int q, boolean pBad, boolean qBad) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ)
                return;

            // 小树接到大树下面，较平衡
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
                badNum[rootP] += badNum[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
                badNum[rootQ] += badNum[rootP];
            }
            count--;
        }

        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }


        public int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        public int count() {
            return count;
        }

        public int maxSize() {
            int max = size[0];
            for (int i = 0; i < size.length; i++) {
                if (size[i] > max)
                    max = size[i];
            }
            return max;
        }

        public int find(int[] parent, int x) {
            return parent[x] == x ? x : (parent[x] = find(parent, parent[x]));
        }

    }
}

