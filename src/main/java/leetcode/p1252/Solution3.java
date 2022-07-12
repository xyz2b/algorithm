package leetcode.p1252;

public class Solution3 {
    public int oddCells(int m, int n, int[][] indices) {
        // 由于每次操作只会将一行和一列的数增加 11，因此我们可以使用一个行数组 rows 和列数组 cols 分别记录每一行和每一列被增加的次数
        int[] row = new int[m];
        int[] col = new int[n];

        for (int i = 0; i < indices.length; i++) {
            row[indices[i][0]]++;
            col[indices[i][1]]++;
        }

        // [i, j]这个位置是奇数，就表示 第i行被加的次数是奇数且第j行被加的次数是偶数，或者第i行被加的次数是偶数且第j行被加的次数是奇数
        // 即仅当一个位置是奇数另一个位置是偶数，它的结果才是奇数
        // 所以可以分别计算奇数的行数，以及偶数的行数(m-奇数的行)；奇数的列数，以及偶数的列数(n-奇数的列数)
        // 奇数的行数*偶数的列数 + 偶数的行数*奇数的列数

        int oddX = 0, oddY = 0;
        for (int i = 0; i < m; i++) {
            if (row[i] % 2 == 1) {
                oddX++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (col[i] % 2 == 1) {
                oddY++;
            }
        }


        return oddX * (n - oddY) + oddY * (m - oddX);
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[][] data = new int[][]{{0,1}, {1,1}};
        System.out.println(solution.oddCells(2,3, data));
    }
}
