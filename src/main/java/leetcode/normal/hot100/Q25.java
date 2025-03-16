package leetcode.normal.hot100;

import lombok.Data;

public class Q25 {

    @Data
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    // 1.造一个虚拟头结点
    // 2.看有咩有k个
    // 3.k个一组翻转列表
    // 4.把上一组的尾和反转后的头相连，然后把尾和头重新指定一下
    // 5.while 循环进行的条件是头不为null
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, p = head;
        while (p != null) {
            ListNode cur = p;
            int index = 1;
            while (cur.next != null && index < k) {
                index++;
                cur = cur.next;
            }
            if (index < k) {
                break;
            } else {
                pre.next = reverseAndReturnHead(p, k);
                pre = p;
                p = p.next;
            }
        }
        return dummy.next;
    }

    public ListNode reverseAndReturnHead(ListNode head, int k) {
        ListNode pre = null;
        ListNode t = head;
        while (head != null && head.next != null && k > 0) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            k--;
        }
        t.next = head;
        return pre;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode listNode = new Q25().reverseKGroup(node1, 3);
        while (listNode != null) {
            System.out.println(listNode);
            listNode = listNode.next;
        }
    }

}
