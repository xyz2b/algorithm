package graph.undirected_weighted_graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 最小生成树
 * Kruskal算法
 * 每次找图中剩下的边中最小的边，判断如果加入了这条边，与之前加入的边会不会形成环
 * 根据切分定理，横切边中的最短边，属于最小生成树，Kruskal算法每次就找最小的边，去判断它是否形成环，如果没有形成环，那它就是横切边，则就属于生成树
 * */
public class Kruskal {
    private WeightedGraph G;
    private ArrayList<WeightEdge> mst;

    public Kruskal(WeightedGraph G) {
        this.G = G;
        mst = new ArrayList<>();

        CCBFS cc = new CCBFS(G);
        // 联通分量的个数大于1，说明这个图不是联通图
        if(cc.count() > 1) return;

        // Kruskal
        // 因为每次要找最小的边，所以需要对图的边进行排序
        // 先将所有边加入列表
        ArrayList<WeightEdge> edges = new ArrayList<>();
        for(int v = 0; v < G.V(); v++) {
            for(int w : G.adj(v)) {
                // 因为无向图，一条边会有两个表示，v-w，w-v，它俩是一样的，所以对边进行排序时候需要进行去除，只选取v-w的边
                if(v < w) {
                    edges.add(new WeightEdge(v, w, G.getWeight(v, w)));
                }
            }
        }
        Collections.sort(edges);

        // 动态快速判断新的边是否和现有的边形成环，使用并查集
        // 即判断新边的两个顶点，是否属于同一个集合，如果属于同一个集合，代表这条边会形成环
        UnionFind uf = new UnionFind(G.V());
        for(WeightEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();
            if(!uf.isConnect(v, w)) {
                mst.add(edge);
                uf.union(v, w);
            }
        }
    }

    public Iterable<WeightEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("gw.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
