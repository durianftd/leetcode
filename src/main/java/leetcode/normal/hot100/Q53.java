package leetcode.normal.hot100;

import java.util.PriorityQueue;

public class Q53 {

    /**
     * 这是优先队列，其实没必要，因为我只想要最小，那我直接维护最小的就行了。这里时间复杂度反而上去了，变成nlogn了
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(0);
        int prefixSum = 0;
        for (int i : nums) {
            prefixSum += i;
            ans = Math.max(ans, prefixSum - pq.peek());
            pq.add(prefixSum);
        }
        return ans;
    }

    /**
     * 这是比较好的做法
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int ans = nums[0];
        int prefixSum = 0, m = 0;
        for (int i : nums) {
            prefixSum += i;
            ans = Math.max(ans, prefixSum - m);
            m = Math.min(m, prefixSum);
        }
        return ans;
    }
}
