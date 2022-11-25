package graph.undirected_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

// BFS求解的单源路径，就是从s到t的最短路径，这是广度优先遍历的特性
public class UnWeightShortPathFloodFill {
    private int[][] gird;
    private int R;
    private int C;

    private boolean[][] visited;
    private int[][] distance;
    private int[][][] pre;
    private int sx;
    private int sy;
    private int tx;
    private int ty;

    private int[][] dirs = {{1,1},{0,1},{1,0},{-1,0},{0,-1},{-1,1},{1,-1},{-1,-1}};

    public UnWeightShortPathFloodFill(int[][] grid, int sx, int sy, int tx, int ty) {
        R = grid.length;
        C = grid[0].length;
        this.gird = grid;
        this.sx = sx;
        this.sy = sy;
        this.tx = tx;
        this.ty = ty;

        this.visited = new boolean[R][C];
        this.distance = new int[R][C];
//        this.pre = new int[R*C];
        this.pre = new int[R][C][2];
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                distance[i][j] = -1;
                pre[i][j] = null;
            }
        }
//        for(int i = 0; i < pre.length; i++) {
//            pre[i] = -1;
//        }

        // gird[x][y]==0：路径途经的所有单元格都的值都是0
        if(gird[sx][sy] == 0) {
            bfs(sx, sy, tx, ty);
        }
    }

//    private void bfs2(int sx, int sy, int tx, int ty) {
//        Queue<Integer> queue = new ArrayDeque<>();
//        int s = sx * C + sy;
//        queue.add(s);
//        visited[sx][sy] = true;
//        distance[sx][sy] = 0;
//        pre[s] = s;
//
//        while (!queue.isEmpty()) {
//            int v = queue.poll();
//            int vx = v / C, vy = v % C;
//            for(int d = 0; d < 8; d++) {
//                int nextx = vx + dirs[d][0];
//                int nexty = vy + dirs[d][1];
//
//                if(inArea(nextx, nexty) && !visited[nextx][nexty] && gird[nextx][nexty] == 0) {
//                    int w = nextx * C + nexty;
//                    queue.add(w);
//                    visited[nextx][nexty] = true;
//                    pre[w] = v;
//                    distance[nextx][nexty] = distance[vx][vy] + 1;
//
//                    if (nextx == tx && nexty == ty) {
//                        break;
//                    }
//                }
//            }
//        }
//    }

    private void bfs(int sx, int sy, int tx, int ty) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {sx, sy});
        visited[sx][sy] = true;
        distance[sx][sy] = 0;
        pre[sx][sy] = new int[] {sx, sy};

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int x = pair[0];
            int y = pair[1];

            if(x == tx && y == ty) {
                break;
            }

            for(int d = 0; d < 8; d++) {
                int nextx = x + dirs[d][0];
                int nexty = y + dirs[d][1];

                if(inArea(nextx, nexty) && !visited[nextx][nexty] && gird[nextx][nexty] == 0) {
                    queue.add(new int[] {nextx, nexty});
                    visited[nextx][nexty] = true;
                    pre[nextx][nexty] = new int[] {x, y};
                    distance[nextx][nexty] = distance[x][y] + 1;
                }
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >=0 && y < C;
    }

    public boolean isConnectedTo() {
        return visited[tx][ty];
    }

    public Iterable<int[]> path() {
        ArrayList<int[]> path = new ArrayList<>();
        if(!isConnectedTo()) return path;

        int[] cur = new int[]{tx, ty};
        while (cur[0] != sx && cur[1] != sy) {
            path.add(cur);
            cur = pre[cur[0]][cur[1]];
        }
        path.add(new int[] {cur[0], cur[1]});
        path.add(new int[] {sx, sy});

        Collections.reverse(path);
        return path;
    }

    public int distance() {
        return distance[tx][ty];
    }

    public static void main(String[] args) {
//        int[][] grid = {{0,1},{1,0}};
        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
//        int[][] grid = {{1,0,0},{1,1,0},{1,1,0}};
        UnWeightShortPathFloodFill usssPathFloodFill = new UnWeightShortPathFloodFill(grid, 0,0 , 2, 2);
        System.out.println(usssPathFloodFill.distance());
        for(int[] pair: usssPathFloodFill.path()) {
            int x = pair[0];
            int y = pair[1];
            System.out.println("x: " + x + ", y: " + y);
        }

    }
}
