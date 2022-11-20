package graph.undirected_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// 类比树的广度优先遍历，只是多加了visited信息
/**
 * dfs和bfs非递归写法只有所用的容器不同，其他完全一样
 * dfs用的是栈，bfs用的是队列
 * 即放入和取出元素的顺序不同
 * 还可以把容器换成随机队列，那结果就变成了随机遍历
 * */
public class GraphBFS {
    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> order;

    public GraphBFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        order = new ArrayList<>(G.V());
        for(int v = 0; v < G.V(); v++) {
            if(!visited[v]) {
                bfs(v);
            }
        }
    }

    private void bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);

            for(int w : G.adj(v)) {
                if(!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        GraphBFS bfs = new GraphBFS(graph);
        System.out.println(bfs.order);
    }

}
