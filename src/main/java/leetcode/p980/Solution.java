package leetcode.p980;


// HamiltonPath
public class Solution {
    private int[][] grid;
    private int R, C;

    private boolean[][] visited;
    private int start, end;

    private int[][] dirs = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        // 还有多少未访问的顶点，初始化为R*C
        int left = R * C;

        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(grid[i][j] == 1) {
                    start = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == 2) {
                    end = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    // 为-1的顶点为障碍不可访问
                    left--;
                }
            }
        }

        return dfs(start, left);
    }

    private int dfs(int v, int left) {
        int x = v / C, y = v % C;
        visited[x][y] = true;
        left--;
        if(left == 0 && v == end) {
            visited[x][y] = false;
            return 1;
        }

        int res = 0;
        for(int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];

            if(inArea(nextx, nexty) && grid[nextx][nexty] == 0 && !visited[nextx][nexty]) {
                int nextv = nextx * C + nexty;
                res += dfs(nextv, left);
            }
        }

        visited[x][y] = false;
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
