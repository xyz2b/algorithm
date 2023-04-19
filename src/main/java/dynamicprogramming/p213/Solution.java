package dynamicprogramming.p213;

import sun.security.x509.GeneralName;

import java.util.Arrays;

public class Solution {
    // 暴力解法
    public int rob(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    // 考虑偷取[start, end]范围里的房子，最大能够偷取多少钱
    private int dfs(int[] nums, int start, int end) {
        if(start >= nums.length) {
            return 0;
        }

        int rst = 0;
        for(int i = start; i <= end; i++) {
            if(i == 0) {
                end = nums.length - 2;
            }
            rst = Math.max(dfs(nums, i + 2, end) + nums[i], rst);
            if(i == 0) {
                end = nums.length - 1;
            }
        }

        return rst;
    }

    // 记忆化搜索
    // memo[0][i]表示考虑偷取nums[i, n-1]所能获得的最大收益
    // memo[1][i]表示考虑偷取nums[i, n-2]所能获得的最大收益
    private int[][] memo;
    public int rob2(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        memo = new int[2][nums.length];
        Arrays.fill(memo[0], -1);
        Arrays.fill(memo[1], -1);

        return Math.max(dfs2(nums, 0, nums.length - 1), dfs2(nums, 0, nums.length - 2));
    }

    // 考虑偷取[index, end]范围里的房子，最大能够偷取多少钱
    private int dfs2(int[] nums, int index, int end) {
        if(index > end) {
            return 0;
        }

        if(memo[nums.length - 1 - end][index] != -1) {
            return memo[nums.length - 1 - end][index];
        }

        for(int i = index; i <= end; i++) {
            if(i == 0) {            // 如果决定要偷0号房子，则最后不能偷取n-1号房子，因为他们相连
                end = nums.length - 2;
            }
            memo[nums.length - 1 - end][index] = Math.max(dfs2(nums, i + 2, end) + nums[i], memo[nums.length - 1 - end][index]);
            // 回溯
            if(i == 0) {
                end = nums.length - 1;
            }
        }

        return memo[nums.length - 1 - end][index];
    }

    public int rob3(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];

        // memo[0][i]表示考虑偷取nums[i, n-1]所能获得的最大收益
        // memo[1][i]表示考虑偷取nums[i, n-2]所能获得的最大收益
        int[][] memo = new int[2][nums.length];
        Arrays.fill(memo[0], -1);
        Arrays.fill(memo[1], -1);

        // nums[n-1, n-1]
        memo[0][nums.length - 2] = nums[nums.length - 2];
        memo[0][nums.length - 1] = 0;
        // nums[n-2, n-2]
        memo[1][nums.length - 1] = nums[nums.length - 1];

        // [0, n-2]，如果决定要偷0号房子，则最后不能偷取n-1号房子，因为他们相连
        for(int i = nums.length - 3; i >= 0; i--) {
            // memo[i]
            for(int j = i; j < nums.length - 1; j++) {
                memo[0][i] = Math.max(memo[0][i], nums[j] + (j + 2 < nums.length ? memo[0][j + 2] : 0));
            }
        }

        // [1, n-1]
        for(int i = nums.length - 2; i >= 0; i--) {
            // memo[i]
            for(int j = i; j < nums.length; j++) {
                memo[1][i] = Math.max(memo[1][i], nums[j] + (j + 2 < nums.length ? memo[1][j + 2] : 0));
            }
        }

        return Math.max(memo[0][0], memo[1][1]);
    }

    public static void main(String[] args) {
        int[] nums = {8,4,8,5,9,6,5,4,4,10};
        Solution solution = new Solution();
        System.out.println(solution.rob3(nums));
    }
}
