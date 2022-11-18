package graph.undirected_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

// 单源路径
public class SingleSourcePathBFS {
    private Graph G;
    private boolean[] visited;
    // 路径的源头
    private int s;
    // 当前节点的上一个节点
    private int[] pre;

    public SingleSourcePathBFS(Graph G, int s) {
        G.validateVertex(s);

        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for(int w : G.adj(v)) {
                if(!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
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

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        SingleSourcePathBFS path = new SingleSourcePathBFS(graph, 0);
        System.out.println(path.path(5));

    }
}
