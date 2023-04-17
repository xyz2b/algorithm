package dynamicprogramming.p343;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int rst = Integer.MIN_VALUE;
    public int integerBreak(int n) {
        dfs(1, 0, n, 0, 1);
        return rst;
    }

    // 暴力枚举，递归，自上而下
    private void dfs(int start, int k, int n, int sum, int mul) {
        if(sum > n) {
            return;
        }

        if(sum == n && k >= 2) {
            rst = Math.max(rst, mul);
            return;
        }

        for(int i = start; i <= n; i++) {
            dfs(i, k + 1, n, sum + i, mul * i);

            if (sum + i >= n) {
                return;
            }

        }
    }


    private int[] memo;
    public int integerBreak2(int n) {
        memo = new int[n + 1];
        return breakInteger(n);
    }

    // 暴力枚举，递归，自下而上
    // 求和为4的数的最大乘积，即求和为3的数的最大乘积*1(1+X)、和为2的数的最大乘积*2(2+X)、和为1的数的最大乘积*3(3+X)，然后返回三者最大值
    // 返回和为n的数的最大乘积
    // 最优子结构：通过求解子问题的最优解，可以获得原问题的最优解
    // 存在重叠子问题，记忆化搜索进行优化
    private int breakInteger(int n) {
        if(n == 1) {
            return 1;
        }

        if(memo[n] != 0) {
            return memo[n];
        }

        int max = 0;
        for(int i = 1; i < n; i++) {
            // 除去integerBreak2(n - i) * i)之外，还有另外一个答案需要考虑，即分割成i和n-i的情况
            max = Math.max(max, Math.max((n - i) * i, breakInteger(n - i) * i));
        }

        memo[n] = max;
        return max;
    }

    public static void main(String[] args) {
        int n = 10;
        Solution solution = new Solution();
        System.out.println(solution.integerBreak2(n));
    }
}
