package leetcode.normal.hot100;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q438 {

    public List<Integer> findAnagrams(String s, String p) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            int index = p.charAt(i) - 'a';
            map.put(index, map.getOrDefault(index, 0) + 1);
        }
        Map<Integer, Integer> cur = new HashMap<>();
        int length = p.length();
        List<Integer> ans = new ArrayList<>();
        int left = 0, right = 0;
        cur.put(s.charAt(right) - 'a', cur.getOrDefault(s.charAt(right) - 'a', 0) + 1);
        while (right < s.length()) {
            if (right - left + 1 == length) {
                if (map.equals(cur)) {
                    ans.add(left);
                }
                right++;
                if (right == s.length()) {
                    break;
                }
                cur.put(s.charAt(right) - 'a', cur.getOrDefault(s.charAt(right) - 'a', 0) + 1);
            } else if (right - left + 1 < length){
                right++;
                if (right == s.length()) {
                    break;
                }
                cur.put(s.charAt(right) - 'a', cur.getOrDefault(s.charAt(right) - 'a', 0) + 1);
            } else {
                left++;
                cur.put(s.charAt(left - 1) - 'a', cur.getOrDefault(s.charAt(left - 1) - 'a', 0) - 1);
                if (cur.get(s.charAt(left - 1) - 'a') == 0) {
                    cur.remove(s.charAt(left - 1) - 'a');
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        map1.put(1, 2);
        map2.put(1, 3);
//        map2.put(2, 3);
        boolean equals = map1.equals(map2);
        System.out.println(equals);

        List<Integer> anagrams = new Q438().findAnagrams("cbaebabacd", "abc");
        System.out.println(anagrams);
    }




}
