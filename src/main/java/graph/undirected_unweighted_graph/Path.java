package graph.undirected_unweighted_graph;

import java.util.ArrayList;
import java.util.Collections;

// 求解固定s到固定t的路径，可以提前退出
public class Path {
    private Graph G;
    private boolean[] visited;
    // 路径的源头
    private int s;
    // 当前节点的上一个节点
    private int[] pre;
    // 路径的终点
    private int t;

    public Path(Graph G, int s, int t) {
        G.validateVertex(s);
        G.validateVertex(t);
        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(s, s);
    }

    // 从v开始遍历，看是否可以到达t
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        // 当前节点v就是t，说明到达t了，返回true
        if(v == t) return true;
        for(int w : G.adj(v)) {
            if(!visited[w]) {
                // 在遍历当前节点v之后的节点时候，可以到达t，返回true
                if(dfs(w, v))
                    return true;
            }
        }
        // 上面遍历完成也没有到达t，说明不可达
        return false;
    }

    // t是否和s相连
    public boolean isConnectedTo() {
        return visited[t];
    }

    // s到t的路径
    public Iterable<Integer> path() {
        ArrayList<Integer> path = new ArrayList<>();
        if(!isConnectedTo()) return path;

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
