package com.gt.struct.tree;

/**
 * 前缀树(字典树)
 * https://blog.csdn.net/weixin_39778570/article/details/81990417
 *
 * @author GTsung
 * @date 2022/1/10
 */
public class TrieTree {

    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        node.pass++;
        int index = 0;
        // 从左往右遍历字符
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a'; // 由字符对应成走向哪条路
            // node.nexts是个英文字符数组
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    public void delete(String word) {
        if (search(word) != 0) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass--;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }
    }

    // word单词加入过几次
    public int search(String word) {
        if (word == null) return 0;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    // 前缀字符串有几个
    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }

    public static class TrieNode {
        // 加前缀树时，这个节点到达过多少次
        public int pass;
        // 节点是否是字符串的结尾节点，如果是，end表示是几个字符串的结尾节点
        public int end;
        public TrieNode[] nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            // 26个英文字符
            nexts = new TrieNode[26];
        }
    }

}
