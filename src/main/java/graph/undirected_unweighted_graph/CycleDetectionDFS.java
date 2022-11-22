package graph.undirected_unweighted_graph;


public class CycleDetectionDFS {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetectionDFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
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
        for (int w : G.adj(v)) {
            if(!visited[w]) {
                if(dfs(w, v))
                    return true;
            } else if(w != parent){
                // 如果v的下一个节点w既不是v的上一个节点，并且w已经是被访问过的，说明产生了环
                return true;
            }
        }
        return false;
    }

    private boolean hasCycle() {
        return hasCycle;
    }
}
