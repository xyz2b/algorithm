package graph.undirected_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

// BFS求解的单源路径，就是从s到t的最短路径，这是广度优先遍历的特性
// Unweighted Single Source Shortest Path
public class USSSPath {
    private Graph G;
    private boolean[] visited;
    // 路径的源头
    private int s;
    // 当前节点的上一个节点
    private int[] pre;
    // 记录当前节点距离源点的距离
    private int[] distances;

    public USSSPath(Graph G, int s) {
        G.validateVertex(s);

        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        distances = new int[G.V()];
        for(int i = 0; i < pre.length; i++) {
            pre[i] = -1;
            distances[i] = -1;
        }
        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        distances[s] = 0;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for(int w : G.adj(v)) {
                if(!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    distances[w] = distances[v] + 1;
                }
            }
        }
    }

    // t是否和s相连
    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    // s到t的最短路径
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

    // 从节点s到节点t的最短距离
    public int distance(int t) {
        G.validateVertex(t);
        return distances[t];
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        USSSPath path = new USSSPath(graph, 0);
        System.out.println(path.path(5));

    }
}
