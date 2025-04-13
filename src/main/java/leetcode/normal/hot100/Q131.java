package leetcode.normal.hot100;

import java.util.ArrayList;
import java.util.List;

public class Q131 {

    List<List<String>> ans = new ArrayList<>();
    public List<List<String>> partition(String s) {
        dfs(0, s, new ArrayList<>(), new StringBuilder());
        return ans;
    }

    private void dfs(int i, String s, List<String> list, StringBuilder sb) {
        if (i == s.length()) {
            for (String str : list) {
                if (!isP(str)) {
                    return;
                }
            }
            ans.add(new ArrayList<>(list));
            return;
        }
        sb.append(s.charAt(i));
        String cur = sb.toString();

        if (i == s.length() - 1) {
            list.add(sb.toString());
            sb = new StringBuilder();
            dfs(i + 1, s, list, sb);
            list.remove(list.size() - 1);
        } else {
            list.add(sb.toString());
            sb = new StringBuilder();
            dfs(i + 1, s, list, sb);
            list.remove(list.size() - 1);
            sb = new StringBuilder(cur);

            dfs(i + 1, s, list, sb);
        }
    }

    private boolean isP(String s) {
        if (s  == null || s.length() == 0) {
            return false;
        }
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> partition = new Q131().partition(s);
        System.out.println(partition);
    }
}
