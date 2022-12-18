package graph.directed_unweighted_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 有向图的强连通分量
// 在一个强连通分量中，任何两点都可达
// 将所有的强连通分量看做一个点，得到的有向图一定是DAG(有向无环图)

// 如果强联通分量能够到一点
// 则翻转这张图后，进行后序遍历，这一点一定相较强联通分量中的点后出现
// 则翻转这张图后，进行后序遍历的逆，这一点一定相较强联通分量中的点前出现
public class SCC {
    private Graph G;
    private int[] visited;
    // 联通分量的个数
    private int sccCount = 0;

    public SCC(Graph G) {
        this.G = G;
        visited = new int[G.V()];
        // -1表示还未遍历过，相同数值的顶点之间是存在连接的，不同数值的顶点之间不存在连接
        Arrays.fill(visited, -1);

        // 获取图的逆
        Graph reverseGraph = G.reverseGraph();
        // 对图的逆进行后序遍历
        GraphDFS dfs = new GraphDFS(reverseGraph);
        ArrayList<Integer> order = new ArrayList<>(dfs.post());
        // 对图的逆的后序遍历求逆
        Collections.reverse(order);

        // 对图的逆的后序遍历的逆进行DFS，求解联通分量
        for(int v : order) {
            if(visited[v] == -1) {
                dfs(v, sccCount);
                sccCount++;
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
        return sccCount;
    }

    // 判断两个顶点是否联通
    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    // 返回各个联通分量的顶点集合
    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[sccCount];
        for(int i = 0; i < sccCount; i++) {
            res[i] = new ArrayList<>();
        }

        for(int v = 0; v < visited.length; v++) {
            res[visited[v]].add(v);
        }

        return res;
    }
}
