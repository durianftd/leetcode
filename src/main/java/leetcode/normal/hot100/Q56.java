package leetcode.normal.hot100;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

public class Q56 {

    public int[][] merge(int[][] intervals) {
        Deque<int[]> queue = new ArrayDeque<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        System.out.println(Arrays.deepToString(intervals));
        for (int[] interval : intervals) {
            while (!queue.isEmpty()) {
                int[] pop = queue.pollLast();
                if (interval[0] <= pop[1]) {
                    interval[0] = Math.min(interval[0], pop[0]);
                }
            }
            queue.offerLast(interval);
        }
        int[][] ans = new int[queue.size()][2];
        int index = 0;
        while (!queue.isEmpty()) {
            ans[index++] = queue.pollFirst();
        }
        return ans;
    }
}
