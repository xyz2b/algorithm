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

    // 返回从index房子之后能够最大偷多少钱
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

    public static void main(String[] args) {
        int[] nums = {2,1,1,2};
        Solution solution = new Solution();
        System.out.println(solution.rob3(nums));
    }

}
