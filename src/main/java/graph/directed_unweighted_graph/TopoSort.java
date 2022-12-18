package graph.directed_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class TopoSort {
    private Graph G;
    private ArrayList<Integer> rst;
    private boolean hasCycle;

    public TopoSort(Graph G) {
        this.G = G;

        int[] inDegrees = new int[G.V()];
        Queue<Integer> queue = new ArrayDeque<>();
        for(int v = 0; v < G.V(); v++) {
            inDegrees[v] = G.inDegree(v);
            // 将入度为0的顶点加入队列
            if(inDegrees[v] == 0) queue.add(v);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for(int w : G.adj(cur)) {
                inDegrees[w]--;
                if(inDegrees[w] == 0) queue.add(w);
            }
        }

        if(rst.size() != G.V()) hasCycle = true;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public ArrayList<Integer> result() {
        return rst;
    }
}
