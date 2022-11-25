package leetcode.p1020_graph;

import java.util.ArrayDeque;
import java.util.Queue;

// 飞地的顶点总数量，即不挨着边界的联通分量的顶点总数
public class SolutionBFS {
    private int[][] grid;
    private int R;
    private int C;

    private boolean[][] visited;
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int numEnclaves(int[][] grid) {
        if(grid == null) return 0;
        R = grid.length;
        if(R == 0) return 0;
        C = grid[0].length;
        if(C == 0) return 0;
        this.grid = grid;
        visited = new boolean[R][C];

        // 可能会挨着边界的顶点，分布于[x, 0]、[x, C-1]、[0, y]、[R-1, y]上
        // 可以先将这些边界的顶点的联通分量给遍历完，然后再去遍历非边界的顶点，剩下的联通分量就是飞地了
        for(int i = 0; i < R; i++) {
            if(!visited[i][0] && grid[i][0] == 1) {
                bfs(i, 0);
            }
            if(!visited[i][C-1] && grid[i][C-1] == 1) {
                bfs(i, C-1);
            }
        }
        for(int j = 1; j < C; j++) {
            if(!visited[0][j] && grid[0][j] == 1) {
                bfs(0, j);
            }
            if(!visited[R-1][j] && grid[R-1][j] == 1) {
                bfs(R-1, j);
            }
        }

        // 遍历非边界的顶点
        int vCount = 0;
        for(int i = 1; i < R-1; i++) {
            for (int j = 1; j < C-1; j++) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    vCount += bfs(i, j);
                }
            }
        }
        return vCount;
    }

    private int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;

        int vCount = 1;
        // 向该顶点的四个方向找寻
        // 这里其实相当于在遍历邻接顶点，只是这里还不能确定四周的点是否为邻接顶点，还需要判断其是否是邻接顶点(grid[x][y] == 1)
        while (!queue.isEmpty()) {
            int[] point = queue.remove();
            x = point[0];
            y = point[1];

            for(int d = 0; d < 4; d++) {
                int nextx = x + dirs[d][0];
                int nexty = y + dirs[d][1];
                if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == 1) {
                    queue.add(new int[] {nextx, nexty});
                    visited[nextx][nexty] = true;
                    vCount++;
                }
            }
        }

        return vCount;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] girds = {{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
        SolutionBFS solution = new SolutionBFS();
        System.out.println(solution.numEnclaves(girds));

    }
}
