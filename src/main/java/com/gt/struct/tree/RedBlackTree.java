package com.gt.struct.tree;

/**
 * 2-3节点 https://blog.csdn.net/weixin_39642998/article/details/111094248
 *
 * @author GTsung
 * @date 2021/12/22
 */
public class RedBlackTree<T extends Comparable<? super T>> {

    /**
     * 红黑树特性 O(logN)
     * 1.根节点为黑色
     * 2.红色节点的子节点必须是黑色
     * 3.每个叶子节点(NIL或NULL)为黑色，这里的叶子节点指的是不存在的节点或者NULL节点
     * 4.从一个节点到该结点的子孙节点的所有路径的黑节点数目相同
     */

    private static class RedBlackNode<T extends Comparable<? super T>> {
        T element;
        RedBlackNode<T> left;
        RedBlackNode<T> right;
        int colour;

        RedBlackNode(T element) {
            this(element, null, null);
        }

        public RedBlackNode(T element, RedBlackNode<T> left, RedBlackNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            // 根节点或者NIL节点为黑色
            this.colour = RedBlackTree.BLACK;
        }
    }

    private RedBlackNode<T> header;
    private RedBlackNode<T> nullNode;

    private static final int BLACK = 1;
    private static final int RED = 0;

    private RedBlackNode<T> current;
    private RedBlackNode<T> parent;
    private RedBlackNode<T> grand;
    private RedBlackNode<T> great;

    // 构造方法
    public RedBlackTree() {
        nullNode = new RedBlackNode<>(null);
        nullNode.left = nullNode.right = nullNode;
        header = new RedBlackNode<>(null);
        header.left = header.right = nullNode;
    }



}
