package leetcode.normal.hot100;

import java.util.*;
import java.util.stream.Collectors;

public class Q239 {

    public static class Struct {
        private int index;
        private int num;

        public int getIndex() {
            return index;
        }

        public int getNum() {
            return num;
        }

        public Struct(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }

//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int n = nums.length;
//        if (n < k) {
//            return null;
//        }
//        int[] ans = new int[n - k + 1];
//        PriorityQueue<Struct> pq = new PriorityQueue<>((o1, o2) -> o2.num - o1.num);
//        for (int i = 0; i < k; i++) {
//            Struct struct = new Struct(i, nums[i]);
//            pq.add(struct);
//        }
//        for (int i = k; i < nums.length; i++) {
//            pq.add(new Struct(i, nums[i]));
//            while (pq.peek().getIndex() <= i - k) {
//                pq.poll();
//            }
//            ans[i - k] = pq.peek().getNum();
//        }
//        return ans;
//    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n < k) {
            return null;
        }
        int[] ans = new int[n - k + 1];
        LinkedList<Struct> deq = new LinkedList<>();
        int maxI = 0, maxN = -99999999;
        for (int i = 0; i < k; i++) {
            maxN = Math.max(nums[i], maxN);
            if (maxN == nums[i]) {
                maxI = i;
            }
        }
        deq.addFirst(new Struct(maxI, maxN));


        ans[0] = deq.peekLast().getNum();
        for (int i = k; i < n; i++) {
            while (!deq.isEmpty() && deq.peekFirst().getNum() <= nums[i]) {
                deq.pollFirst();
            }
            deq.addFirst(new Struct(i, nums[i]));
            while (!deq.isEmpty() && deq.peekLast().getIndex() <= i - k) {
                deq.pollLast();
            }
            ans[i - k + 1] = deq.peekLast().getNum();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int k = 1;
        int[] ints = new Q239().maxSlidingWindow(nums, k);
        System.out.println(ints);
    }
}
