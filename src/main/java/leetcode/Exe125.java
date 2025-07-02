package leetcode;

public class Exe125 {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            while (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }
            System.out.println(l + ":" + r);
            boolean res = checkCharEqual(s.charAt(l), s.charAt(r));
            if (!res) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private boolean checkCharEqual(char a, char b) {
        int x1 = a - 'a';
        int x2 = a - 'A';
        int y1 = b - 'a';
        int y2 = b - 'A';
        if (x1 == y1 || x2 == y1 || x1 == y2 || x2 == y2) {
            return true;
        }
        return false;
    }
}
