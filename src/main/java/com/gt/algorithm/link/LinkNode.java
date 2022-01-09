package com.gt.algorithm.link;

/**
 * @author GTsung
 * @date 2022/1/9
 */
public class LinkNode {
    int val;
    LinkNode next;

    public LinkNode(int val) {
        this(val, null);
    }

    public LinkNode(int val, LinkNode next) {
        this.val = val;
        this.next = next;
    }

    public static LinkNode createLinkNodes() {
        LinkNode head = new LinkNode(12);
        LinkNode one = new LinkNode(4);
        LinkNode two = new LinkNode(7);
        LinkNode three = new LinkNode(2);
        head.next = one;
        one.next = two;
        two.next = three;
        return head;
    }
}
