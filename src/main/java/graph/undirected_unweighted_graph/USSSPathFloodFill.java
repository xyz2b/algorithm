package graph.undirected_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

// BFS求解的单源路径，就是从s到t的最短路径，这是广度优先遍历的特性
// Unweighted Single Source Shortest Path
public class USSSPathFloodFill {
    private int[][] gird;
    private int R;
    private int C;

    private boolean[][] visited;
    private int[][] distance;
    private int[][][] pre;
    private int sx;
    private int sy;
    private int[][] dirs = {{1,1},{0,1},{1,0},{-1,0},{0,-1},{-1,1},{1,-1},{-1,-1}};

    public USSSPathFloodFill(int[][] grid, int sx, int sy) {
        R = grid.length;
        C = grid[0].length;
        this.gird = grid;
        this.sx = sx;
        this.sy = sy;

        this.visited = new boolean[R][C];
        this.distance = new int[R][C];
        this.pre = new int[R][C][2];
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                distance[i][j] = -1;
                pre[i][j] = null;
            }
        }

        // gird[x][y]==0：路径途经的所有单元格都的值都是0
        if(gird[sx][sy] == 0) {
            bfs(sx, sy);
        }
    }

    private void bfs(int sx, int sy) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {sx, sy});
        visited[sx][sy] = true;
        distance[sx][sy] = 0;
        pre[sx][sy] = new int[] {sx, sy};

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int x = pair[0];
            int y = pair[1];

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

    public boolean isConnectedTo(int tx, int ty) {
        if(inArea(tx, ty)) {
            return visited[tx][ty];
        }
        return false;
    }

    public Iterable<int[]> path(int tx, int ty) {
        ArrayList<int[]> path = new ArrayList<>();
        if(!isConnectedTo(tx, ty)) return path;

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

    public int distance(int tx, int ty) {
        if(inArea(tx, ty)) {
            return distance[tx][ty];
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[][] grid = {{0,1},{1,0}};
        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
//        int[][] grid = {{1,0,0},{1,1,0},{1,1,0}};
        USSSPathFloodFill usssPathFloodFill = new USSSPathFloodFill(grid, 0,0);
        System.out.println(usssPathFloodFill.distance(2, 2));
        for(int[] pair: usssPathFloodFill.path(2, 2)) {
            int x = pair[0];
            int y = pair[1];
            System.out.println("x: " + x + ", y: " + y);
        }

    }
}
