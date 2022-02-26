package com.gt.algorithm.offer;

import com.gt.algorithm.link.LinkNode;
import com.gt.algorithm.tree.TreeNode;

import java.util.*;

/**
 * @author GTsung
 * @date 2022/2/21
 */
public class Sample01 {

    /**
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
     * 请找出数组中任意一个重复的数字。
     */
    public int findNum(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;
    }

    public int findNum2(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            if (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
                continue;
            }
            i++;
        }
        return -1;
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public static boolean hasNum(int[][] arr, int k) {
        int wide = arr.length;
        int deep = arr[0].length;
        for (int i = wide - 1, j = 0; i >= 0 && j < deep; ) {
            if (arr[i][j] > k) {
                i--;
            } else if (arr[i][j] < k) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static String replaceSpace(String str) {
        if (str == null || str.length() == 0) return str;
        int length = str.length();
        char[] chars = str.toCharArray();
        char[] newChars = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] == ' ') {
                newChars[size++] = '%';
                newChars[size++] = '2';
                newChars[size++] = '0';
            } else {
                newChars[size++] = chars[i];
            }
        }
        return new String(newChars, 0, size);
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     */
    public static int[] printReverse(LinkNode nodes) {
        if (nodes == null) return new int[0];
        LinkedList<Integer> stack = new LinkedList<>();
        while (nodes != null) {
            stack.push(nodes.val);
        }
        int i = 0;
        int[] arr = new int[stack.size()];
        while (!stack.isEmpty()) {
            arr[i++] = stack.pop();
        }
        return arr;
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字
     */
    static Map<Integer, Integer> map = new HashMap<>();

    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        return build(preOrder, 0, preOrder.length,
                inOrder, 0, inOrder.length);
    }

