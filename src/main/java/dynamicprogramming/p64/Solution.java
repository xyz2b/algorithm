package dynamicprogramming.p64;

public class Solution {
    private int[][] grid;
    private int R;
    private int C;
    // 因为只会往下或者右，不会往回走，所以不需要记录已经走过的格子
    private int[][] memo;    // 记忆
    private int[][] dirs = {{0, 1}, {1, 0}};

    public int minPathSum(int[][] grid) {
        this.grid = grid;
        this.R = grid.length;
        this.C = grid[0].length;
        memo = new int[R][C];

        return dfs(0, 0);
    }

    // 记忆化搜索 - 自上而下
    private int dfs(int x, int y) {
        // 到达终点
        if(x == R - 1 && y == C - 1) {
            memo[x][y] = grid[x][y];
            return grid[x][y];
        }

        if(memo[x][y] != 0) {
            return memo[x][y];
        }

        int min = Integer.MAX_VALUE;
        for(int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];

            if(inArea(nextx, nexty)) {
                int sum = dfs(nextx, nexty);
                if(sum < min) {
                    min = sum;
                }
            }
        }

        int sum = min + grid[x][y];
        memo[x][y] = sum;
        return sum;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >=0 && y < C;
    }

    // 动态规划 - 自下而上
    public int minPathSum2(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int[][] memo = new int[R][C];
        int[][] dirs = {{0, -1}, {-1, 0}};

        memo[0][0] = grid[0][0];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(i == 0 && j == 0) continue;

                int min = Integer.MAX_VALUE;
                for(int d = 0; d < dirs.length; d++) {
                    int nextx = i + dirs[d][0];
                    int nexty = j + dirs[d][1];
                    if (inArea(nextx, nexty, R, C)) {
                        int sum = memo[nextx][nexty];
                        if(sum < min) {
                            min = sum;
                        }
                    }
                }
                memo[i][j] = min + grid[i][j];
            }
        }
        return memo[R-1][C-1];
    }

    private boolean inArea(int x, int y, int R, int C) {
        return x >= 0 && x < R && y >=0 && y < C;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        Solution solution = new Solution();
        System.out.println(solution.minPathSum2(grid));
    }
}
