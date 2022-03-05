package com.gt.algorithm.zuo.lesson02;

/**
 * @author GTsung
 * @date 2022/3/3
 */
public class IslandTest {

    public static void main(String[] args) {

    }

    // 一个矩阵，只有0和1两个数，每个位置都可以和上下左右位置连接，求连续为1的岛的个数
    private static int countIslands(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int res = 0;
        for (int i = 0; i< N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1) {
                    res++;
                    infect(m, i, j, N, M);
                }
            }
        }
        return res;
    }

    private static void infect(int[][] m, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
            return;
        }
        m[i][j] = 2;
        infect(m, i + 1, j, N, M);
        infect(m, i - 1, j, N, M);
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
    }
}
