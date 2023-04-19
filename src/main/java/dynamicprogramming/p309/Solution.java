package dynamicprogramming.p309;


import java.util.Arrays;

public class Solution {
    // 暴力解法
    public int maxProfit(int[] prices) {
        if(prices.length <= 1) return 0;
        return dfs(prices, 0, false);
    }

    // 尝试在[day, n-1]中进行买卖操作，获取最大利益
    // hold表示当前是否持有股票
    private int dfs(int[] prices, int day, boolean hold) {
        if(day >= prices.length) {
            return 0;
        }

        int rst = 0;
        for(int i = day; i < prices.length; i++) {
            if(hold) {  // 卖出
                rst = Math.max(rst, dfs(prices, i + 2, false) + prices[i]);
            } else {    // 买入
                rst = Math.max(rst, dfs(prices, i + 1, true) - prices[i]);
            }
        }

        return rst;
    }

    // 记忆化搜索
    // memo(0, i) 考虑(i, n - 1)卖出操作最大利益
    // memo(1, i) 考虑(i, n - 1)买入操作最大利益
    private int[][] memo;
    public int maxProfit2(int[] prices) {
        if(prices.length <= 1) return 0;
        memo = new int[2][prices.length];

        return dfs2(prices, 0, false);
    }

    // 尝试在[day, n-1]中进行买卖操作，获取最大利益
    // hold表示当前是否持有股票
    private int dfs2(int[] prices, int day, boolean hold) {
        if(day >= prices.length) {
            return 0;
        }

        if(hold && memo[0][day] != 0) {
            return memo[0][day];
        }

        if(!hold && memo[1][day] != 0) {
            return memo[1][day];
        }

        for(int i = day; i < prices.length; i++) {
            if(hold) {  // 卖出
                memo[0][day] = Math.max(memo[0][day], dfs2(prices, i + 2, false) + prices[i]);
            } else {    // 买入
                memo[1][day] = Math.max(memo[1][day], dfs2(prices, i + 1, true) - prices[i]);
            }
        }

        if(hold) {
            return memo[0][day];
        } else {
            return memo[1][day];
        }
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        Solution solution = new Solution();
        System.out.println(solution.maxProfit2(prices));
    }
}
