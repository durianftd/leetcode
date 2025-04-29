package leetcode.weekly;

public class w250420 {

    class Data {
        public int m;
        public int[] cnt;

        // 构造方法
        public Data(int m, int[] cnt) {
            this.m = m;
            this.cnt = cnt.clone(); // 防止外部修改原始数组
        }
    }
    class SegmentTree {

        private final int k;
        private final int n;
        private final Data[] tree;

        // 合并两个 Data
        private Data mergeData(Data a, Data b) {
            int[] cnt = a.cnt.clone();
            for (int m = 0; m < k; m++) {
                cnt[a.m * m % k] += b.cnt[m];
            }
            return new Data(a.m * b.m % k, cnt);
        }

        private Data newData(int val) {
            int m = val % k;
            int[] cnt = new int[k];
            cnt[m] = 1;
            return new Data(m, cnt);
        }

        // 线段树维护数组 a
        public SegmentTree(int[] a, int k) {
            this.k = k;
            n = a.length;
            tree = new Data[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
            build(a, 1, 0, n - 1);
        }

        // 更新 a[i] 为 mergeData(a[i], val)
        // 时间复杂度 O(log n)
        public void update(int i, int val) {
            update(1, 0, n - 1, i, val);
        }

        // 返回用 mergeData 合并所有 a[i] 的计算结果，其中 i 在闭区间 [ql, qr] 中
        // 时间复杂度 O(log n)
        public int query(int ql, int qr, int x) {
            return query(1, 0, n - 1, ql, qr).cnt[x];
        }

        // 合并左右儿子的 val 到当前节点的 val
        private void maintain(int node) {
            tree[node] = mergeData(tree[node * 2], tree[node * 2 + 1]);
        }

        // 用 a 初始化线段树
        // 时间复杂度 O(n)
        private void build(int[] a, int node, int l, int r) {
            if (l == r) { // 叶子
                tree[node] = newData(a[l]); // 初始化叶节点的值
                return;
            }
            int m = (l + r) / 2;
            build(a, node * 2, l, m); // 初始化左子树
            build(a, node * 2 + 1, m + 1, r); // 初始化右子树
            maintain(node);
        }

        private void update(int node, int l, int r, int i, int val) {
            if (l == r) { // 叶子（到达目标）
                tree[node] = newData(val);
                return;
            }
            int m = (l + r) / 2;
            if (i <= m) { // i 在左子树
                update(node * 2, l, m, i, val);
            } else { // i 在右子树
                update(node * 2 + 1, m + 1, r, i, val);
            }
            maintain(node);
        }

        private Data query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) { // 当前子树完全在 [ql, qr] 内
                return tree[node];
            }
            int m = (l + r) / 2;
            if (qr <= m) { // [ql, qr] 在左子树
                return query(node * 2, l, m, ql, qr);
            }
            if (ql > m) { // [ql, qr] 在右子树
                return query(node * 2 + 1, m + 1, r, ql, qr);
            }
            Data lRes = query(node * 2, l, m, ql, qr);
            Data rRes = query(node * 2 + 1, m + 1, r, ql, qr);
            return mergeData(lRes, rRes);
        }
    }


    public int[] resultArray(int[] nums, int k, int[][] queries) {
        SegmentTree t = new SegmentTree(nums, k);
        int n = nums.length;
        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int[] q = queries[qi];
            t.update(q[0], q[1]);
            ans[qi] = t.query(q[2], n - 1, q[3]);
        }
        return ans;
    }
}
