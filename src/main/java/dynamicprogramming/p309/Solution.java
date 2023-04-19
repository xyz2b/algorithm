package dynamicprogramming.p309;

import java.io.LineNumberReader;

public class Solution {
    public int maxProfit(int[] prices) {
        return dfs(prices, 0, 1);
    }

    private int dfs(int[] prices, int day, int status) {
        if(day >= prices.length) {
            return 0;
        }

        int rst = Integer.MIN_VALUE;
        for(int i = day; i < prices.length; i++) {
            if(status == 0) { // 持有时候，只能卖和不做任何操作
                // 卖，下一个状态冷冻
                rst = Math.max(dfs(prices, day + 1, 2) + prices[day], rst);
                // 不做任何操作，继续持有
                rst = Math.max(dfs(prices, day + 1, status), rst);
            } else if(status == 1) { // 非持有时候，只能买和不做任何操作
                // 买
                rst = Math.max(dfs(prices, day + 1, 1) - prices[day], rst);
                // 不做任何操作
                rst = Math.max(dfs(prices, day + 1, status), rst);
            } else { // 冷冻期，不能做任何操作
                rst = Math.max(dfs(prices, day + 1, status), rst);
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        Solution solution = new Solution();
        System.out.println(solution.maxProfit(prices));
    }
}
