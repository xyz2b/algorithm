package graph.directed_unweighted_graph;

import java.util.ArrayList;
import java.util.Arrays;

// 同无向图
// 计算从某个节点开始所能到达的节点数目
public class CCDFS {
    private Graph G;
    private int[] visited;
    private ArrayList<Integer> ccVCount = new ArrayList<>();

    public CCDFS(Graph G) {
        this.G = G;
        visited = new int[G.V()];

        for(int v = 0; v < G.V(); v++) {
            // -1表示还未遍历过
            Arrays.fill(visited, -1);
            int vCount = dfs(v);
            // 每次进入该逻辑都是一个新的联通分量
            ccVCount.add(vCount);
        }
    }

    // v顶点所在联通分量的顶点数
    private int dfs(int v) {
        visited[v] = 1;
        int vCount = 1;
        for(int w : G.adj(v)) {
            if(visited[w] == -1) {
                vCount += dfs(w);
            }
        }
        return vCount;
    }

    public Iterable<Integer> ccVCount() {
        return ccVCount;
    }
}
