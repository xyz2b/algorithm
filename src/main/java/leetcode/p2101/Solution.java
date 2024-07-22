package leetcode.p2101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // 点的连通性，有向无权图
    // 计算从某个节点开始所能到达的节点数目
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 构造图
        for(int i = 0; i < n; i++) {
            int[] bomb1 = bombs[i];
            double x1 = bomb1[0];
            double y1 = bomb1[1];
            int r1 = bomb1[2];
            for(int j = i + 1; j < n; j++) {
                int[] bomb2 = bombs[j];
                double x2 = bomb2[0];
                double y2 = bomb2[1];
                int r2 = bomb2[2];

                double dis = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
                if(dis <= r1) {
                    graph[i].add(j);
                }
                if(dis <= r2) {
                    graph[j].add(i);
                }
            }
        }

        // -1表示还未遍历
        int[] visited = new int[n];
        int vCountMax = 0;

        // 计算从某个点可达的节点数目
        for(int i = 0; i < n; i++) {
            Arrays.fill(visited, -1);
            int vCount = dfs(i, graph, visited);
            vCountMax = Math.max(vCountMax, vCount);
        }

        return vCountMax;
    }

    // 从v顶点所能到达的顶点数
    private int dfs(int v , List<Integer>[] graph, int[] visited) {
        visited[v] = 1;

        int vCount = 1;
        for(int nextN : graph[v]) {
            if(visited[nextN] == -1) {
                vCount += dfs(nextN, graph, visited);
            }
        }

        return vCount;
    }

    public static void main(String[] args) {
        int[][] bombs = {{2,1,3},{6,1,4}};
        Solution solution = new Solution();
        System.out.println(solution.maximumDetonation(bombs));
    }
}
