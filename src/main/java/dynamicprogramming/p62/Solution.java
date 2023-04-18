package dynamicprogramming.p62;

import java.util.Arrays;

public class Solution {

    private int[][] dirs = {{1,0},{0,1}};
    private int R;
    private int C;

    // 暴力搜索 - 自上而下
    public int uniquePaths(int m, int n) {
        R = m;
        C = n;

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

            if(inArea(nextx, nexty)) {
                rst += dfs(nextx, nexty);
            }
        }
        return rst;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    // (x,y)点到(m-1, n-1)的路径有几条，至少会有1条，所以memo就初始化为0即可
    private int[][] memo;
    // 记忆化搜索 - 自上而下
    public int uniquePaths2(int m, int n) {
        memo = new int[m][n];

        R = m;
        C = n;

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

            if(inArea(nextx, nexty)) {
                rst += dfs2(nextx, nexty);
            }
        }

        memo[x][y] = rst;
        return rst;
    }

    // 动态规划 - 自下而上
    public int uniquePaths3(int m, int n) {
        R = m;
        C = n;
        // (x,y)点到(m-1, n-1)的路径有几条，至少会有1条，所以memo就初始化为0即可
        int[][] memo = new int[m][n];
        int[][] dirs = {{1,0},{0,1}};

        memo[m - 1][n - 1] = 1;

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(i == m - 1 && j == n - 1) {
                    continue;
                }
                for(int d = 0; d < dirs.length; d++) {
                    int nextx = i + dirs[d][0];
                    int nexty = j + dirs[d][1];
                    if (inArea(nextx, nexty)) {
                        memo[i][j] += memo[nextx][nexty];
                    }
                }
            }
        }
        return memo[0][0];
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths3(m, n));
    }
}
