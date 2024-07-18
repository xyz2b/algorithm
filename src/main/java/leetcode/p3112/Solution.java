package leetcode.p3112;

import java.util.*;

public class Solution {


    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        // 构建图，取路径最小值
        HashMap<Integer, Integer>[] graph = new HashMap[n];
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int l = edge[2];

            if(u == v) {    // 自己到自己的过滤掉
                continue;
            }

            if(graph[u] == null) {
                graph[u] = new HashMap<>();
            }
            if(graph[v] == null) {
                graph[v] = new HashMap<>();
            }

            if(graph[u].get(v) != null) {
                if(graph[u].get(v) > l) {
                    graph[u].put(v, l);
                    graph[v].put(u, l);
                }
            } else {
                graph[u].put(v, l);
                graph[v].put(u, l);
            }
        }


        // 单源最短路径，dijkstra算法
        // 初始时，到源的最短路径都初始化为正无穷。源点到源点的最短路径是0，d[s] = 0，
        //  跟源点相连的点到源点的距离初始为两点之间的距离，d[a] = sa，d[b] = sb
        // 此时找到目前还没确定解的 源点到所有其他点之间距离最小的点，源点到它目前的距离就是最短路径。因为它已经是最小了，如果再绕到走其他点过来的距离肯定比现在的距离大。
        //  如果不是此时最小的距离则不是最短路径，有可能绕过最小距离的点再到该点比当前距离小
        // 之后判断从 源点 经过 已经确定和源点最短路径的点，到达其他的点，会不会比直接从源点到其他点的距离小
        // 继续以上过程

        // 每轮循环
        // 1.找到当前还没有求解出最短路径的节点中距离源点距离最小的那个节点
        // 2.确定这个节点的最短路径就是当前大小（前提：没有负权边）
        // 3.根据这个节点的最短路径大小，更新其他节点的路径长度

        // 若d[a]最小
        // 则d[a]就是从源点到a的最短路径
        // d[b] = min(d[b], d[a] + ab)  如果源点s从a再到b的距离，比本来源点s到b的距离短，就更新d[b]

        // 每次都要找最小的值，使用最小堆，也就是java中的优先队列默认形式
        int[] d = new int[n];
        // 已经确定最短路径的节点
        boolean[] visited = new boolean[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        int s = 0;
        d[0] = 0;

        Queue<int[]> queue = new PriorityQueue<>((int[] x, int[] y) -> x[1]- y[1]);
        queue.offer(new int[] {s, d[s]});
        while (!queue.isEmpty()) {
            // a目前到s距离最短的节点
            int[] node = queue.poll();
            int a = node[0];
            // 已经确定最短路径的顶点，直接丢掉
            if(visited[a]) {
                continue;
            }
            visited[a] = true;

            // 跟a相邻的节点b
            if(graph[a] != null) {
                for(Map.Entry<Integer, Integer> entry : graph[a].entrySet()) {
                    int b = entry.getKey();
                    int ab = entry.getValue();
                    if(!visited[b] && d[a] + ab < d[b] && // 如果b没有确认答案，并且s经过a到b，比原本s到b要短，就需要更新b的最短路径
                            d[a] + ab < disappear[b]) {
                            // 在将当前节点的邻接点 b 放入堆时，还需要判断新的路径长度是否小于 disappear[b]，如果不满足，则不入堆。
                            // 因为如果s经过a到b之后的距离 大于等于 disappear[b]，则表示s经过a到达b之后，b节点已经不存在了，也就表示s没办法经过a到达b了，所以不入堆。
                        d[b] = d[a] + ab;
                        queue.offer(new int[] {b, d[b]});
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            if(d[i] == Integer.MAX_VALUE) {
                d[i] = -1;
            }
        }

        return d;
    }

    public static void main(String[] args) {
        int n = 10;
        int[][] edges = {{5,4,3},{7,4,8},{2,0,8},{0,5,3},{4,0,8},{0,0,1},{6,6,4}};
        int[] disappear = {13,15,10,19,11,14,17,8,13,17};
        Solution solution = new Solution();
        solution.minimumTime(n, edges, disappear);
    }
}
