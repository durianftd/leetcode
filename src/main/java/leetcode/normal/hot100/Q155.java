package leetcode.normal.hot100;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

public class Q155 {

    TreeMap<Integer, Integer> map = new TreeMap<>();
    Deque<Integer> deque = new LinkedList<>();
    public Q155() {

    }

    public void push(int val) {
        deque.offerLast(val);
        Integer i = map.get(val);
        if (i == null) {
            map.put(val, 1);
        } else {
            map.put(val, i + 1);
        }
    }

    public void pop() {
        Integer i = deque.pollLast();
        if (i == null) {
            return;
        }
        Integer cnt = map.get(i);
        cnt = cnt - 1;
        if (cnt == 0) {
            map.remove(i);
        }
    }

    public int top() {
        Integer s = deque.peekLast();
        return s == null ? 0 : s;
    }

    public int getMin() {
        Integer i = map.firstKey();
        return i == null ? 0 : i;
    }
}
