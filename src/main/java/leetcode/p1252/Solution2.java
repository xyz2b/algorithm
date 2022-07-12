package leetcode.p1252;

public class Solution2 {
    public int oddCells(int m, int n, int[][] indices) {
        // 由于每次操作只会将一行和一列的数增加 11，因此我们可以使用一个行数组 rows 和列数组 cols 分别记录每一行和每一列被增加的次数
        int[] row = new int[m];
        int[] col = new int[n];

        for (int i = 0; i < indices.length; i++) {
            row[indices[i][0]]++;
            col[indices[i][1]]++;
        }

        int rst = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // [i, j]这个位置被加的次数就等于第i行被加的次数 加上 第j列被加的次数
                if((row[i] + col[j]) % 2 == 1) {
                    rst++;
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[][] data = new int[][]{{0,1}, {1,1}};
        System.out.println(solution.oddCells(2,3, data));
    }
}
