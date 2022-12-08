package graph.undirected_weighted_graph;

import java.util.ArrayList;

/**
 * 最小生成树
 * Prim算法
 * 随便找一个切分，一旦找到了一个切分，那么至少知道了最小生成树中的一条边，即横切边中的最短边
 * 操作切分，从1:V-1开始，每次找到当前切分的最短横切边，扩展切分，直到没有切分
 * 从 1 个顶点开始进行切分(1:V-1)，然后将最短横切边的另一个顶点纳入到 切分的一边(2:V-2)，继续找当前新的切分的横切边的最短边
 * */
public class Prim {
    private WeightedGraph G;
    private ArrayList<WeightEdge> mst;

    public Prim(WeightedGraph G) {
        this.G = G;

        mst = new ArrayList<>();

        CCBFS cc = new CCBFS(G);
        // 联通分量的个数大于1，说明这个图不是联通图
        if(cc.count() > 1) return;

        // Prim
        // visited来表示切分，true为一边，false为另一边
        boolean[] visited = new boolean[G.V()];
        // 从 1:V-1 开始，起始0顶点和其他顶点形成一个切分的两边
        visited[0] = true;
        // 最短生成树 有 V-1 个边
        for(int i = 1; i < G.V(); i++) {
            // 找切分的最短横切边，需要遍历每个为true的顶点，然后找到其邻接为false的顶点，这两点形成的边就是横切边，然后找到最小的即可
            WeightEdge minEdge = new WeightEdge(-1, -1, Integer.MAX_VALUE);
            for(int v = 0; v < G.V(); v++) {
                if(visited[v]) {
                    for(int w : G.adj(v)) {
                        if(!visited[w] && G.getWeight(v, w) < minEdge.getWeight()) {
                            minEdge = new WeightEdge(v, w, G.getWeight(v, w));
                        }
                    }
                }
            }
            mst.add(minEdge);
            // 扩充切分
            visited[minEdge.getV()] = true;
            visited[minEdge.getW()] = true;
        }

    }

    public Iterable<WeightEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("gw.txt");
        Prim prim = new Prim(g);
        System.out.println(prim.result());
    }
}
