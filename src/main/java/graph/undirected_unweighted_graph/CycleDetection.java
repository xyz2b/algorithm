package graph.undirected_unweighted_graph;


public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++) {
            if(!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {
        // parent是v的上一个节点
        visited[v] = true;
        for (int w : G.adj(v)) {
            if(!visited[w]) {
                dfs(w, v);
            } else if(w != parent){
                // 如果v的下一个节点w既不是v的上一个节点，并且w已经是被访问过的，说明产生了环
                hasCycle = true;
            }
        }
    }

    private boolean hasCycle() {
        return hasCycle;
    }
}
