package leetcode.weekly;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class w250406 {

    static class Router {
        int cnt = 0;
        int size = 0;

        List<Traffic> sl = new LinkedList<>();
        Deque<Traffic> q = new LinkedList<>();
        Set<Traffic> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        public Router(int memoryLimit) {
            size = memoryLimit;
        }

        public boolean addPacket(int source, int destination, int timestamp) {
            Traffic traffic = new Traffic(source, destination, timestamp, cnt++);
            if (set.contains(traffic)) {
                return false;
            }
            if (q.isEmpty()) {
                q.offerLast(traffic);
                map.put(traffic.index, 0);
            } else if (q.size() >= size) {
                Traffic first = q.pollFirst();
                Integer index = map.get(first.index);
                sl.remove(index);
                q.offerLast(traffic);
                int xx = 0;
                map.put(traffic.index, xx);
            } else {
                q.offerLast(traffic);
                int xx = 0;
                map.put(traffic.index, xx);
            }
            set.add(traffic);
            return true;
        }

        public int[] forwardPacket() {
            if (q.isEmpty()) {
                return new int[0];
            }
            Traffic first = q.pollFirst();
            return new int[]{first.source, first.destination, first.timestamp};
        }

        public int getCount(int destination, int startTime, int endTime) {
            int left = 0, right = 0;
            return right - left + 1;
        }
    }

    static class Traffic {

        int index;
        int source;
        int destination;
        int timestamp;

        public Traffic() {}

        public Traffic(int source, int destination, int timestamp, int index) {
            this.source = source;
            this.destination = destination;
            this.timestamp = timestamp;
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Traffic traffic = (Traffic) o;
            return source == traffic.source && destination == traffic.destination && timestamp == traffic.timestamp;

        }
    }


    class Pair {
        long s;
        int i;
        public Pair(long s1, int i1) {
            s = s1;
            i = i1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (getClass() != o.getClass()) {
                return false;
            }
            Pair p = (Pair) o;
            return this.s == p.s && this.i == p.i;
        }

        @Override
        public int hashCode() {
            return Objects.hash(s, i);
        }
    }
    // no.4
    public int minimumPairRemoval(int[] nums) {
        TreeSet<Pair> valueSet = new TreeSet<>((a, b) -> a.s == b.s ? a.i - b.i : Long.compare(a.s, b.s));
        TreeSet<Integer> indexSet = new TreeSet<>();
        int n = nums.length;
        int dec = 0, ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int s = nums[i] + nums[i + 1];
            valueSet.add(new Pair(s, i));
            if (nums[i] > nums[i + 1]) {
                dec++;
            }
        }
        for (int i = 0; i < n; i++) {
            indexSet.add(i);
        }
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = nums[i];
        }
        while (dec > 0) {
            Pair pair = valueSet.pollFirst();
            long s = pair.s;
            int i = pair.i;
            System.out.println(indexSet);
            Integer higher = indexSet.higher(i);
            if (longs[i] > longs[higher]) {
                dec--;
            }
            Integer pre = indexSet.lower(i);
            Integer next = indexSet.higher(higher);

            if (pre != null) {
                if (longs[pre] > longs[i]) {
                    dec--;
                }
                if (longs[pre] > s) {
                    dec++;
                }
                valueSet.remove(new Pair(longs[pre] + longs[i], pre));
                valueSet.add(new Pair(longs[pre] + s, pre));
            }

            if (next != null) {
                if (longs[higher] > longs[next]) {
                    dec--;
                }
                if (s > longs[next]) {
                    dec++;
                }
                valueSet.remove(new Pair(longs[higher] + longs[next], higher));
                valueSet.add(new Pair(s + longs[next], i));
            }
            indexSet.remove(higher);
            longs[i] = s;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new w250406().minimumPairRemoval(new int[]{1, 2, 3});
        System.out.println(i);
    }
}
