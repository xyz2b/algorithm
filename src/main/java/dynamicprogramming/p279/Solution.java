package dynamicprogramming.p279;

import java.util.Arrays;

public class Solution {
    // 暴力枚举，递归，自上而下
    public int numSquares(int n) {
        return squaresNum(n);
    }

    // 返回 和为 n 的完全平方数的最少数量
    private int squaresNum(int n) {
        if(n == 0) {
            return 0;
        }

        if(n == 1) {
            return 1;
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            // (i * i) + (n - i * i) == n
            min = Math.min(min, squaresNum(n - i * i));
        }

        return min + 1;
    }

    // 记忆化搜索，递归，自上而下
    private int[] memo;
    public int numSquares2(int n) {
        memo = new int[n + 1];
        return squaresNum2(n);
    }

    // 返回 和为 n 的完全平方数的最少数量
    private int squaresNum2(int n) {
        if(n == 0) {
            return 0;
        }

        if(n == 1) {
            return 1;
        }

        if(memo[n] != 0) {
            return memo[n];
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i * i <= n; i++) {
            // (i * i) + (n - i * i) == n
            min = Math.min(min, squaresNum2(n - i * i));
        }

        memo[n] = min + 1;
        return min + 1;
    }

    // 动态规划，递归，自下而上
    public int numSquares3(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);

        memo[0] = 0;
        memo[1] = 1;

        for(int i = 2; i <=n; i++) {
            for (int j = 1; j * j <= i; j++) {
                // (j * j) + (n - j * j) == n
                memo[i] = Math.min(memo[i], memo[i - j * j] + 1);
            }
        }

        return memo[n];
    }

    public static void main(String[] args) {
        int n = 6730;
        Solution solution = new Solution();
        System.out.println(solution.numSquares3(n));
    }
}
