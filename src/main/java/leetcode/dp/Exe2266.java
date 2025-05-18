package leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Exe2266 {

    public int countTexts(String pressedKeys) {
        int mod = (int)1e9 + 7;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2,3);map.put(3,3);map.put(4,3);map.put(5,3);
        map.put(6,3);map.put(7,4);map.put(8,3);map.put(9,4);
        int n = pressedKeys.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(pressedKeys.charAt(i - 1));
            Integer ii = Integer.parseInt(s);
            Integer res = map.get(ii);
            // 2 -> 3
            // 222
            //   *
            for (int j = 1; j < res && i - j - 1 >= 0; j++) {
                if (Objects.equals(pressedKeys.charAt(i - 1), pressedKeys.charAt(i - j - 1))) {
                    dp[i] = (dp[i] + dp[i - j - 1]) % mod;
                }
            }
            dp[i] = (dp[i] + dp[i - 1]) % mod;
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    public static void main(String[] args) {
        int i = new Exe2266().countTexts("22233");
        System.out.println(i);
    }
}
