package leetcode.p593;

import java.util.Arrays;

public class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] square = new int[][] {p1, p2, p3, p4};

        Arrays.sort(square, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);


        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.validSquare(new int[] {0,0}, new int[] {1,1}, new int[] {1,0}, new int[] {0,1}));
    }
}
