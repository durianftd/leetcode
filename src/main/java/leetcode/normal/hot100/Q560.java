package leetcode.normal.hot100;

import java.util.HashMap;
import java.util.Map;

public class Q560 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0, ans = 0;
        for (int num : nums) {
            prefixSum += num;
            if (prefixSumMap.containsKey(prefixSum - k)) {
                ans += prefixSumMap.get(prefixSum - k);
            } else {
                prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
            }
        }
        return ans;
    }
}
