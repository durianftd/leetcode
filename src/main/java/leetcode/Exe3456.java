package leetcode;

public class Exe3456 {
    public boolean hasSpecialSubstring(String s, int k) {
        boolean ans = false;
        for (int i = 0; i < s.length(); i++) {
            int j = 0;
            for (; j < k && i + j < s.length(); j++) {
                if (s.charAt(i + j) != s.charAt(i)) {
                    break;
                }
            }
            if (j == k && check(s, i, i + j - 1)) {
                ans = true;
            }
        }
        return ans;
    }

    private boolean check(String s, int start, int end) {
        int l = s.length();
        if (start > 0 && s.charAt(start - 1) == s.charAt(start)) {
            return false;
        }
        if (end < s.length() - 1 && s.charAt(end + 1) == s.charAt(end)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aaabaaa";
        int k = 3;

        boolean b = new Exe3456().hasSpecialSubstring(s, k);
        System.out.println(b);
    }
}
