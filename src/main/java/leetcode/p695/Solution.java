package leetcode.p695;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * 二维->一维
 * (x,y) -> x*C+y
 *
 * 一维->二维
 * v -> x = v/C
 *      y = v%C
 * C为二维的列数 data[0].length
 *
 * 四联通
 * dirs=[[-1,0],[1,0],[0,-1],[0,1]]
 *
 * for(int d=0; d<4;d++){
 *     nextx=x+dirs[d][0];
 *     nexty=y+dirs[d][1];
 * }
 *
 * 八联通
 * dirs=[[-1,0],[1,0],[0,-1],[0,1],[-1,1],[-1,-1],[1,-1],[1,1]]
 *
 * for(int d=0; d<8;d++){
 *     nextx=x+dirs[d][0];
 *     nexty=y+dirs[d][1];
 * }
 * */
public class Solution {
    // 邻接矩阵表示法的图
    // G.length就是图的顶点数V
    private HashSet<Integer>[] G;
    private boolean[] visited;

    private int[][] grid;
    // gird行数
    private int R;
    // grid列数
    private int C;

    // 四联通
    private int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null) return 0;
        // 行数
        R = grid.length;
        if(R == 0) return 0;
        // 列数
        C = grid[0].length;
        if(C == 0) return 0;
        this.grid = grid;

        G = constructGraph();

        int rst = 0;
        // G.length就是图的顶点数V
        visited = new boolean[G.length];
        for(int v = 0; v < G.length; v++) {
            int x = v / C, y = v % C;

            if(!visited[v] && grid[x][y] == 1) {
                rst = Math.max(dfs(v), rst);
            }
        }

        return rst;
    }

    // v顶点所在联通分量的顶点数
    private int dfs(int v) {
        visited[v] = true;
        int vCount = 1;
        for(int w : G[v]) {
            if(!visited[w]) {
                vCount += dfs(w);
            }
        }
        return vCount;
    }

    private HashSet<Integer>[] constructGraph() {
        HashSet<Integer>[] g = new HashSet[R*C];
        for(int i = 0; i < g.length; i++) {
            g[i] = new HashSet<>();
        }

        for(int v = 0; v < g.length; v++) {
            int x = v / C, y = v % C;

            if(grid[x][y] == 1) {
                for(int d = 0; d < 4; d++) {
                    int nextx = x + dirs[d][0];
                    int nexty = y + dirs[d][1];

                    // 需要处理越界情况
                    if(inArea(nextx, nexty) && grid[nextx][nexty] == 1) {
                        int nextv = nextx * C + nexty;
                        g[v].add(nextv);
                        g[nextv].add(v);
                    }
                }
            }
        }

        return g;
    }

    // 判断[x, y]是否越界
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {

    }
}