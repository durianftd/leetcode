package leetcode.weekly;

import java.util.*;

class Router {

    class Traffic {
        int s;
        int d;
        int t;

        public Traffic(int s, int d, int t) {
            this.s = s;
            this.d = d;
            this.t = t;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            Traffic t = (Traffic) o;
            return this.s == t.s && this.d == t.d &&this.t == t.t;
        }

        @Override
        public int hashCode() {
            return Objects.hash(s,d,t);
        }

        @Override
        public String toString() {
            return "s:" + s + "d:" + d + "t:" + t;
        }
    }

    int size = 0;

    class Item {
        // linkedlist的随机访问时间复杂度是O(n) shit
        LinkedList<Traffic> deque = new LinkedList();
        int offset;
    }

    Set<Traffic> set = new HashSet<>();
    Map<Integer, Item> map = new HashMap<>();
    Deque<Traffic> q = new ArrayDeque<>();

    public Router(int memoryLimit) {
        this.size = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        // System.out.println("sdt" + source + destination + timestamp + "map" + map);
        Traffic newT = new Traffic(source, destination, timestamp);
        if (set.contains(newT)) {
            return false;
        }
        Item item = map.getOrDefault(destination, new Item());
        LinkedList<Traffic> deque = item.deque;
        if (set.size() >= size) {
            Traffic traffic = q.pollFirst();
            set.remove(traffic);
            map.get(traffic.d).offset++;
            deque.offerLast(newT);
            set.add(newT);
        } else {
            deque.offerLast(newT);
            set.add(newT);
        }
        q.offerLast(newT);
        map.put(destination, item);
        return true;
    }

    public int[] forwardPacket() {
        // System.out.println("forward" + "map" + map);
        Traffic traffic = q.pollFirst();
        if (traffic == null) {
            return new int[0];
        }
        // System.out.println("forward" + "map" + map + "traffic" + traffic);
        map.get(traffic.d).offset++;
        set.remove(traffic);
        return new int[]{traffic.s, traffic.d, traffic.t};
    }

    public int getCount(int destination, int startTime, int endTime) {
        // System.out.println("getCount" + "map" + map);
        Item item = map.getOrDefault(destination, new Item());
        LinkedList<Traffic> deque = item.deque;
        if (deque.size() == 0) {
            return 0;
        }
        // System.out.println(deque);
        return getLeft(deque, endTime + 1, item.offset) - getLeft(deque, startTime, item.offset);
    }

    private int getLeft(LinkedList<Traffic> list, int time, int left) {
        int right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).t >= time) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // System.out.println(left);
        return left;
    }
}
