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

    /**
     * 插入情况：
     * 1.插入节点为根节点，则将插入节点变为黑色
     * 2.插入节点的父节点为黑色，则不用调整
     * 3.插入节点的父节点为红色
     *  3.1.插入节点的叔叔节点为红色：将插入节点的父节点及叔叔节点的颜色变为黑色，祖父节点颜色变为红色
     *  3.2.插入节点的叔叔节点为黑色：
     *      3.2.1.插入节点的父节点为祖父节点的左子节点且插入节点为父节点的左子节点：
     *              对祖父节点进行右旋，并将父节点与祖父节点颜色进行互换
     *      3.2.2.插入节点的父节点为祖父节点的右子节点且插入节点为父节点的右子节点：
     *              对祖父节点进行左旋，并将父节点与祖父节点颜色进行互换
     *      3.2.3.插入节点的父节点为祖父节点的左子节点且插入节点为父节点的右子节点：
     *              将父节点进行左旋，此时插入节点为父节点，再让祖父节点进行右旋并与新父节点颜色互换
     *      3.2.4.插入节点的父节点为祖父节点的右子节点且插入节点为父节点的左子节点：
     *              将父节点进行右旋，此时插入节点为父节点，再让祖父节点进行左旋并于新父节点颜色互换
     */

    /**
     * 删除情况:
     *  1.当删除的节点与其子节点颜色有一方为红色时，删除节点后子节点顶上来，并将其子节点的颜色变为黑色
     *  2.当删除节点为黑叶子节点或者删除节点与删除节点的子节点都是黑色时：
     *      2.1.如果删除节点的兄弟节点为黑色且兄弟节点的子节点至少有一个是红节点时：
     *          2.1.1.这个兄弟节点为父节点的左子节点且兄弟节点的红色子结点为其的左子节点：
     *              将删除节点的父节点进行右旋，并将兄弟节点的红色左子节点置为黑色
     *          2.1.2.这个兄弟节点为父节点的右子节点且兄弟节点的红色子节点为其的右子节点：
     *              将删除节点的父节点进行左旋，并将兄弟节点的红色右子节点置为黑色
     *          2.1.3.这个兄弟节点为父节点的左子节点且兄弟节点的红色子节点为其的右子节点：
     *              兄弟节点进行左旋，并将兄弟节点置为红色，兄弟节点的红色子节点(左旋后成为兄弟节点的父节点)置为黑色，
     *              并对删除节点的父节点进行右旋
     *          2.1.4.这个兄弟节点为父节点的右子节点且兄弟节点的红色子节点为其的左子节点:
     *              兄弟节点进行右旋，并将兄弟节点置为红色，兄弟节点的红色子节点(右旋后成为兄弟节点的父节点)置为黑色，
     *              并对删除节点的父节点进行左旋
     *      2.2.如果删除节点的兄弟节点为黑色且兄弟节点的子节点均为黑色：
     *          直接将兄弟节点的颜色置为红色即可
     *      2.3.如果删除节点的兄弟节点为红色(此时兄弟节点一定有子节点且为黑色)：
     *          2.3.1.兄弟节点为父节点的左子节点：
     *              将删除节点的父节点进行右旋，原兄弟节点的颜色变为黑色，父节点的颜色变为红色，此时父节点左旋后存在
     *              黑色的左子节点，再次将父节点的颜色置为黑色，父节点此时的左子节点置为红色
     *          2.3.2.兄弟节点为父节点的右子节点
     *              将删除节点的父节点进行左旋，原兄弟节点的颜色变为黑色，父节点的颜色变为红色，此时父节点右旋后存在
     *              黑色的右子节点，再次将父节点的颜色置为黑色，父节点此时的右子节点置为红色
     *              
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
