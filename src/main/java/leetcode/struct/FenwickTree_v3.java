package leetcode.struct;

/**
 * 区间修改，区间查询
 * 记住刚用的时候，需要初始化
 */
public class FenwickTree_v3 {

    private int n;
    private int[] C1;  // 维护差分数组D[i]
    private int[] C2;  // 维护i*D[i]

    public FenwickTree_v3(int size) {
        this.n = size;
        this.C1 = new int[n + 2];  // 下标从1开始，多开空间避免越界
        this.C2 = new int[n + 2];
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int[] C, int x, int d) {
        while (x <= n) {
            C[x] += d;
            x += lowbit(x);
        }
    }

    private int sum(int[] C, int x) {
        int res = 0;
        while (x > 0) {
            res += C[x];
            x -= lowbit(x);
        }
        return res;
    }

    // 区间[l,r]加d
    public void rangeAdd(int l, int r, int d) {
        add(C1, l, d);
        add(C1, r + 1, -d);
        add(C2, l, l * d);
        add(C2, r + 1, -(r + 1) * d);
    }

    // 查询前缀和[1,x]
    public int prefixSum(int x) {
        return (x + 1) * sum(C1, x) - sum(C2, x);
    }

    // 查询区间和[l,r]
    public int rangeSum(int l, int r) {
        return prefixSum(r) - prefixSum(l - 1);
    }

    // 测试用例
    public static void main(String[] args) {
        int n = 10;
        FenwickTree_v3 ft = new FenwickTree_v3(n);

        // 区间[2,5]加3
        ft.rangeAdd(2, 5, 3);
        // 区间[3,7]加2
        ft.rangeAdd(3, 7, 2);

        // 查询区间[1,5]的和
        System.out.println("Sum of [1,5]: " + ft.rangeSum(1, 5));  // 应输出3*4 + 2*3 = 18
        // 查询区间[4,6]的和
        System.out.println("Sum of [4,6]: " + ft.rangeSum(4, 6));  // 应输出3*2 + 2*3 = 12
    }
}
