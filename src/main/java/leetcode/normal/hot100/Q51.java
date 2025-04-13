package leetcode.normal.hot100;

import java.util.*;

public class Q51 {

    List<List<String>> ans = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] map = new char[n][n];
        Set<Integer> hl = new HashSet<>(), lh = new HashSet<>();
        boolean[] col = new boolean[n];
        int[] row = new int[n];
        dfs(0, row, col, hl, lh, map);
        return ans;
    }

    private void dfs(int curRow, int[] row, boolean[] col, Set<Integer> hl, Set<Integer> lh, char[][] map) {
        if (curRow >= map.length) {
            List<String> cur = new ArrayList<>();
            for (int i = 0; i < row.length; i++) {
                char[] data = new char[row.length];
                Arrays.fill(data, '.');
                data[row[i]] = 'Q';
                String s = new String(data);
                cur.add(s);
            }
            ans.add(cur);
            return;
        }
        for (int i = 0; i < map.length; i++) {
            int hl_res = curRow - i;
            int lh_res = curRow + i;
            if (!col[i] && !hl.contains(hl_res) && !lh.contains(lh_res)) {
                col[i] = true;
                hl.add(hl_res);
                lh.add(lh_res);
                row[curRow] = i;
                dfs(curRow + 1, row, col, hl, lh, map);
                col[i] = false;
                hl.remove(hl_res);
                lh.remove(lh_res);
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> ans = new Q51().solveNQueens(4);
        System.out.println(ans);
    }
}
