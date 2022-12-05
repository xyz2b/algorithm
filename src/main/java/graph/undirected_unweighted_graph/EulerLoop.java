package graph.undirected_unweighted_graph;

import java.util.ArrayList;
import java.util.Collections;

public class EulerLoop {
    private Graph G;

    // 可以压缩状态，一条边使用两个顶点的组合数字来表示
    // 边是否访问
    private boolean[][] visited;
    // 上一条边
    private int[][][] pre;
    private int[] end;
    private int[] start;

    public EulerLoop(Graph G) {
        this.G = G;
        visited = new boolean[G.V()][G.V()];
        pre = new int[G.V()][G.V()][2];

        start = new int[] {0, G.adj(0).iterator().next()};
        // 起始遍历第一条边
        dfs(start[0], start[1], G.E(), start[0], start[1]);
    }

    // (x,y)为当前递归的边，left为剩下未访问的边数，parent为v的父亲节点
    private boolean dfs(int x, int y, int leftEdge, int parentX, int parentY) {
        visited[x][y] = true;
        visited[y][x] = true;
        leftEdge--;
        pre[x][y] = new int[] {parentX, parentY};
        if(y == 0 && leftEdge == 0) {
            end = new int[] {x, y};
            return true;
        }

        for(int w : G.adj(y)) {
            if(!visited[y][w] && !visited[w][y]) {
                if(dfs(y, w, leftEdge, x, y)) return true;
            }
        }

        visited[x][y] = false;
        visited[y][x] = false;
        return false;
    }

    public boolean hasEulerLoop() {
        CCDFS cc = new CCDFS(G);
        if(cc.count() > 1) return false;

        for(int v = 0; v < G.V(); v++) {
            if(G.degree(v) % 2 == 1) return false;
        }
        return true;
    }

    public Iterable<int[]> path() {
        ArrayList<int[]> path = new ArrayList<>();
        if(end == null) return path;

        int[] cur = end;
        while (cur[0] != start[0] || cur[1] != start[1]) {
            path.add(cur);
            cur = pre[cur[0]][cur[1]];
        }
        path.add(start);

        Collections.reverse(path);

        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g6.txt");
        EulerLoop el = new EulerLoop(g);
        for(int[] p :el.path()) {
            System.out.println("(" + p[0] + ", " + p[1] + ")");;
        }
    }
}
