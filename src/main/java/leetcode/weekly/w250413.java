package leetcode.weekly;

import leetcode.struct.FenwickTree_v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class w250413 {

    public String smallestPalindrome(String s) {
        int[] a = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int alpha = s.charAt(i) - 'a';
            a[alpha]++;
        }
        // List<int[]> aL = new ArrayList<>();
        // Array.sort(al, (o1, o2) -> {
        //    return o1[0] - o2[0];
        // });
        int j = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 1) {
                j = i;
            }
        }
        if (j >= 0) {
            a[j]--;
        }
        StringBuilder sb = new StringBuilder();
        int cnt = 1, index = 0;
        while (cnt <= s.length() / 2) {
            int num = a[index];
            int cur = num;
            while (cur > 0 && cur > num / 2) {
                sb.append((char)('a' + index));
                cur--;
                cnt++;
            }
//            if(index == j) {
//                sb.deleteCharAt(sb.length() - 1);
//            }
            index++;
        }
        StringBuilder ans = new StringBuilder();
        ans.append(sb);
        String s1 = sb.reverse().toString();
        if (j >= 0) {
            ans.append((char)('a' + j));
        }
        ans.append(s1);
        return ans.toString();
    }

    public String smallestPalindrome(String s, int k) {
        int[] al = new int[26];
        for (int i = 0; i < s.length(); i++) {
            al[s.charAt(i) - 'a']++;
        }
        int index = -1;
        for (int i = 0; i < 26; i++) {
            if (al[i] % 2 == 1) {
                index = i;
                al[i]--;
            }
            al[i] = al[i] / 2;
        }

        char[] array = new char[s.length() / 2];
        if (rank(array.length, al, k) < k) {
            return "";
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 26; j++) {
                if (al[j] != 0) {
                    array[i] = (char)('a' + j);
                    al[j]--;
                    //TODO
                    int c = rank(array.length - i - 1, al, k);
                    if (c >= k) {
                        break;
                    }
                    k -= c;
                    al[j]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(array);
        if (index >= 0) {
            sb.append((char) ('a' + index));
        }

        for (int i = 0; i < array.length; i++) {
            sb.append(array[array.length - i - 1]);
        }
        return sb.toString();

    }

    private int rank(int n, int[] al, int k) {
        long res = 1;
        int sz = 0;
        for (int ints : al) {
            res *= comb(n - sz, ints, k);
            if (res >= k) {
                return k;
            }
            sz += ints;
        }
        return (int)res;
    }

    private int comb(int n, int m, int k) {
        m = Math.min(m, n - m);
        long res = 1;
        for (int i = 1; i <= m; i++) {
            res = res * (n - i + 1) / i;
            if (res >= k) {
                return k;
            }
        }
        return (int) res;
    }


    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        dfs(1, 0, g, in, out);

        int[] weight = new int[n + 1];
        FenwickTree_v1 diff = new FenwickTree_v1(n);

        for (int[] e : edges) {
            update(e[0], e[1], e[2], in, out, weight, diff);
        }

        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                update(q[1], q[2], q[3], in, out, weight, diff);
            } else {
                ans.add(diff.pre(in[q[1]]));
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private int clock = 0;

    private void dfs(int x, int fa, List<Integer>[] g, int[] in, int[] out) {
        in[x] = ++clock; // 进来的时间
        for (int y : g[x]) {
            if (y != fa) {
                dfs(y, x, g, in, out);
            }
        }
        out[x] = clock; // 离开的时间
    }

    private void update(int x, int y, int w, int[] in, int[] out, int[] weight, FenwickTree_v1 diff) {
        // 保证 y 是 x 的儿子
        if (in[x] > in[y]) {
            y = x;
        }
        int d = w - weight[y]; // 边权的增量
        weight[y] = w;
        // 把子树 y 中的最短路长度都增加 d（用差分树状数组维护）
        diff.update(in[y], d);
        diff.update(out[y] + 1, -d);
    }

    public static void main(String[] args) {
//        String z = new w250413().smallestPalindrome("z");
//        System.out.println(z);
//        String z = new w250413().smallestPalindrome("babab");
//        System.out.println(z);
//        z = new w250413().smallestPalindrome("daccad");
//        System.out.println(z);

//        String bacab = new w250413().smallestPalindrome("jbukqunfhgoreeroghfnuqkubj",
//                212);
//        System.out.println(bacab);
        int n = 7;
        int[][] edges = new int[6][3];
        edges[0] = new int[]{1,3,3};
        edges[1] = new int[]{1,7,7};
        edges[2] = new int[]{3,4,5};
        edges[3] = new int[]{2,3,4};
        edges[4] = new int[]{4,5,10};
        edges[5] = new int[]{4,6,11};

        int[][] queries = new int[8][];
        int[] query1 = new int[]{2, 1};
        int[] query2 = new int[]{2, 2};
        int[] query3 = new int[]{2, 3};
        int[] query4 = new int[]{2, 4};
        int[] query5 = new int[]{2, 5};
        int[] query6 = new int[]{2, 6};
        int[] query7 = new int[]{2, 7};
        queries[1] = query1;
        queries[2] = query2;
        queries[3] = query3;
        queries[4] = query4;
        queries[5] = query5;
        queries[6] = query6;
        queries[7] = query7;
        queries[0] = new int[]{1,4,3,6};
        int[] ints = new w250413().treeQueries(7, edges, queries);
        System.out.println(Arrays.toString(ints));
    }
}
