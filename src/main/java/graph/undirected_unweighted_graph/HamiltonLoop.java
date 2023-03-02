package graph.undirected_unweighted_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HamiltonLoop {
    private Graph G;
//    private boolean[] visited;
    // 状态压缩，使用int的一位标识某个顶点是否已经访问过
    private int visited;
    private int[] pre;
    // 哈密尔顿回路的最后一个顶点
    private int end;

    public HamiltonLoop(Graph G) {
        this.G = G;
        visited = 0;
        pre = new int[G.V()];
        Arrays.fill(pre, -1);
        end = -1;
        dfs(0, G.V(), 0);
    }

    // v为当前递归的顶点，left为剩下未访问的顶点数，返回值是是否存在哈密尔顿回路
    private boolean dfs(int v, int left, int parent) {
//        visited[Solution] = true;
        visited += (1 << v);
        pre[v] = parent;
        left--;
        if (left == 0 && G.hasEdge(v, 0)) { // 通过v可以回到了原点0，并且所有顶点都被访问过了，就找到了哈密尔顿回路
            end = v;
            return true;
        }

        for(int w : G.adj(v)) {
            if((visited & (1 << w)) == 0 /* !visited[w] */) {
                if(dfs(w, left, v)) return true;
            }
//            else if (w == 0 && left == 0) { // 通过v回到了原点0，并且所有顶点都被访问过了，就找到了哈密尔顿回路
//                end = Solution;
//                return true;
//            }
        }
        // 回溯
        // 从顶点v开始找不到哈密尔顿回路，那么就回退到v的父亲节点，即退出v的递归，返回上一层
        // 此时需要将顶点v置为未访问的
//        visited[Solution] = false;
        visited -= (1 << v);
        // 其实这里不需要left++，因为回退到递归上一层时候，上一层的left是不受下一层子递归的left影响的，因为值传递
//        left++;
        return false;
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> path = new ArrayList<>();
        if(end == -1) return path;

        int cur = end;
        while (cur != 0) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(0);

        Collections.reverse(path);

        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g3.txt");
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.path());

        Graph g2 = new Graph("g4.txt");
        HamiltonLoop hl2 = new HamiltonLoop(g2);
        System.out.println(hl2.path());
    }
}
