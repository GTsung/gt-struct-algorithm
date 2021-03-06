package com.gt.algorithm.link;

import java.util.LinkedList;

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

    /**
     * 回文鏈表
     */
    private static boolean isHui(ListNode head) {
        if (head == null) return true;
        LinkedList<Integer> stack = new LinkedList<>();
        ListNode h = head;
        int length = 0;
        while (h != null) {
            h = h.next;
            length++;
        }
        h = head;
        for (int i = 0; i < length / 2; i++) {
            stack.push(h.value);
            h = h.next;
        }
        while (h != null) {
            if (stack.pop() != h.value) {
                return false;
            }
            h = h.next;
        }
        return true;
    }

    /**
     * 找大於、小於、等於區域
     *
     * @param node
     * @param val
     */
    private static void findArea(ListNode node, int val) {
        ListNode smallerHeader = null;
        ListNode smallerTail = null;
        ListNode biggerHeader = null;
        ListNode biggerTail = null;
        ListNode sameHeader = null;
        ListNode sameTail = null;

        while (node != null) {
            if (node.value < val) {
                setNode(node, smallerHeader, smallerTail);
            } else if (node.value > val) {
                setNode(node, biggerHeader, biggerTail);
            } else {
                setNode(node, sameHeader, sameTail);
            }
            node = node.next;
        }
    }

    private static void setNode(ListNode node, ListNode headerNode, ListNode tailNode) {
        if (headerNode == null) {
            headerNode = tailNode = node;
        } else {
            tailNode = tailNode.next = node;
        }
    }

    /**
     * 判斷鏈表是否有環
     */
    private static boolean isCircle(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;
        while (true) {
            fast = fast == null ? null : fast.next == null ? null : fast.next.next;
            slow = slow == null ? null : slow.next;
            if (slow == fast) {
                break;
            }
        }
        return slow != null;
    }

    /**
     * 有環鏈表的入環點
     */
    private static ListNode firstToCircle(ListNode node) {
        if (!isCircle(node)) return null;

        ListNode fast = node;
        ListNode slow = node;
        while (true) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        fast = node;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 兩個鏈表第一個相交點
     *
     * @param n1
     * @param n2
     * @return
     */
    private ListNode findIntersectNode(ListNode n1, ListNode n2) {
        if (n1 == null || n2 == null) return null;
        if (!isCircle(n1) && !isCircle(n2)) {
            // 都無環
            return noCircleIntersect(n1, n2);
        } else if (isCircle(n1) && isCircle(n2)) {
            // 都有環
            return circleIntersect(n1, n2);
        }
        // 其餘不可能相交
        return null;
    }

    /**
     * 兩個有環鏈表第一個相交點
     *
     * @param n1
     * @param n2
     * @return
     */
    private ListNode circleIntersect(ListNode n1, ListNode n2) {
        // 查看入環點是否是同一節點
        ListNode firstN1 = firstToCircle(n1);
        ListNode firstN2 = firstToCircle(n2);
        if (firstN1 == firstN2) {
            // 以這個入環點為終點，類似下面的兩個無環鏈表查找第一個相交點方法
            if (firstN1 != null) {
                firstN1.next = null;
            }
            return noCircleIntersect(n1, n2);
        }
        // 入環點不相同，隨意返回firstN1或firstN2任意一個節點
        return firstN1;
    }

    /**
     * 兩個無環鏈表第一個相交點
     *
     * @param n1
     * @param n2
     * @return
     */
    private ListNode noCircleIntersect(ListNode n1, ListNode n2) {
        ListNode h1 = n1;
        ListNode h2 = n2;
        int l1 = 0;
        int l2 = 0;
        while (h1 != null) {
            l1++;
            h1 = h1.next;
        }
        while (h2 != null) {
            l2++;
            h2 = h2.next;
        }
        h1 = n1;
        h2 = n2;
        int moved = Math.abs(l1 - l2);
        for (int i = 0; i < moved; i++) {
            if (l1 < l2) {
                h2 = h2.next;
            } else {
                h1 = h1.next;
            }
        }
        while (h1 != h2) {
            h1 = h1.next;
            h2 = h2.next;
        }
        return h1;
    }

    /**
     * 合并兩個升序的鏈表為一個升序鏈表
     *
     * @param n1
     * @param n2
     * @return
     */
    private static ListNode merge2ListNodes(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }
        if (n1.value < n2.value) {
            n1.next = merge2ListNodes(n1.next, n2);
            return n1;
        } else {
            n2.next = merge2ListNodes(n1, n2.next);
            return n2;
        }
    }

    /**
     * 合并k個升序鏈表為一個升序鏈表(或者使用小頂堆)
     * 先聲明一個空ListNode，遍歷數組merge2賦值給這個空ListNode
     *
     * @param listNodes
     * @return
     */
    private static ListNode mergeKListNodes(ListNode[] listNodes) {
        if (listNodes.length == 0) {
            return null;
        }
        if (listNodes.length == 1) {
            return listNodes[0];
        }
        if (listNodes.length == 2) {
            return merge2ListNodes(listNodes[0], listNodes[1]);
        }
        int mid = listNodes.length / 2;
        ListNode[] left = new ListNode[mid];
        for (int i = 0; i < mid; i++) {
            left[i] = listNodes[i];
        }
        ListNode[] right = new ListNode[listNodes.length - mid];
        for (int i = mid, j = 0; i < listNodes.length; i++, j++) {
            right[j] = listNodes[i];
        }
        return merge2ListNodes(mergeKListNodes(left), mergeKListNodes(right));
    }

    /**
     * 每兩個節點進行互換
     *
     * @param node
     * @return
     */
    private static ListNode reverseTwoNodes(ListNode node) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = node;
        ListNode tmp = dummyHead;
        while (tmp.next != null && tmp.next.next != null) {
            ListNode n1 = tmp.next;
            ListNode n2 = tmp.next.next;
            n1.next = n2.next;
            n2.next = n1;
            tmp.next = n2;
            tmp = n1;
        }
        return dummyHead.next;
    }

    /**
     * 給定一個鏈表和一個小於等於鏈表長度的k，k個節點進行互換
     *
     * @param head
     * @param k
     * @return
     */
    private static ListNode reverseKNodes(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;
        while (head != null) {
            ListNode tail = pre;
            // 查看剩餘長度是否大於等於k
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];

            pre.next = head;
            tail.next = next;
            pre = tail;
            head = tail.next;
        }
        return hair.next;
    }

    private static ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }


}
