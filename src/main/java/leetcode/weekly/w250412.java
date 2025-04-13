package leetcode.weekly;

import java.util.HashSet;
import java.util.Set;

public class w250412 {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                for (int k = j; k <= n; k++) {
                    set.add( nums[i - 1] ^ nums[j - 1] ^ nums[k - 1]);
                }
            }
        }
        System.out.println(set);
        return set.size();
    }

    public static void main(String[] args) {
        int i = new w250412().uniqueXorTriplets(new int[]{6, 7, 8, 9});
        System.out.println(i);
        int n1 = 6, n2 = 7;
        System.out.println(n1 ^ n2);
        int s1 = 6;
        int s2 = 7;
        int s3 = 8;
        int s4 = 9;
        System.out.println(Integer.toBinaryString(s1));
        System.out.println(Integer.toBinaryString(s2));
        System.out.println(Integer.toBinaryString(s3));
        System.out.println(Integer.toBinaryString(s4));
    }
}
