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

        // 考虑将[0, index]范围内的物品放进容量为c的背包的最大价值
        // 情况一: 考虑将[0, index-1]范围内的物品放进容量为c的背包的最大价值（丢弃index物品）
        // 情况二：考虑将[0, index-1]范围内的物品放进容量为c - w[index]的背包的最大价值（将index物品放入背包如果能放进去的话(c >= w[index])）
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

        for(int index = 1; index < n; index++) {
            for(int c = 0; c <= C; c++) {
                // 考虑将[0, index]范围内的物品放进容量为c的背包的最大价值
                memo[index][c] = memo[index - 1][c];
                if(c >= w[index]) {
                    memo[index][c] = Math.max(memo[index][c], memo[index - 1][c - w[index]] + v[index]);
                }
            }
        }

        return memo[n - 1][C];
    }

    // 动态规划 - 优化空间
    // F(i,c) = max{F(i-1,c), v(i) + F(i-1,c-w(i))}
    // 求解第i行，只需要依赖第i-1行的数据即可，不需要前面的数据
    // 只需要两行即可，第一行永远在处理i为偶数的情况，第二行永远在处理i为奇数的情况
    public int knapsack0103(int[] w, int[] v, int C) {
        int n = w.length;
        int[][] memo = new int[2][C+1];
        for(int i = 0; i < 2; i++) {
            Arrays.fill(memo[i], -1);
        }

        // 基本问题，将第0号物品放入容量为c的背包中的最大价值
        for(int c = 0; c <= C; c++) {
            memo[0][c] = c >= w[0] ? v[0] : 0;
        }

        for(int index = n - 1; index >= 0; index--) {
            for(int c = 0; c <= C; c++) {
                // 考虑将[0, index]范围内的物品放进容量为c的背包的最大价值
                // 由于memo物理上只有2行，所以逻辑上第index行应该对应到物理上memo的第index%2行
                memo[index % 2][c] = Math.max(memo[(index - 1) % 2][c], c >= w[index] ? memo[(index - 1) % 2][c - w[index]] + v[index] : -1);
            }
        }

        return memo[(n - 1) % 2][C];
    }


    // 动态规划 - 优化空间2
    // F(i,c) = max{F(i-1,c), v(i) + F(i-1,c-w(i))}
    // 因为求解第i行，永远只使用到了第i-1行和第i行同列的元素以及同列左侧的第w[0...n]-w[i]个元素，永远不会使用同列右侧的元素，
    //      所以可以只使用一行空间，然后从右至左的刷新这一行
    public int knapsack0104(int[] w, int[] v, int C) {
        int n = w.length;
        int[] memo = new int[C+1];
        Arrays.fill(memo, -1);

        // 基本问题，将第0号物品放入容量为c的背包中的最大价值
        for(int c = 0; c <= C; c++) {
            memo[c] = c >= w[0] ? v[0] : 0;
        }

        for(int index = 1; index < n; index++) {
            for(int c = C; c >= w[index]; c--) {
                // 考虑将[0, index]范围内的物品放进容量为c的背包的最大价值
                // 由于memo物理上只有2行，所以逻辑上第index行应该对应到物理上memo的第index%2行
                memo[c] = Math.max(memo[c], memo[c - w[index]] + v[index]);
            }
        }

        return memo[C];
    }
}
