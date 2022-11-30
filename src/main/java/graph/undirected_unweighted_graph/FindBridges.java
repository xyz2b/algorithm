package graph.undirected_unweighted_graph;

import java.util.ArrayList;

public class FindBridges {
    class Edge {
        private int v, w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return String.format("%d-%d", v, w);
        }
    }
    private Graph G;
    private boolean[] visited;

    // order[v]表示顶点v在DFS的访问顺序
    // low[v]表示DFS过程中，顶点v能到达的最小order值
    private int[] order;
    private int[] low;
    // 节点的oder值 == 当前遍历的节点数
    private int cnt;

    private ArrayList<Edge> res;

    public FindBridges(Graph G) {
        this.G = G;
        res = new ArrayList<>();
        visited = new boolean[G.V()];
        order = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;
        for(int v = 0; v < G.V(); v++) {
            if(!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        order[v] = cnt;
        low[v] = order[v];
        cnt++;
        for(int w : G.adj(v)) {
            if(!visited[w]) {
                dfs(w, v);

                // 经过递归之后，可能v的子节点w的节点的low值要小于当前节点的low值，说明通过v的子节点可以到达比v所能到达最低order值的节点还要低，因为v和w相连接，说明v也可以到达这个更低的order值的节点
                if(low[w] < low[v]) {
                    low[v] = low[w];
                }

                // 考察 v-w 的边，通过v的子节点w，可以到达比v的order值还小的节点，说明v-w这条边不是桥
                // 如果 通过v的子节点w，不能到达比v的order值还小的节点，即通过v的子节点w到达不了v的祖先节点，说明v-w这个边就是桥
                if(low[w] > order[v]) { // v-w边是桥
                    res.add(new Edge(v, w));
                }
            } else if (w != parent) { // visited[w]，说明有环了，所以v-w边不可能是桥
                if(low[w] < low[v])
                    // w已访问过，并且w不是v的父亲节点，并且w的low值小于v的low值，此时更新v的low值为w的low值，说明v能通过w到达比当前所能到达的order值更小的节点
                    low[v] = low[w];
            }
        }
    }

    public Iterable<Edge> bridges() {
        return res;
    }
}
