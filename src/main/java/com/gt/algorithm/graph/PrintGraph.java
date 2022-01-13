package com.gt.algorithm.graph;

import com.gt.struct.graph.Graph;

import java.util.*;

/**
 * @author GTsung
 * @date 2022/1/10
 */
public class PrintGraph {

    public static void main(String[] args) {

    }

    /**
     * N*3的矩阵 [from节点上的值, to节点上的值, weight]
     *
     * @param matrix
     * @return
     */
    private static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Graph.Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Graph.Node(to));
            }
            Graph.Node fromNode = graph.nodes.get(from);
            Graph.Node toNode = graph.nodes.get(to);
            Graph.Edge newEdge = new Graph.Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }

    /**
     * 图的宽度优先遍历
     */
    public static void bfs(Graph.Node node) {
        if (node == null) return;
        Queue<Graph.Node> queue = new LinkedList<>();
        Set<Graph.Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Graph.Node cur = queue.poll();
            System.out.println(cur.value);
            for (Graph.Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.offer(next);
                }
            }
        }
    }


    /**
     * 图的深度优先遍历
     */
    private static void dfs(Graph.Node node) {
        if (node == null) return;
        LinkedList<Graph.Node> stack = new LinkedList<>();
        Set<Graph.Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Graph.Node cur = stack.pop();
            for (Graph.Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }


    /**
     * 拓扑排序
     */
    private static List<Graph.Node> sortedTopology(Graph graph) {
        // key为node，value为node的入度
        Map<Graph.Node, Integer> inMap = new HashMap<>();
        // 入度为0的进入队列
        Queue<Graph.Node> zeroInQueue = new LinkedList<>();
        for (Graph.Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.offer(node);
            }
        }
        // 拓扑排序的结果
        List<Graph.Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Graph.Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Graph.Node next : cur.nexts) {
                // 将入度减1
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.offer(next);
                }
            }
        }
        return result;
    }

    // 無向圖:最小生成树
    // https://blog.csdn.net/a2392008643/article/details/81781766

}
