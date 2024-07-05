package leetcode.p3033;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // 找每一列的-1和最大值
        List<List<Integer>> one = new ArrayList<>(cols);
        List<Integer> max = new ArrayList<>(cols);

        for(int c = 0; c < cols; c++) {
            one.add(new ArrayList<>());
            max.add(Integer.MIN_VALUE);
            for(int r = 0; r < rows; r++) {
                if(matrix[r][c] == -1) {
                    one.get(c).add(r);
                } else {
                    if(matrix[r][c] > max.get(c)) {
                        max.set(c, matrix[r][c]);
                    }
                }
            }
        }

        for(int c = 0; c < cols; c++) {
            List<Integer> oneRow = one.get(c);

            for(int r : oneRow) {
                matrix[r][c] = max.get(c);
            }
        }

        return matrix;
    }
}
