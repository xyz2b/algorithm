package graph.directed_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// 同无向图
// 计算从某个节点开始所能到达的节点数目
public class CCBFS {
    private Graph G;
    private int[] visited;
    private ArrayList<Integer> ccVCount = new ArrayList<>();

    public CCBFS(Graph G) {
        this.G = G;
        visited = new int[G.V()];


        for(int v = 0; v < G.V(); v++) {
            // -1表示还未遍历过
            Arrays.fill(visited, -1);
            int vCount = bfs(v);
            ccVCount.add(vCount);
        }
    }

    private int bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        visited[s] = 1;
        int vCount = 1;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for(int w : G.adj(v)) {
                if(visited[w] == -1) {
                    queue.add(w);
                    visited[w] = 1;
                    vCount++;
                }
            }
        }
        return vCount;
    }

    public Iterable<Integer> ccVCount() {
        return ccVCount;
    }
}
