package graph.undirected_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// 联通分量，即一个图中不连通的子图的个数（联通：可以从一个顶点经过若干条边到达另一个顶点）
public class CCBFS {
    private Graph G;
    private int[] visited;
    // 联通分量的个数
    private int ccCount = 0;

    public CCBFS(Graph G) {
        this.G = G;
        visited = new int[G.V()];
        // -1表示还未遍历过，相同数值的顶点之间是存在连接的，不同数值的顶点之间不存在连接
        Arrays.fill(visited, -1);

        for(int v = 0; v < G.V(); v++) {
            if(visited[v] == -1) {
                bfs(v, ccCount);
                ccCount++;
            }
        }
    }

    private void bfs(int s, int ccid) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        visited[s] = ccid;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for(int w : G.adj(v)) {
                if(visited[w] == -1) {
                    queue.add(w);
                    visited[w] = ccid;
                }
            }
        }
    }

    // 联通分量的个数
    public int count() {
        return ccCount;
    }

    // 判断两个顶点是否联通
    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    // 返回各个联通分量的顶点集合
    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for(int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }

        for(int v = 0; v < visited.length; v++) {
            res[visited[v]].add(v);
        }

        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CCBFS ccbfs = new CCBFS(graph);
        for(ArrayList<Integer> cc : ccbfs.components()) {
            System.out.println(cc);
        }
        System.out.println("==========================");
        Graph graph2 = new Graph("g2.txt");
        CCBFS ccbfs2 = new CCBFS(graph2);
        for(ArrayList<Integer> cc : ccbfs2.components()) {
            System.out.println(cc);
        }
    }
}
