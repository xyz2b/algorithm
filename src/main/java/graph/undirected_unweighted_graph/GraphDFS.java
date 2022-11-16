package graph.undirected_unweighted_graph;

import java.util.ArrayList;

public class GraphDFS {
    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++) {
            if(!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);
        for(int w : G.adj(v)) {
            if(!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public ArrayList<Integer> pre() {
        return pre;
    }

    public ArrayList<Integer> post() {
        return post;
    }
}
