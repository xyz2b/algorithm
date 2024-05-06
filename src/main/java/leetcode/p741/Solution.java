package leetcode.p741;

import java.util.HashSet;

public class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == -1) return 0;
        if(grid[n-1][n-1] == -1) return 0;

        int[][][] f = new int[2 * n - 1][n][n];
        for(int k = 0; k < 2 * n - 1; k++) {
            for(int x1 = 0; x1 < n; x1++) {
                for(int x2 = 0; x2 < n; x2++) {
                    f[k][x1][x2] = Integer.MIN_VALUE;
                }
            }
        }
        f[0][0][0] = grid[0][0];
        for(int k = 1;k < 2 * n - 1; k++) {
            for(int x1 = Math.max(k - n + 1, 0); x1 <= Math.min(k, n - 1); x1++) {
                int y1 = k - x1;
                if(grid[x1][y1] == -1)
                    continue;

                for(int x2 = x1; x2 <= Math.min(k, n - 1); x2++) {
                    int y2 = k - x2;
                    if(grid[x2][y2] == -1)
                        continue;

                    // 都往右
                    int res = f[k-1][x1][x2];
                    // 下 右
                    if(x1 > 0) {
                        res = Math.max(res, f[k-1][x1-1][x2]);
                    }
                    // 右 下
                    if(x2 > 0) {
                        res = Math.max(res, f[k-1][x1][x2-1]);
                    }
                    // 下 下
                    if(x1 > 0 && x2 > 0) {
                        res = Math.max(res, f[k-1][x1-1][x2-1]);
                    }

                    res += grid[x1][y1];
                    if(x1 != x2) {
                        res += grid[x2][y2];
                    }
                    f[k][x1][x2] = res;
                }
            }

        }

        return Math.max(f[n*2-2][n-1][n-1], 0);
    }
}
