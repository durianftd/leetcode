package leetcode.normal.hot100;

import leetcode.normal.hot100.Q25.*;
public class Q148 {


    public ListNode sortList(ListNode head) {
        if (head == null) return null;

        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        ListNode dummy =  new ListNode(0);
        dummy.next = head;
        for (int cl = 1; cl < len; cl *= 2) {
            ListNode pre = dummy;
            ListNode cur = pre.next;
            while (cur != null) {
                // 计算l1是否够长cl
                int l1Count = 0;
                int l2Count = 0;
                ListNode l1 = cur;
                while (l1Count < cl && cur != null) {
                    l1Count++;
                    cur = cur.next;
                }
                if (l1Count < cl) {
                    // 说明l1都不够
                    break;
                }
                ListNode l2 = cur;
                while (l2Count < cl && cur != null) {
                    l2Count++;
                    cur = cur.next;
                }

                while (l1Count > 0 && l2Count > 0) {
                    if (l1.val <= l2.val) {
                        pre.next = l1;
                        l1 = l1.next;
                        l1Count--;
                    } else {
                        pre.next = l2;
                        l2 = l2.next;
                        l2Count--;
                    }
                    pre = pre.next;
                }

                if (l1Count == 0) pre.next = l2;
                if (l2Count == 0) pre.next = l1;

                while (l1Count != 0 || l2Count != 0) {
                    if (l1Count > 0) l1Count--;
                    if (l2Count > 0) l2Count--;
                    pre = pre.next;
                }
                pre.next = cur;
            }
        }
        return dummy.next;
    }
}