    private static TreeNode build(int[] preOrder, int preStart, int preEnd,
                                  int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preOrder[preStart];
        int in = map.get(rootVal);
        int leftNum = in;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preOrder, preStart + 1, preStart + leftNum,
                inOrder, inStart, in - 1);
        root.right = build(preOrder, preStart + leftNum + 1, preEnd,
                inOrder, in + 1, inEnd);
        return root;
    }

    /**
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
     */
    public static class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void offer(int val) {
            stack1.push(val);
        }

        public Integer poll() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (!stack2.isEmpty()) {
                return stack2.pop();
            }
            return null;
        }
    }

    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。
     * 斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     */
    public static int fib(int n) {
        if (n < 2) return n;
        int a = 0, b = 0, sum = 1;
        for (int i = 2; i <= n; i++) {
            a = b;
            b = sum;
            sum = a + b;
        }
        return sum;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，
     * 并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。  
     */
    private static int find(int[] arr) {
        int low = arr[0], high = arr.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (arr[pivot] < arr[high]) high = pivot;
            else if (arr[pivot] > arr[high]) low = pivot + 1;
            else high--;
        }
        return arr[low];
    }

    /**
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
     * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
     * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     */
    private static boolean exist(char[][] board, String word) {
        // dfs+剪枝
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        // 判斷邊界,board上下邊界越界或者矩陣中的值與words下標的值不等
        if (i < 0 || i >= board.length
                || j < 0 || j >= board[0].length
                || board[i][j] != words[k]) return false;
        if (k == words.length - 1) return true;
        board[i][j] = '\0'; // 將矩陣中的字符設置為空，防止第二次使用
        boolean res = dfs(board, words, i + 1, j, k + 1)
                || dfs(board, words, i - 1, j, k + 1)
                || dfs(board, words, i, j + 1, k + 1)
                || dfs(board, words, i, j - 1, k + 1);
        // 回溯的時候賦值回去，方便下次查詢
        board[i][j] = words[k];
        return res;
    }

    /**
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
     * 也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
     * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     */
    static class Move {
        int m, n, k;
        boolean[][] visited;

        public int movingCount(int m, int n, int k) {
            this.m = m;
            this.n = n;
            this.k = k;
            this.visited = new boolean[m][n];
            return dfsMove(0, 0, 0, 0);
        }

        // i,j ----> 坐標; si ---> i的數位和  sj ---> j的數位和
        private int dfsMove(int i, int j, int si, int sj) {
            if (i >= m || j >= n || k < si + sj || visited[i][j]) return 0;
            visited[i][j] = true;
            return 1 + dfsMove(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj)
                    + dfsMove(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
        }
    }

    /**
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     */
    private static int cuttingRope(int n) {
        // 將每段繩子切為長度為3的片段最優，剩下的一段有三個取值
        // 為2：不再拆分
        // 為1: 將一段長度為3的繩子切割為2和1兩段，長度為1的與這段合并；即 2 * 2 > 1 * 3
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) (Math.pow(3, a - 1) * 4);
        return (int) (Math.pow(3, a) * 2);
    }

    /**
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），
     * 返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
     */
    private static int hammingWeight(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
     */
    private static double myPow(double x, int n) {
        if (x == 0) return 0;
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }

    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     */
    private static int[] print(int n) {
        int end = (int) (Math.pow(10, n) - 1);
        int[] res = new int[end];
        for (int i = 0; i < end; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点
     */
    private static LinkNode delete(LinkNode head, int val) {
        if (head == null) return head;
        if (head.val == val) return head.next;
        LinkNode pre = head, current = head.next;
        while (current != null && current.val != val) {
            pre = current;
            current = current.next;
        }
        if (current != null) {
            pre.next = current.next;
        }
        return head;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     */
    private static int[] sort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (left < right && arr[left] % 2 == 1) {
                left++;
            }
            if (left < right && arr[right] % 2 == 0) {
                right--;
            }
            if (left < right && arr[left] % 2 == 0 && arr[right] % 2 == 1) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        return arr;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     */
    private static LinkNode getKthFromEnd(LinkNode head, int k) {
        LinkNode fast = head;
        LinkNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     */
    private static LinkNode mergeTwoLink(LinkNode l1, LinkNode l2) {
        LinkNode head = new LinkNode(0), dum = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dum.next = new LinkNode(l1.val);
                l1 = l1.next;
            } else {
                dum.next = new LinkNode(l2.val);
                l2 = l2.next;
            }
            dum = dum.next;
        }
        dum.next = l1 == null ? l2 : l1;
        return head.next;
    }

    /**
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     */
    private static boolean isSubTree(TreeNode a, TreeNode b) {
        return (a != null && b != null) && (rec(a, b) || isSubTree(a.left, b) || isSubTree(a.right, b));
    }

    private static boolean rec(TreeNode a, TreeNode b) {
        if (b == null) return true; // b已經走完則返回true
        if (a == null || a.value != b.value) return false; // a走完表示不匹配
        return rec(a.left, b.left) && rec(a.right, b.right);
    }

    /**
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     */
    private static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     */
    private static boolean isSame(TreeNode root) {
        return root == null || same(root.left, root.right);
    }

    private static boolean same(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left != null && right == null
                || left == null && right != null
                || left.value != right.value) return false;
        return same(left.left, right.right) && same(left.right, right.left);
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
     * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     */
    private static boolean verifyPostOrder(int[] postOrder) {
        // 後序遍歷的數組最後一個元素是根
        return recur(postOrder, 0, postOrder.length - 1);
    }

    private static boolean recur(int[] postOrder, int i, int j) {
        if (i >= j) return true;
        int p = i;
        // 找到右子樹的第一個下標
        while (postOrder[p] < postOrder[j]) p++;
        int m = p;
        while (postOrder[p] > postOrder[j]) p++;
        // 比較p是否為最後一個下標,且比較左子樹和右子樹
        return p == j && recur(postOrder, i, m - 1) && recur(postOrder, m, j - 1);
    }

    /**
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
     * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     */
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    private List<List<Integer>> pathSum(TreeNode root, int target) {
        recurFind(root, target);
        return res;
    }

    private void recurFind(TreeNode root, int target) {
        if (root == null) return;
        path.add(root.value);
        target -= root.value;
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        }
        recurFind(root.left, target);
        recurFind(root.right, target);
        path.removeLast();
    }

    /**
     * 请实现 copyRandomList 函数，复制一个复杂链表。
     * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
     * 还有一个 random 指针指向链表中的任意节点或者 null。
     */
    static class Node {
        int val;
        Node next, random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    private Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
     * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
     */
    static class Solution {
        TreeNode pre, head;

        private TreeNode treeToDoublyList(TreeNode root) {
            if (root == null) return null;
            ds(root); // 中序遍歷
            head.left = pre;
            pre.right = head;
            return head;
        }

        void ds(TreeNode cur) {
            if (cur == null) return;
            ds(cur.left);
            if (pre != null) pre.right = cur;
            else head = cur;
            cur.left = pre;
            pre = cur;
            ds(cur.right);
        }

    }

    /**
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。
     */
    private int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(0, nums[i - 1]);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    /**
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，
     * 打印能拼接出的所有数字中最小的一个。
     */
    private void sortMin(int[] arr) {
        // 拼接數組中的元素，要求拼接的值最小，本意就是排序
    }

    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     */
    private int findMaxNum(String str) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0, j = -1;
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                j = Math.max(j, map.get(str.charAt(i)));
            }
            map.put(str.charAt(i), i);
            res = Math.max(res, i - j);
        }
        return res;
    }

    /**
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     */
    private int maxVal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.max(grid[i][j-1], grid[i-1][j]);
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     */
    private int findUglyInN(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    public static void main(String[] args) {
    }
}
