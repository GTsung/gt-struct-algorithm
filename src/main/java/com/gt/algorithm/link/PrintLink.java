package com.gt.algorithm.link;

/**
 * 打印链表
 *
 * @author GTsung
 * @date 2022/1/9
 */
public class PrintLink {

    public static void main(String[] args) {
        printReverse(LinkNode.createLinkNodes());
    }

    /**
     * 反转打印链表
     *
     * @param node
     */
    private static void printReverse(LinkNode node) {
        if (node == null) return;
        printReverse(node.next);
        System.out.print(node.val + " ");
    }

    public static void print(LinkNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
