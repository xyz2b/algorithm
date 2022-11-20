package graph.undirected_unweighted_graph;

import java.util.ArrayList;
import java.util.Stack;

// // 类比树的深度优先遍历，只是多加了visited信息
/**
 * dfs和bfs非递归写法只有所用的容器不同，其他完全一样
 * dfs用的是栈，bfs用的是队列
 * 即放入和取出元素的顺序不同
 * 还可以把容器换成随机队列，那结果就变成了随机遍历
 * */
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
