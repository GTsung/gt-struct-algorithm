package com.gt.algorithm.link;

/**
 * @author GTsung
 * @date 2022/1/11
 */
public class LeetLink {

    private static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this(value, null);
        }

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 1.给定两个非空链表，将两个链表的数字相加得到一个新链表
     */
    private static ListNode sumTwoNode(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode first = root;
        int sum = 0;
        int divide = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.value;
            int v2 = l2 == null ? 0 : l2.value;
            sum = v1 + v2 + divide;
            divide = sum / 10;
            first = first.next = new ListNode(sum % 10);
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (divide != 0) {
            first.next = new ListNode(divide);
        }
        return root.next;
    }

    /**
     * 删除链表的倒数第N个节点
     */
    private static ListNode deleteN(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode h1 = head;
        int length = 0;
        while (h1 != null) {
            h1 = h1.next;
            length++;
        }
        if (length < n) {
            return head;
        }
        h1 = head;
        if (length == n) {
            // delete first node
            ListNode res = h1.next;
            h1.next = null;
            return res;
        }

        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if (n == 1) {
            // delete last node
            slow.next = null;
            return head;
        }
        ListNode l = slow.next;
        slow.next = l.next;
        l.next = null;
        return head;
    }

}
