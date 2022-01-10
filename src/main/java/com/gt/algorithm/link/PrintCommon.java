package com.gt.algorithm.link;

/**
 * 两个链表打印相同元素
 * @author GTsung
 * @date 2022/1/9
 */
public class PrintCommon {

    public static void main(String[] args) {
        // 两个链表元素较小者向前移动，元素一致则索引一同向前移动
    }
}

// 回文(1.使用额外空间栈;
// 2.快慢指针将中间节点后的节点反转，比较前半部分与后半部分是否相等)

// 给定某一个元素，链表找到这个元素的小区域、等于区域、大于区域
// 6个节点: smallHead、smallTail、equalsHead、equalsTail、bigHead、bigTail

// 有环或者无环的两个链表，得到相交的第一个节点
// 判断有环:快慢指针，当两个指针相等时，将快指针置为头指针，与慢指针均只走一步，相等处即为入环点
// 无环相交: 遍历两个链表求出长度，最后的节点若地址相等则相交，长度长的链表先走长度差，接着一起走找到相交节点
// 两个链表都是有环:1.不相交 2.在入环前相交 3.环内相交


