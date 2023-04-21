package dynamicprogramming.knapsackproblem;

import javax.security.auth.login.CredentialException;
import java.util.Arrays;

public class Solution {
    /**
     * 背包问题
     * 有一个背包，它的容量为C。现在有n个不同的物品，编号为0...n-1，其中每一件物品的重量为w(i)，价值为v(i)。
     * 问可以向这个背包中放入哪些物品，使得在不超过背包容量的基础上，物品的总价值最大
     *
     * 通常函数的参数个数意味着要解决这个问题所要满足的约束条件个数
     * 背包问题有两个约束条件：F(n,c)考虑将n个物品放进容量为C的背包，使得价值最大
     *
     * 状态转移函数，两种情况取最大值
     * F(i,c) = F(i-1,c) : 不考虑第i个物品(直接丢弃)，从i-1个物品开始考虑
     *        = v(i) + F(i-1, c-w(i)) : 将第i个物品装进背包(价值为v(i),重量为w(i))，之后考虑的问题是 从第i-1个物品开始考虑将它们放进容量为c-w(i)的背包中，使得价值最大
     * F(i,c) = max{F(i-1,c), v(i) + F(i-1,c-w(i))}
     * */
    private int[] w;
    private int[] v;
    private int n;
    private int[][] memo;
    public int knapsack01(int[] w, int[] v, int C) {
        this.w = w;
        this.v = v;
        n = w.length;
        memo = new int[n][C+1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return knapsack01(0, C);
    }

    // 记忆化搜索
    // 考虑将[0, index]范围内的物品放进容量为c的背包的最大价值
    private int knapsack01(int index, int c) {
        if(index >= n || c <= 0) {
            return 0;
        }

        if(memo[index][c] != -1) {
            return memo[index][c];
        }

        int res = knapsack01(index - 1, c);
        if(c >= w[index]) {
            res = Math.max(res, knapsack01(index - 1, c - w[index]) + v[index]);
        }

        memo[index][c] = res;
        return res;
    }

    // 动态规划
    public int knapsack0102(int[] w, int[] v, int C) {
        int n = w.length;
        int[][] memo = new int[n][C+1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        // 基本问题，将第0号物品放入容量为c的背包中的最大价值
        for(int c = 0; c <= C; c++) {
            memo[0][c] = c >= w[0] ? v[0] : 0;
        }

        for(int index = n - 1; index >= 0; index--) {
            for(int c = 0; c <= C; c++) {
                // 考虑将[0, index]范围内的物品放进容量为c的背包的最大价值
                memo[index][c] = Math.max(memo[index - 1][c], c >= w[index] ? memo[index - 1][c - w[index]] + v[index] : -1);
            }
        }

        return memo[n - 1][C];
    }
}
