package graph.undirected_unweighted_graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class FloodFillBFS {
    private boolean[][] visited;

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

        int rst = 0;
        // G.length就是图的顶点数V
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                // 遍历图的时候，因为对不是陆地的顶点不感兴趣 ，所以顶点如果不是陆地直接忽略，不是陆地的顶点不进行dfs
                if(!visited[i][j] && grid[i][j] == 1) {
                    rst = Math.max(bfs(i, j), rst);
                }
            }
        }

        return rst;
    }

    // v顶点所在联通分量的顶点数
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

    // 判断[x, y]是否越界
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {

    }
}
