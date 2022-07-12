package leetcode.p1252;

public class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[][] data = new int[m][n];

        for (int i = 0; i < indices.length; i++) {
            for(int j = 0; j < m; j++) {
                data[j][indices[i][1]]++;
            }

            for(int j = 0; j < n; j++) {
                data[indices[i][0]][j]++;
            }
        }

        int rst = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (data[i][j] % 2 == 1) {
                    rst++;
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] data = new int[][]{{0,1}, {1,1}};
        System.out.println(solution.oddCells(2,3, data));
    }
}
