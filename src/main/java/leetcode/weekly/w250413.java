package leetcode.weekly;

import java.math.BigDecimal;
import java.util.Arrays;

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

    public static void main(String[] args) {
//        String z = new w250413().smallestPalindrome("z");
//        System.out.println(z);
//        String z = new w250413().smallestPalindrome("babab");
//        System.out.println(z);
//        z = new w250413().smallestPalindrome("daccad");
//        System.out.println(z);

        String bacab = new w250413().smallestPalindrome("jbukqunfhgoreeroghfnuqkubj",
                212);
        System.out.println(bacab);

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
}
