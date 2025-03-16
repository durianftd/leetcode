package leetcode.normal.hot100;

import org.checkerframework.checker.units.qual.A;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Q46 {

    List<List<Integer>> ans = new ArrayList<>();
    int[] cur;
    public List<List<Integer>> permute(int[] nums) {
        cur = nums;
        dfs(0);
        return ans;
    }

    void dfs(int x) {
        if (x == cur.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int i : cur) {
                list.add(i);
            }
            ans.add(list);
        }
        for (int i = x; i < cur.length; i++) {
            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }
    void swap(int a, int b) {
        int tmp = cur[a];
        cur[a] = cur[b];
        cur[b] = tmp;
    }

//    public static void main(String[] args) {
//        int[] nums = new int[]{1,2,3};
//        Q46 q46 = new Q46();
//        List<List<Integer>> permute = q46.permute(nums);
//        System.out.println(permute);
//        System.out.println(q46.count);
//
//
//    }
}
