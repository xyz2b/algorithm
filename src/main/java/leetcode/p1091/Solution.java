package leetcode.p1091;

import java.util.ArrayDeque;
import java.util.Queue;

// bfs求从[0, 0]到[R-1,C-1]的最短路径
// 8联通
public class Solution {
    private int[][] gird;
    private int R;
    private int C;

    private boolean[][] visited;
    private int[][] distance;

    private int[][] dirs = {{1,1},{0,1},{1,0},{-1,0},{0,-1},{-1,1},{1,-1},{-1,-1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null) return -1;
        R = grid.length;
        if(R == 0) return -1;
        C = grid[0].length;
        if(C == 0) return -1;
        this.gird = grid;

        this.visited = new boolean[R][C];
        this.distance = new int[R][C];
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                distance[i][j] = -1;
            }
        }

        // 从[0, 0]到[R-1,C-1]的最短路径
        int sx = 0, sy = 0, tx = R - 1, ty = C - 1;
        if(gird[sx][sy] == 0) {
            bfs(sx, sy, tx, ty);
        }

        return distance[tx][ty] == -1 ? distance[tx][ty] : distance[tx][ty] + 1;
    }

    private void bfs(int sx, int sy, int tx, int ty) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {sx, sy});
        visited[sx][sy] = true;
        distance[sx][sy] = 0;

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
                    distance[nextx][nexty] = distance[x][y] + 1;
                }
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >=0 && y < C;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1},{1,0}};
//        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
//        int[][] grid = {{1,0,0},{1,1,0},{1,1,0}};
        Solution solution = new Solution();
        System.out.println(solution.shortestPathBinaryMatrix(grid));
    }
}
