package leetcode.p1971;

import java.util.Arrays;
import java.util.TreeSet;

// 无向无权图，判断两个点是否相连
// 无平行边，无自环边
public class Solution {
    private boolean[] visited;
    private TreeSet<Integer>[] adj;
    private int V;

    private int s;
    private int t;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        this.V = n;
        this.s = source;
        this.t = destination;
        this.adj = new TreeSet[V];
        for(int i = 0; i < V; i++) {
            adj[i] = new TreeSet<>();
        }
        for(int[] pairs : edges) {
            int v = pairs[0];
            int w = pairs[1];
            adj[v].add(w);
            adj[w].add(v);
        }

        this.visited = new boolean[V];

        return bfs(s);
    }

    private boolean bfs(int v) {
        if(v == t) return true;
        visited[v] = true;

        for(int w : adj[v]) {
            if(!visited[w]) {
                if(bfs(w)) return true;
            }
        }

        return false;
    }
}
