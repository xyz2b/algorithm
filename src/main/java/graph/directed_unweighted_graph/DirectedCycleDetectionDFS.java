package graph.directed_unweighted_graph;

// 不同于无向图
public class DirectedCycleDetectionDFS {
    private Graph G;
    private boolean[] visited;
    // 表示顶点是否在遍历的路径上
    private boolean[] onPath;
    private boolean hasCycle = false;

    public DirectedCycleDetectionDFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        onPath = new boolean[G.V()];
        // 每一个联通分量都要判断是否有环
        for(int v = 0; v < G.V(); v++) {
            if(!visited[v]) {
                if(dfs(v, v))
                    hasCycle = true;
                break;
            }
        }
    }

    // 从顶点v开始，判断图中是否有环
    private boolean dfs(int v, int parent) {
        // parent是v的上一个节点
        visited[v] = true;
        onPath[v] = true;
        for (int w : G.adj(v)) {
            if(!visited[w]) {
                if(dfs(w, v))
                    return true;
            } else if(onPath[w]){
                // 如果v的下一个节点w在遍历的路径上，并且w已经是被访问过的，说明产生了环
                return true;
            }
        }
        // 回退
        onPath[v] = false;
        return false;
    }

    private boolean hasCycle() {
        return hasCycle;
    }
}
