package leetcode.p1260;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        // 行
        int m = grid.length;
        // 列
        int n = grid[0].length;

        List<List<Integer>> rst = new ArrayList<>(m);
        for(int i = 0; i < m; i++) {
            List<Integer> col = new ArrayList<>(n);
            for(int j = 0; j < n; j++) {
                col.add(0);
            }
            rst.add(col);
        }

        // 行
        for(int i = 0; i < m; i++) {
            // 列
            for(int j = 0; j < n; j++) {
                int c = grid[i][j];

                // 移动k个单位之后，当前[i][j]元素应该在哪一行
                // (j + k) / n，当前元素移动k个单位后，应该移动多少行
                // (i + ((j + k) / n)) ，当前元素移动k个单位后，应该在哪一行
                // (i + ((j + k) / n)) % m，%m是当上面计算出来的所在行号是超出边界的情况
                int row = (i + ((j + k) / n)) % m;
                // 移动k个单位之后，当前[i][j]元素应该在哪一列
                int col = (j + k) % n;

                rst.get(row).set(col, c);
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        List<List<Integer>> rst = solution.shiftGrid(grid, 4);
        for(List<Integer> l : rst) {
            for (int i : l) {
                System.out.println(i);
            }
        }
    }
}
