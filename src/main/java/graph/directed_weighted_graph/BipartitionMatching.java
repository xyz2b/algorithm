package graph.directed_weighted_graph;

/**
 * 最大匹配和完全匹配
 * 前提：二分图
 *
 * 最大匹配
 * 二分图的两边顶点，一一对应的最大边数
 * （最大匹配不一定是完全匹配）
 *
 * 完全匹配
 * 二分图的两边顶点，都存在一一对应关系
 * 看最大匹配是否所有点都匹配（完全匹配一定是最大匹配）
 *
 * 使用最大流算法，解决匹配问题
 * 在二分图中加上两点，一个是源点 V，一个是汇聚点 V+1
 * 源点指向二分图的一边的点，权值为1
 * 二分图另一边的点指向汇聚点，权值为1
 * 二分图一边的点指向另一边的点，权值为1
 * 构成一个有向有权图，可以直接使用maxFlow算法进行计算最大流量，即最大匹配
 * */
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
