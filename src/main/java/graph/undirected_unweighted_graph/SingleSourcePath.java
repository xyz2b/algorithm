package graph.undirected_unweighted_graph;

import java.util.ArrayList;
import java.util.Collections;

// 单源路径
public class SingleSourcePath {
    private Graph G;
    private boolean[] visited;
    // 路径的源头
    private int s;
    // 当前节点的上一个节点
    private int[] pre;

    public SingleSourcePath(Graph G, int s) {
        G.validateVertex(s);

        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(s, s);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        for(int w : G.adj(v)) {
            if(!visited[w]) {
                dfs(w, v);
            }
        }
    }

    // t是否和s相连
    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    // s到t的路径
    public Iterable<Integer> path(int t) {
        ArrayList<Integer> path = new ArrayList<>();
        if(!isConnectedTo(t)) return path;

        int cur = t;
        while (cur != s) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(s);

        Collections.reverse(path);
        return path;
    }


}
