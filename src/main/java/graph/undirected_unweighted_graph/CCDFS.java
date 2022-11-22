package graph.undirected_unweighted_graph;

import java.util.ArrayList;
import java.util.Arrays;

// 联通分量，即一个图中不连通的子图的个数（联通：可以从一个顶点经过若干条边到达另一个顶点）
public class CCDFS {
    private Graph G;
    private int[] visited;
    // 联通分量的个数
    private int ccCount = 0;
    // 每个联通分量的顶点数
    private ArrayList<Integer> ccVCount = new ArrayList<>();

    public CCDFS(Graph G) {
        this.G = G;
        visited = new int[G.V()];
        // -1表示还未遍历过，相同数值的顶点之间是存在连接的，不同数值的顶点之间不存在连接
        Arrays.fill(visited, -1);

        for(int v = 0; v < G.V(); v++) {
            if(visited[v] == -1) {
                int vCount = dfs(v, ccCount);
                // 每次进入该逻辑都是一个新的联通分量
                ccVCount.add(vCount);
                ccCount++;
            }
        }
    }

    // v顶点所在联通分量的顶点数
    private int dfs(int v, int ccid) {
        visited[v] = ccid;
        int vCount = 1;
        for(int w : G.adj(v)) {
            if(visited[w] == -1) {
                vCount += dfs(w, ccid);
            }
        }
        return vCount;
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

    public Iterable<Integer> ccVCount() {
        return ccVCount;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CCDFS ccdfs = new CCDFS(graph);
        for(ArrayList<Integer> cc : ccdfs.components()) {
            System.out.println(cc);
        }
        for(int vCount : ccdfs.ccVCount()) {
            System.out.println(vCount);
        }
        System.out.println("==========================");
        Graph graph2 = new Graph("g2.txt");
        CCDFS ccdfs2 = new CCDFS(graph2);
        for(ArrayList<Integer> cc : ccdfs2.components()) {
            System.out.println(cc);
        }
        for(int vCount : ccdfs2.ccVCount()) {
            System.out.println(vCount);
        }
    }
}
