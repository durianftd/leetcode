package leetcode.normal.hot100;

import java.util.*;
import java.util.stream.Collectors;

public class Q49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashMap = new HashMap<>();
        for (String s : strs) {
            String s1 = sortString(s);
            List<String> orDefault = hashMap.getOrDefault(s1, new ArrayList<>());
            orDefault.add(s);
            hashMap.put(s1, orDefault);
        }
        return hashMap.values().stream().collect(Collectors.toList());
    }

    private static String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        System.out.println(new Q49().groupAnagrams(strs));
    }
}
