package leetcode.weekly;

import java.util.*;

public class w250426 {

    public String findCommonResponse(List<List<String>> responses) {
        Map<String, Integer> map = new HashMap<>();
        for (List<String> list : responses) {
            Set<String> set = new HashSet<>();
            set.addAll(list);
            for (String s : set) {
                map.put(s, map.getOrDefault(s, 0));
            }
        }
        System.out.println(map);
        Map.Entry<String, Integer> stringIntegerEntry = map.entrySet().stream().min((e1, e2) -> (e2.getValue() - e1.getValue()) == 0 ? (e1.getKey().compareTo(e2.getKey())) : (e2.getValue() - e1.getValue())).orElse(null);
        if (stringIntegerEntry == null) return null;
        return stringIntegerEntry.getKey();

    }
}
