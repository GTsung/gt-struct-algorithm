package com.gt.algorithm.link;

/**
 * 反转链表
 * @author GTsung
 * @date 2022/1/9
 */
public class ReverseLink {

    public static void main(String[] args) {
        LinkNode origin = LinkNode.createLinkNodes();
        PrintLink.print(origin);
        LinkNode node = reversSingleLink(origin);
        PrintLink.print(node);
    }

    /**
     * 反转单链表
     * @param node
     * @return
     */
    private static LinkNode reversSingleLink(LinkNode node) {
        LinkNode head = null;
        LinkNode current = node;
        while (current != null) {
            LinkNode next = current.next;
            current.next = head;
            head = current;
            current = next;
        }
        return head;
    }
}
