package graph.undirected_unweighted_graph;

import java.util.ArrayList;
import java.util.List;

public class FindCutPoints {
    private Graph G;
    private boolean[] visited;

    // order[v]表示顶点v在DFS的访问顺序
    // low[v]表示DFS过程中，顶点v能到达的最小order值
    private int[] order;
    private int[] low;
    // 节点的oder值 == 当前遍历的节点数
    private int cnt;

    private ArrayList<Integer> res;

    public FindCutPoints(Graph G) {
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

        int child = 0;
        for(int w : G.adj(v)) {
            if(!visited[w]) {
                dfs(w, v);

                // 经过递归之后，可能v的子节点w的节点的low值要小于当前节点的low值，说明通过v的子节点可以到达比v所能到达最低order值的节点还要低，因为v和w相连接，说明v也可以到达这个更低的order值的节点
                if(low[w] < low[v]) {
                    low[v] = low[w];
                }

                // 如果v有一个孩子节点w，满足low[w] >= order[v]，则v是割点
                // 特殊情况：根节点 (v节点如果有父亲的话，它一定不是根节点)
                if(v != parent && low[w] >= order[v]) {
                    res.add(v);
                }

                child++;
                if (v == parent && child > 1) {
                    // v是根节点，并且v的DFS遍历树中孩子节点数量大于1(因为是要在v的DFS遍历树中，所以v不能是已访问过的节点)，去掉根节点联通分量数量就会变化，即表示根节点也是割点
                    res.add(v);
                }
            } else if (w != parent) { // visited[w]，说明有环了，所以v-w边不可能是桥
                if(low[w] < low[v])
                    // w已访问过，并且w不是v的父亲节点，并且w的low值小于v的low值，此时更新v的low值为w的low值，说明v能通过w到达比当前所能到达的order值更小的节点
                    low[v] = low[w];
            }
        }
    }

    public Iterable<Integer> bridges() {
        return res;
    }
}
