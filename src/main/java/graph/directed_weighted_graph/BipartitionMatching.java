package graph.directed_weighted_graph;

public class BipartitionMatching {
    private Graph G;
    private int maxMatching;

    public BipartitionMatching(Graph G) {
        BipartitionDetection bd = new BipartitionDetection(G);
        if(!bd.isBipartition()) {
            throw new IllegalArgumentException("BipartitionMatching only works for bipartite graph");
        }
        this.G = G;

        // 构造出来的有向图多了两个顶点，源点和汇聚点，标号分别为V和V+1
        WeightedGraph network = new WeightedGraph(G.V() + 2, true);
        int[] colors = bd.colors();
        for(int v = 0; v < G.V(); v++) {
            // 添加 顶点 到 一边的顶点的 边
            if(colors[v] == 1) network.addEdge(G.V(), v, 1);
            // 添加 另一边的顶点 到 汇聚点的边
            else network.addEdge(v, G.V() + 1, 1);
            for(int w : G.adj(v)) {
                if (v < w) {    // G为无向图，这里是构造有向图，所以只希望遍历一次v-w边
                    if(colors[v] == 1)  network.addEdge(v, w, 1);
                    else network.addEdge(w, v, 1);  // v为0，w肯定为1
                }
            }
        }

        MaxFlow maxFlow = new MaxFlow(network, G.V(), G.V() + 1);
        maxMatching = maxFlow.result();
    }

    public int maxMatching() {
        return maxMatching;
    }

    public static void main(String[] args) {
        Graph g = new Graph("network.txt", false);
        BipartitionMatching bm = new BipartitionMatching(g);
        System.out.println(bm.maxMatching);
    }
}
