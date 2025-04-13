package leetcode.normal.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q17 {

    List<String> ans = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        dfs(0, digits, new StringBuilder(), map);
        return ans;
    }

    private void dfs(int i, String digits, StringBuilder sb, Map<Integer, String> map) {
        if (i == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        String cur = map.get(digits.charAt(i) - '0');
        for (int j = 0; j < cur.length(); j++) {
            char c = cur.charAt(j);
            sb.append(c);
            dfs(i + 1, digits, sb, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
