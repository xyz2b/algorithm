package graph.undirected_unweighted_graph;

// 所有点对路径问题，对应英文 All Pairs (Vertices) Path (Problem)
public class AllPairsPath {
    private Graph G;
    // 我们定义一个 SingleSourcePath 的数组，存储每一个顶点对应的单源路径问题的解
    private SingleSourcePath[] paths;

    public AllPairsPath(Graph G) {
        this.G = G;

        paths = new SingleSourcePath[G.V()];
        for(int v = 0; v < G.V(); v++) {
            paths[v] = new SingleSourcePath(G, v);
        }
    }

    // 看从顶点 s 到顶点 t 是否有路径？
    public boolean isConnectedTo(int s, int t){
        G.validateVertex(s);
        return paths[s].isConnectedTo(t);
    }

    // 求从顶点 s 到顶点 t 的路径
    public Iterable<Integer> path(int s, int t) {
        G.validateVertex(s);
        return paths[s].path(t);
    }
}
