package com.gt.struct.graph;


import java.util.*;

/**
 * 图
 *
 * @author GTsung
 * @date 2022/1/10
 */
public class Graph {

    public Map<Integer, Node> nodes;
    public Set<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    /**
     * 点
     */
    public static class Node {
        public int value;
        // 入度
        public int in;
        // 出度
        public int out;
        // 属于此节点的边A->B  那么A的nexts中包含B 而B的nexts中无
        public List<Node> nexts;
        public List<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    /**
     * 边
     */
    public static class Edge {
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

}
