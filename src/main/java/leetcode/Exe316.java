package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Exe316 {

    public String removeDuplicateLetters(String s) {
        int[] nums = new int[26];
        Arrays.fill(nums, -1);
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a'] = i;
        }
        Deque<Character> stack = new LinkedList<>();
        boolean[] contain = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (contain[c - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && stack.peekLast() > c && nums[stack.peekLast() - 'a'] > i) {
                char poll = stack.pollLast();
                contain[poll - 'a'] = false;
            }
            stack.offerLast(c);
            contain[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "cbacdcbc";
        String s1 = new Exe316().removeDuplicateLetters(s);
        System.out.println(s1);
    }
}
