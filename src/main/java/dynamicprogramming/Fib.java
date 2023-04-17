package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fib {
    private int num = 0;

    public Fib() {}
    public int fib(int n) {
        num++;

        if(n == 0) return 0;
        if(n == 1) return 1;

        return fib(n - 1) + fib(n - 2);
    }

    private int[] memo;
    private int n;
    public Fib(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        this.n = n;
    }

    // 记忆化搜索 - 自顶向下的解决问题
    public int fib2(int n) {
        num++;

        if(n == 0) return 0;
        if(n == 1) return 1;

        if(memo[n] == -1) {
            memo[n] = fib2(n - 1) + fib2(n - 2);
        }
        return memo[n];
    }

    // 记忆化搜索 - 自底向上的解决问题
    // 动态规划: 将原问题拆解成若干子问题，同时保存子问题的答案，使得每个子问题只求解一次，最终获得原问题的答案
    // 最优子结构：通过求解子问题的最优解，可以获得原问题的最优解
    //                              -> 记忆化搜索(自顶向下的解决问题)
    // 递归问题 -> 最优子结构&重叠子问题       |
    //                              -> 动态规划(自底向上的解决问题)
    public int fib3(int n) {
        num++;

        memo[0] = 0;
        memo[1] = 1;
        for(int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 40;
        Fib fib = new Fib(n);
        fib.fib2(n);
        System.out.println(fib.num);
    }
}
