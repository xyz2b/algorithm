package dynamicprogramming.p198;

import java.util.Arrays;

public class Solution {
    // 暴力解法
    public int rob(int[] nums) {
        int rst = 0;
        for(int i = 0; i < nums.length; i++) {
            rst = Math.max(rst, dfs(nums, i));
        }
        return rst;
    }

    // 返回从index房子之后能够最大偷多少钱
    private int dfs(int[] nums, int index) {
        if(index >= nums.length) {
            return 0;
        }

        int rst = 0;
        for(int i = index; i < nums.length; i++) {
            rst = Math.max(dfs(nums, i + 2), rst);
        }

        rst += nums[index];
        return rst;
    }


    // 记忆化搜索
    // 从index房子之后能够最大偷多少钱
    private int[] memo;
    public int rob2(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        int rst = 0;
        for(int i = 0; i < nums.length; i++) {
            rst = Math.max(rst, dfs2(nums, i));
        }
        return rst;
    }

    // 返回偷取[index, n-1]范围里的房子，最大能够偷取多少钱
    private int dfs2(int[] nums, int index) {
        if(index >= nums.length) {
            return 0;
        }

        if(memo[index] != -1) {
            return memo[index];
        }

        int rst = 0;
        for(int i = index; i < nums.length; i++) {
            rst = Math.max(dfs2(nums, i + 2), rst);
        }

        rst += nums[index];
        memo[index] = rst;
        return rst;
    }

    // 动态规划
    public int rob3(int[] nums) {
        if(nums.length == 1) return nums[0];

        // 从index房子之后能够最大偷多少钱
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);

        memo[nums.length - 1] = nums[nums.length - 1];
        memo[nums.length - 2] = nums[nums.length - 2];

        for(int i = nums.length - 3; i >= 0; i--) {
            int rst = 0;
            for(int j = i; j < nums.length - 2; j++) {
                rst = Math.max(rst, memo[j + 2]);
            }
            rst += nums[i];
            memo[i] = rst;
        }

        int rst = 0;
        for(int i = 0; i < memo.length; i++) {
            rst = Math.max(rst, memo[i]);
        }
        return rst;
    }


    /** 注意其中对状态的定义（对状态的定义即是递归函数的定义）
        考虑偷取[x, n-1]范围里的房子（递归函数的定义）
        这里是考虑偷取[x,n-1]范围里的房子，这并不代表一定会去偷取x这个房子，这个x只是考虑的范围的起点，
            在有些动态规划的问题中，很有可能这个状态就被定义成了一定要偷取x这个房子，
            这两个状态是不一样的，可以理解成这两个递归函数的定义是不一样的

        根据对状态的定义，决定状态的转移
        f(0) = max{v(0) + f(2), v(1) + f(3), v(2) + f(4), ..., v(n-3) + f(n-1), v(n-2), v(n-1) }    (状态转移方程)
        f(0): 考虑偷取[0, n-1]范围里的所有房子所能获得的最大金钱
        v(0): 取出0号房子的金钱
        v(0) + f(2): 取出0号房子的金钱 + 考虑偷取[2, n-1]范围里的所有房子所能获得的最大金钱
        max: 取{}里面所有情况的最大值，每种情况以逗号分隔
    **/

    public static void main(String[] args) {
        int[] nums = {2,1,1,2};
        Solution solution = new Solution();
        System.out.println(solution.rob3(nums));
    }

}
