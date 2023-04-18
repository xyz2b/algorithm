package dynamicprogramming.p63;

public class Solution {
    private int[][] obstacleGrid;
    private int[][] dirs = {{1,0},{0,1}};
    private int R;
    private int C;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        this.obstacleGrid = obstacleGrid;
        R = obstacleGrid.length;
        C = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1) return 0;
        if(obstacleGrid[R - 1][C - 1] == 1) return 0;

        return dfs(0, 0);
    }

    // 返回(x,y)到(m-1,n-1)的路径数量
    private int dfs(int x, int y) {
        if(x == R - 1 && y == C - 1) {
            return 1;
        }

        int rst = 0;
        for(int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];

            if(inArea(nextx, nexty) && obstacleGrid[nextx][nexty] != 1) {
                rst += dfs(nextx, nexty);
            }
        }
        return rst;
    }

    // (x,y)点到(m-1, n-1)的路径有几条，至少会有1条，所以memo就初始化为0即可
    private int[][] memo;
    // 记忆化搜索 - 自上而下
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {

        this.obstacleGrid = obstacleGrid;
        R = obstacleGrid.length;
        C = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1) return 0;
        if(obstacleGrid[R - 1][C - 1] == 1) return 0;

        memo = new int[R][C];

        return dfs2(0, 0);
    }

    private int dfs2(int x, int y) {
        if(x == R - 1 && y == C - 1) {
            return 1;
        }

        if(memo[x][y] != 0) {
            return memo[x][y];
        }

        int rst = 0;
        for(int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];

            if(inArea(nextx, nexty) && obstacleGrid[nextx][nexty] != 1) {
                rst += dfs2(nextx, nexty);
            }
        }

        memo[x][y] = rst;
        return rst;
    }

    // 动态规划 - 自下而上
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        R = obstacleGrid.length;
        C = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1) return 0;
        if(obstacleGrid[R - 1][C - 1] == 1) return 0;

        // (x,y)点到(m-1, n-1)的路径有几条，至少会有1条，所以memo就初始化为0即可
        int[][] memo = new int[R][C];
        int[][] dirs = {{1,0},{0,1}};

        memo[R - 1][C - 1] = 1;

        for(int i = R - 1; i >= 0; i--) {
            for(int j = C - 1; j >= 0; j--) {
                if(i == R - 1 && j == C - 1) {
                    continue;
                }
                for(int d = 0; d < dirs.length; d++) {
                    int nextx = i + dirs[d][0];
                    int nexty = j + dirs[d][1];
                    if (inArea(nextx, nexty) && obstacleGrid[nextx][nexty] != 1) {
                        memo[i][j] += memo[nextx][nexty];
                    }
                }
            }
        }
        return memo[0][0];
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        Solution solution = new Solution();
        System.out.println(solution.uniquePathsWithObstacles2(obstacleGrid));
    }
}
