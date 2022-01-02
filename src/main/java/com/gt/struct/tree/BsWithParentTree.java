package com.gt.struct.tree;

/**
 * @author GTsung
 * @date 2021/12/19
 */
public class BsWithParentTree<T extends Comparable<? super T>> {

    static class BsNode<T extends Comparable<? super T>> {
        T value;
        BsNode<T> parent;
        BsNode<T> left;
        BsNode<T> right;

        BsNode(T value, BsNode<T> parent, BsNode<T> left, BsNode<T> right) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    private BsNode<T> root;

    public BsNode<T> findMax() {
        return findMax(root);
    }

    private BsNode<T> findMax(BsNode<T> node) {
        if (node == null) return null;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public BsNode<T> finMin() {
        return findMin(root);
    }

    private BsNode<T> findMin(BsNode<T> node) {
        if (node == null) return null;
        if (node.left == null) return node;
        return findMin(node.left);
    }

    /**
     * 寻找前驱节点，即该节点的前一个小于该节点值的节点
     *
     * @param node
     * @return
     */
    public BsNode<T> predecessor(BsNode<T> node) {
        // 该node有左子节点，则找到左子节点的最大值节点
        if (node.left != null) {
            return findMax(node.left);
        }
        // 没有左子节点，判断该节点是否为父节点的右节点，是则返回父节点即可
        BsNode<T> parent = node.parent;
        while (parent != null && parent.right != node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    /**
     * 后继节点
     *
     * @param node
     * @return
     */
    public BsNode<T> successor(BsNode<T> node) {
        // 后继节点找该节点的右子树的最小节点
        if (node.right != null) {
            return findMin(node.right);
        }
        // 查看该节点是否为父节点的左子节点，如果是则返回父节点即可
        BsNode<T> parent = node.parent;
        while (parent != null && parent.left != node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    public void insert(T val) {
        BsNode<T> bsNode = new BsNode<>(val, null, null, null);
        insert(this, bsNode);
    }

    private void insert(BsWithParentTree<T> tree, BsNode<T> node) {

        BsNode<T> x = tree.root;
        BsNode<T> y = null;
        int compareVal;
        while (x != null) {
            y = x;
            compareVal = x.value.compareTo(node.value);
            if (compareVal > 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (y == null) {
            tree.root = node;
        } else {
            compareVal = y.value.compareTo(node.value);
            if (compareVal > 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        }
    }

    public void remove(T val) {
        BsNode<T> node = remove(this, new BsNode<>(val, null, null, null));
        node = null;
    }

    private BsNode<T> remove(BsWithParentTree<T> tree, BsNode<T> node) {
        BsNode<T> x = null;
        BsNode<T> y = null;
        if (node.left == null || node.right == null) {
            x = node;
        } else {
            x = successor(node);
        }

        if (x.left != null) {
            y = x.left;
        } else {
            y = x.right;
        }

        if (y != null) {
            y.parent = x.parent;
        }
        if (x.parent == null) {
            tree.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        if (x != node) {
            node.value = x.value;
        }

        return x;
    }
}
