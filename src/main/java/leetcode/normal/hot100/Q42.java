package leetcode.normal.hot100;

import java.util.LinkedList;
import java.util.List;

public class Q42 {
    /**
     * 横着算
     */
//    public int trap(int[] height) {
//        List<Integer> stack = new LinkedList<>();
//        int ans = 0, l = height.length;
//        for (int i = 0; i < l; i++) {
//            int x = height[i];
//            while(stack.size() > 0 && height[stack.get(stack.size() - 1)] < x) {
//                int midNum = height[stack.get(stack.size() - 1)], midIndex = stack.get(stack.size() - 1);
//                stack.remove(stack.size() - 1);
//                if (stack.size() == 0) {
//                    continue;
//                }
//                int leftNum = height[stack.get(stack.size() - 1)], leftIndex = stack.get(stack.size() - 1);
//                int w = i - leftIndex - 1;
//                int h = Math.min(leftNum, x) - midNum;
//                ans += w * h;
//            }
//            stack.add(i);
//        }
//        return ans;
//    }

    /**
     * 竖着算
     * @param args
     */
//    public int trap(int[] height) {
//        int n = height.length;
//        int[] leftMax = new int[n + 1], rightMax = new int[n + 2];
//        for (int i = 0; i < n; i++) {
//            leftMax[i + 1] = Math.max(leftMax[i], height[i]);
//        }
//        for (int i = n; i >= 1; i--) {
//            rightMax[i] = Math.max(rightMax[i + 1], height[i - 1]);
//        }
//        int ans = 0;
//        for (int i = 1; i <= n; i++) {
//            int i1 = Math.min(leftMax[i - 1], rightMax[i + 1]) - height[i - 1];
//            if (i1 > 0) {
//                ans += i1;
//            }
//        }
//        return ans;
//    }

    /**
     * 竖着算
     */
    public int trap(int[] height) {
        int ans = 0, n = height.length;
        int lIdx = 0, rIdx = n - 1;
        int leftMax = 0, rightMax = 0;
        while (lIdx < rIdx) {
            leftMax = Math.max(leftMax, height[lIdx]);
            rightMax = Math.max(rightMax, height[rIdx]);
            if (leftMax < rightMax) {
                ans += leftMax - height[lIdx];
                lIdx++;
            } else {
                ans += rightMax - height[rIdx];
                rIdx--;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] n = new int[]{10,9,7,8};
        int trap = new Q42().trap(n);
        System.out.println(trap);
    }

}
