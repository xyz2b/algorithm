package leetcode.p3128;

public class Solution {
    public long numberOfRightTriangles(int[][] grid) {
        // 以 1 为直角顶点，它所能构成的直角三角形的个数 = 它所在的列其他1的个数 * 它所在行的其他1的个数
        // 统计每行，每列1的个数

        int m = grid.length;
        int n = grid[0].length;

        long[] row = new long[m];
        long[] col = new long[n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                row[i] += grid[i][j];
                col[j] += grid[i][j];
            }
        }

        long ret = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    ret += ((row[i] - 1) * (col[j] - 1));
                }
            }
        }

        return ret;
    }

    public long numberOfRightTriangles2(int[][] grid) {
        // 以 1 为直角顶点，查找 上下左右的 1 的个数，上和左右的1都可以组成直角，下和左右的1也都可以组成直角

        int m = grid.length;
        int n = grid[0].length;

        int ret = 0;
        for(int x = 0; x < m; x++) {
            for(int y = 0; y < n; y++) {
                if(grid[x][y] != 1) {
                    continue;
                }
                int up = 0;
                for(int u = x - 1; u >= 0; u--) {
                    if(grid[u][y] == 1) {
                        up++;
                    }
                }

                int down = 0;
                for(int d = x + 1; d < m; d++) {
                    if(grid[d][y] == 1) {
                        down++;
                    }
                }

                int left = 0;
                for(int l = y - 1; l >= 0; l--) {
                    if(grid[x][l] == 1) {
                        left++;
                    }
                }

                int right = 0;
                for(int r = y + 1; r < n; r++) {
                    if(grid[x][r] == 1) {
                        right++;
                    }
                }

                ret += (up * left + up * right + down * left + down * right);
            }
        }
        return ret;
    }
}
