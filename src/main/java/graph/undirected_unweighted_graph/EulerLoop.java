package graph.undirected_unweighted_graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * 欧拉回路
 * 从一个点出发，沿着边行走，经过每个边恰好一次，之后再回到出发点
 *
 * 欧拉回路存在的性质
 * 对于无向联通图
 * 每个点的度是偶数 <—> 图存在欧拉回路
 *
 * 欧拉路径
 * 从一个点出发，沿着边行走，经过每个边恰好一次，之后来到结束点
 * 结束点和起始点可以不一样
 *
 * 欧拉路径存在的性质
 * 对于无向联通图
 * 除了两个点之外每个点的度是偶数 <—> 图存在欧拉回路
 * 一个点是起始点，只需要有条路出去即可
 * 另一个点是终止点，只需要有条路进来即可
 *
 * 求欧拉路径也可以使用Hierholzer算法
 * 只不过起始点和终止点不能随意选择，需要选择两个度为奇数的点作为起始点和终止点，其他点的度都是偶数
 *
 * */
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

    // 回溯法
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

    // Hierholzer算法
    public Iterable<Integer> path2() {
        ArrayList<Integer> path = new ArrayList<>();
        if(!hasEulerLoop()) return path;

        Graph g = (Graph) G.clone();

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            // 始终关注栈顶的点
            int curv = stack.peek();
            if(g.degree(curv) != 0) {   // 从 curv 点出发还有边没有走，就随便选一个边走，然后将这个边从图中删掉，将边的另一个点压入栈中
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                stack.push(w);
            } else {    // 从 curv 点出发所有边都走过了，那么就回退到上一个节点
                path.add(stack.pop());
            }
        }
        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g6.txt");
        EulerLoop el = new EulerLoop(g);
//        for(int[] p :el.path()) {
//            System.out.println("(" + p[0] + ", " + p[1] + ")");;
//        }
        System.out.println(el.path2());

        Graph g2 = new Graph("g7.txt");
        EulerLoop el2 = new EulerLoop(g2);
        System.out.println(el2.path2());
    }
}
