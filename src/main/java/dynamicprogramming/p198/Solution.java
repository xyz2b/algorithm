package dynamicprogramming.p198;

import java.util.Arrays;

public class Solution {
    // 暴力解法
    public int rob(int[] nums) {
        return dfs(nums, 0);
    }

    // 考虑偷取[index, n-1]范围里的房子，最大能够偷取多少钱
    private int dfs(int[] nums, int index) {
        if(index >= nums.length) {
            return 0;
        }

        int rst = 0;
        for(int i = index; i < nums.length; i++) {
            rst = Math.max(dfs(nums, i + 2) + nums[i], rst);
        }

        return rst;
    }


    // 记忆化搜索
    // memo[i]表示考虑偷取nums[i, n-1]所能获得的最大收益
    private int[] memo;
    public int rob2(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        return dfs2(nums, 0);
    }

    // 考虑偷取[index, n-1]范围里的房子，最大能够偷取多少钱
    private int dfs2(int[] nums, int index) {
        if(index >= nums.length) {
            return 0;
        }

        if(memo[index] != -1) {
            return memo[index];
        }

        for(int i = index; i < nums.length; i++) {
            memo[index] = Math.max(dfs2(nums, i + 2) + nums[i], memo[index]);
        }

        return memo[index];
    }

    // 动态规划
    /** 注意其中对状态的定义（对状态的定义即是递归函数的定义）
     考虑偷取[x, n-1]范围里的房子（递归函数的定义）
     这里是考虑偷取[x,n-1]范围里的房子，这并不代表一定会去偷取x这个房子，这个x只是考虑的范围的起点，
     在有些动态规划的问题中，很有可能这个状态就被定义成了一定要偷取x这个房子，
     这两个状态是不一样的，可以理解成这两个递归函数的定义是不一样的

     根据对状态的定义，决定状态的转移
     f(0) = max{v(0) + f(2), v(1) + f(3), v(2) + f(4), ..., v(n-3) + f(n-1), v(n-2), v(n-1) }    (状态转移方程)
     f(0): 考虑偷取[0, n-1]范围里的所有房子所能获得的最大金钱
     v(0): 取出0号房子的金钱
     v(0) + f(2): 取出0号房子的金钱 + 考虑偷取[2, n-1]范围里的所有房子所能获得的最大金钱。
     v(n-2) + f(n): 但是对于f(n)来说，[n,n-1]是个空集，没有意义
     max: 取{}里面所有情况的最大值，每种情况以逗号分隔

     状态就是定义了这个函数要做什么
     而状态转移就是定义了这个函数要怎么做
     **/
    public int rob3(int[] nums) {
        if(nums.length == 0) return 0;

        // memo[i]表示考虑偷取nums[i, n-1]所能获得的最大收益
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);

        // nums[n-1, n-1]
        memo[nums.length - 1] = nums[nums.length - 1];

        // 考虑偷取[i, n-1]范围里的房子，最大能够偷取多少钱。最终是求出[0, n-1]
        // 状态转移方程: f(i) = max{v(i) + f(i+2), v(i+1) + f(i+1+2), v(i+2) + f(i+2+2), ..., v(n-3) + f(n-1), v(n-2), v(n-1) }
        for(int i = nums.length - 2; i >= 0; i--) {
            // memo[i]
            for(int j = i; j < nums.length; j++) {
                memo[i] = Math.max(memo[i], nums[j] + (j + 2 < nums.length ? memo[j + 2] : 0));
            }
        }

        return memo[0];
    }

    // 改变对状态的定义: 考虑偷取[0, i]范围里的房子（递归函数的定义）。最终是求出[0, n-1]
    // 状态转移方程: f(i) = max{v(0), v(1), v(2) + f(0), ..., v(i-1) + f(i-1-2), v(i) + f(i-2) }
    public int rob4(int[] nums) {
        // memo[i]表示考虑偷取nums[0, i]所能获得的最大收益
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        return dfs4(nums, nums.length - 1);
    }

    // 考虑偷取[0, index]范围里的房子，最大能够偷取多少钱
    private int dfs4(int[] nums, int index) {
        if(index < 0) {
            return 0;
        }

        if(memo[index] != -1) {
            return memo[index];
        }

        for(int i = index; i >= 0; i--) {
            memo[index] = Math.max(dfs4(nums, i - 2) + nums[i], memo[index]);
        }

        return memo[index];
    }

    public int rob5(int[] nums) {
        if(nums.length == 0) return 0;

        // memo[i]表示考虑偷取nums[0, i]所能获得的最大收益
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);

        // nums[0, 0]
        memo[0] = nums[0];

        // 改变对状态的定义: 考虑偷取[0, i]范围里的房子（递归函数的定义）。最终是求出[0, n-1]
        // 状态转移方程: f(i) = max{v(0), v(1), v(2) + f(0), ..., v(i-1) + f(i-1-2), v(i) + f(i-2) }
        for(int i = 1; i < nums.length; i++) {
            // memo[i]
            for(int j = i; j >= 0; j--) {
                memo[i] = Math.max(memo[i], nums[j] + (j - 2 >= 0 ? memo[j - 2] : 0));
            }
        }

        return memo[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        Solution solution = new Solution();
        System.out.println(solution.rob4(nums));
    }

}
