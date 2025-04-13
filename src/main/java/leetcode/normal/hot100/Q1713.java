package leetcode.normal.hot100;

import java.beans.BeanInfo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q1713 {

        static class Node {
            int val;
            Node s1;
            Node s2;

            Node(){}
            Node(int val) {
                this.val = val;
            }

            @Override
            public String toString() {
                return "val:" + val + "left:" + (s1 == null ? "null" : s1.val) + "right:" + (s2 == null ? "null" : s2.val);
            }

        }

        Map<Integer, Node> map = new HashMap<>();

        public int[] restoreArray(int[][] adjacentPairs) {
            int n = adjacentPairs.length;
            int[] ans = new int[n + 1];
            for (int[] nums : adjacentPairs) {
                union(nums[0], nums[1]);
            }
            Node begin = null; //     2 1
            for (Node a : map.values()) {
                if (a.s2 == null) {
                    begin = a;
                }
            }
            int i = 0, last = -1000000;
            while (begin != null) {
                ans[i] = begin.val;
                if (last == -1000000) {
                    begin = begin.s1;
                } else if (begin.s1 != null && begin.s1.val == last){
                    begin = begin.s2;
                } else {
                    begin = begin.s1;
                }
                last = ans[i++];
            }
            return ans;

        }

        private void union(int a, int b) {
            Node nodeA = map.get(a);
            Node nodeB = map.get(b);
            if (nodeA == null) {
                nodeA = new Node(a);
                map.put(a, nodeA);
            }
            if (nodeB == null) {
                nodeB = new Node(b);
                map.put(b, nodeB);
            }

            if (nodeA.s1 == null) {
                nodeA.s1 = nodeB;
            } else {
                nodeA.s2 = nodeB;
            }

            if (nodeB.s1 == null) {
                nodeB.s1 = nodeA;
            } else {
                nodeB.s2 = nodeA;
            }


        }

    public static void main(String[] args) {
        int[][] ints = new int[][]{{2,1},{3,4},{3,2}};

        int[] ints1 = new Q1713().restoreArray(ints);
        System.out.println(Arrays.toString(ints1));
    }

}
