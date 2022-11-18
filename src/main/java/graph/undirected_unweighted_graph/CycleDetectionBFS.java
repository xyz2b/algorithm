package graph.undirected_unweighted_graph;


import java.util.ArrayDeque;
import java.util.Queue;

public class CycleDetectionBFS {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetectionBFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++) {
            if(!visited[v]) {
                if(bfs(v))
                    hasCycle = true;
                break;
            }
        }
    }

    // 从顶点v开始，判断图中是否有环
    private boolean bfs(int s) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {s, s});
        visited[s] = true;

        while (!queue.isEmpty()) {
            int[] pair = queue.remove();
            int v = pair[0];
            // parent是v的上一个节点
            int parent = pair[1];

            for(int w : G.adj(v)) {
                if(!visited[w]) {
                    queue.add(new int[] {w, v});
                    visited[w] = true;
                } else if(w != parent) {
                    // 如果v的下一个节点w既不是v的上一个节点，并且w已经是被访问过的，说明产生了环
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CycleDetectionBFS cycleDetectionBFS = new CycleDetectionBFS(graph);
        System.out.println(cycleDetectionBFS.hasCycle());

        Graph graph2 = new Graph("g2.txt");
        CycleDetectionBFS cycleDetectionBFS2 = new CycleDetectionBFS(graph2);
        System.out.println(cycleDetectionBFS2.hasCycle());
    }
}
