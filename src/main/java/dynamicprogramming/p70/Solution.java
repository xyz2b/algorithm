package dynamicprogramming.p70;

public class Solution {
    public int climbStairs(int n) {
        if(n == 0) {
            return 1;
        }
        if(n == 1) {
            return 1;
        }

        // 走到n-1和走到n-2有多少种方法(退一步和退两步)
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    // 爬到第index个台阶有多少种方法
    private int[] memo;

    public int climbStairs2(int n) {
        memo = new int[n+1];

        return climb(n);
    }

    // 记忆化搜索 - 自上而下
    // 爬到第n个台阶有多少种方法
    private int climb(int n) {
        if(n == 0) {
            return 1;
        }
        if(n == 1) {
            return 1;
        }

        if(memo[n] == 0) {
            // 走到n-1和走到n-2有多少种方法(退一步和退两步)
            memo[n] = climb(n - 1) + climb(n - 2);
        }

        return memo[n];
    }

    // 动态规划 - 自下而上
    public int climbStairs3(int n) {
        memo = new int[n+1];
        memo[0] = 1;
        memo[1] = 1;

        for(int i = 2; i <= n; i++) {
            // 在i-1台阶再走一步，或者在i-2台阶再走两步就到了i台阶，所以走到i台阶的方法是走到i-1台阶的方法数+走到i-2台阶的方法数
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }

    private int climb3(int n) {

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs(3));
    }
}
