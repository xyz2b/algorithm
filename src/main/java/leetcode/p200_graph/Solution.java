package leetcode.p200_graph;

// 岛屿数量就是图的联通分量个数
public class Solution {
    private boolean[][] visited;
    private char[][] grid;
    private int R;
    private int C;
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        R = grid.length;
        if(R == 0) return 0;
        C = grid[0].length;
        if(C == 0) return 0;
        this.grid = grid;
        visited = new boolean[R][C];

        int ccCount = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    dfs(i ,j);
                    ccCount++;
                }
            }
        }

        return ccCount;
    }

    private void dfs(int x, int y) {
        visited[x][y] = true;

        for(int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == '1') {
                dfs(nextx, nexty);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
