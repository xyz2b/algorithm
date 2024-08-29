package leetcode.p3142;

class Solution {
    public boolean satisfiesConditions(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for(int j = 0; j < n; j++) {
            for(int i = 0; i < m - 1; i++) {
                if(grid[i][j] != grid[i+1][j]) {
                    return false;
                }
            }
        }

        for(int j = 0; j < n - 1; j++) {
            if(grid[0][j] == grid[0][j+1]) {
                return false;
            }
        }
        return true;
    }
}
