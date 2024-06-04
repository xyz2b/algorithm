package leetcode.p3067;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        graph = new List[n];
        // 构建图的邻接矩阵
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int w = edge[2];
            if (graph[a] == null) {
                graph[a] = new ArrayList<>();
            }
            if (graph[b] == null) {
                graph[b] = new ArrayList<>();
            }
            graph[a].add(new int[]{b, w});
            graph[b].add(new int[]{a, w});
        }

        int[] ret = new int[n];
        // 以图中的每个节点作为根，dfs统计根节点每个子树中到根节点距离为signalSpeed倍数的节点数目，他们的组合就是经过该节点可连接的服务器对的数目
        // 如何组合，比如有三个子树，每个子树有满足条件的节点数为 2 3 4，那么组合起来就总共有 2 * 3 + 2 * 4 + 3 * 4 种 --> (2 * 3) + ((2 + 3) * 4)
        // 2 3 4 5，那么组合起来就总共有 2 * 3 + 2 * 4 + 2 * 5 + 3 * 4 + 3 * 5 + 4 * 5 种 --> (2 * 3) + ((2 + 3) * 4) + ((2 + 3 + 4) * 5)
        for(int i = 0; i < n; i++) {
            int pre = 0;
            int[] visited = new int[n];
            visited[i] = 1;
            for(int j = 0; j < graph[i].size(); j++) {
                visited[graph[i].get(j)[0]] = 1;
                int cnt = dfs(graph[i].get(j)[0], graph[i].get(j)[1], signalSpeed, visited);
                ret[i] += pre * cnt;
                pre += cnt;
            }
        }

        return ret;
    }

    private List<int[]>[] graph;
    private int dfs(int node, int w, int signalSpeed, int[] visited) {

        int nodeNum = 0;
        if(w % signalSpeed == 0) {
            nodeNum++;
        }
        for(int[] nextNode : graph[node]) {
            int n = nextNode[0];
            int nw = nextNode[1];
            if(visited[n] == 0) {
                visited[n] = 1;
                nodeNum += dfs(n, w + nw, signalSpeed, visited);
            }
        }

        return nodeNum;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,1},{1,2,5},{2,3,13},{3,4,9},{4,5,2}};
        int signalSpeed = 1;
        Solution solution = new Solution();
        int[] ret = solution.countPairsOfConnectableServers(edges, signalSpeed);
        for(int i : ret) {
            System.out.println(i);
        }
    }
}
