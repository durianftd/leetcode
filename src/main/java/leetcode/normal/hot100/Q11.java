package leetcode.normal.hot100;

public class Q11 {

    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = new int[]{8,7,2,1};
        int i = new Q11().maxArea(height);
        System.out.println(i);
    }
}
