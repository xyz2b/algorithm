package leetcode.p2065;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    private HashMap<Integer, Integer>[] adj;

    int ret = 0;
    int maxTime;
    int[] visited;
    int[] values;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        this.values = values;
        this.maxTime = maxTime;
        adj = new HashMap[values.length];

        for(int i = 0; i < values.length; i++) {
            adj[i] = new HashMap<>();
        }

        visited = new int[values.length];

        for(int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            int weight = edge[2];
            adj[n1].put(n2, weight);
            adj[n2].put(n1, weight);
        }



        visited[0] = 1;
        dfs(0, 0, values[0]);
        return ret;
    }

    private void dfs(int n, int time, int value) {
        // 如果回到了节点0，就是一条路径，对答案进行更新
        if(n == 0) {
            ret = Math.max(ret, value);
        }

        for(Map.Entry<Integer, Integer> entry : adj[n].entrySet()) {
            int nextN = entry.getKey();
            int t = entry.getValue();

            if(time + t <= maxTime) {
                if(visited[nextN] == 0) {
                    visited[nextN] = 1;
                    // 没有访问过的，value需要加上该节点的value，time加上对应的time
                    dfs(nextN, time + t, value + values[nextN]);
                    // 遍历下一条路径时，将上一条遍历路径的visited恢复
                    // 比如 0 和 1 3 相邻，第一次循环1，第二次循环3，循环1执行dfs的时候将visited[1]置位，当循环3的时候，需要将visited[1]置为取消，因为是一条新的路径了
                    visited[nextN] = 0;
                } else {
                    // 已经访问过的，value不需要加上该节点的value
                    // 但是time需要再加上
                    dfs(nextN, time + t, value);
                }
            }
        }
    }
}
