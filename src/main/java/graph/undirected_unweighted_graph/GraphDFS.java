package graph.undirected_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

// // 类比树的深度优先遍历，只是多加了visited信息
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

    private void dfsNonRecursive(int s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;

        while (!stack.isEmpty()) {
            int v = stack.pop();
            pre.add(v);

            for(int w : G.adj(v)) {
                if(!visited[w]) {
                    stack.push(w);
                    visited[w] = true;
                }
            }
        }
    }

    public ArrayList<Integer> pre() {
        return pre;
    }

    public ArrayList<Integer> post() {
        return post;
    }
}
