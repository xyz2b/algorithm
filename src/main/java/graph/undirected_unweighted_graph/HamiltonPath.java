package graph.undirected_unweighted_graph;

import java.util.ArrayList;
import java.util.Collections;

public class HamiltonPath {
    private Graph G;
//    private boolean[] visited;
    // 状态压缩，使用int的一位标识某个顶点是否已经访问过
    private int visited;
    private int[] pre;
    // 哈密尔顿回路的最后一个顶点
    private int end;

    private int s;

    public HamiltonPath(Graph G, int s) {
        this.G = G;
        visited = 0;
        pre = new int[G.V()];
        end = -1;
        this.s = s;
        dfs(s, G.V(), s);
    }

    // v为当前递归的顶点，left为剩下未访问的顶点数，返回值是是否存在哈密尔顿路径
    private boolean dfs(int v, int left, int parent) {
//        visited[Solution] = true;
        visited += (1 << v);
        pre[v] = parent;
        left--;

        if (left == 0 /* && G.hasEdge(Solution, 0) */) { // 只需要保证所有顶点都被访问过了，不需要通过v再回到了原点s，就找到了哈密尔顿路径
            end = v;
            return true;
        }

        for(int w : G.adj(v)) {
            if((visited & (1 << w)) == 0 /* !visited[w] */) {
                if(dfs(w, left, v)) return true;
            }
//            else if (/* w == s && */ left == 0) { // 只需要保证所有顶点都被访问过了，不需要通过v再回到了原点s，就找到了哈密尔顿路径
//                end = Solution;
//                return true;
//            }
        }
        // 回溯
        // 从顶点v开始找不到哈密尔顿路径，那么就回退到v的父亲节点，即退出v的递归，返回上一层
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
        while (cur != s) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(s);

        Collections.reverse(path);

        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g5.txt");
        HamiltonPath hl = new HamiltonPath(g, 1);
        System.out.println(hl.path());;
    }
}
