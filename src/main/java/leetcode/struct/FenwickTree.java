package leetcode.struct;

public class FenwickTree {

    private final int[] tree;

    public FenwickTree(int n) {
        tree = new int[n + 1]; // 使用下标 1 到 n
    }

    // a[i] 增加 val
    // 1 <= i <= n
    public void update(int i, int val) {
        for (; i < tree.length; i += i & -i) {
            tree[i] += val;
        }
    }

    // 求前缀和 a[1] + ... + a[i]
    // 1 <= i <= n
    public int pre(int i) {
        int res = 0;
        for (; i > 0; i &= i - 1) {
            res += tree[i];
        }
        return res;
    }

    public static void main(String[] args) {
//        extracted();
        test();
        int k = 2 & -2;
        System.out.println(k);
    }

    private static void extracted() {
        int i = 1, cnt = 20;
        while (cnt-- > 0) {
            int prei = i;
            int y = i & -i;
            i += y;
            System.out.println("prei:" + prei + ",y:" + y + ",afteri:" + i);
        }
    }

    private static void test() {
        for (int i = 1; i < 100; i ++) {
            String bs = Integer.toBinaryString(i);
            String bs1 = Integer.toBinaryString(-i);
            System.out.println("i: " + i + " : " + bs);
            System.out.println("i: " + i + " : " + bs1);
        }
    }
}
