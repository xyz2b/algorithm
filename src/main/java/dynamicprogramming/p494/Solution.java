package dynamicprogramming.p494;

import java.util.Arrays;

public class Solution {
    // i为nums的索引，t为target
    // 由于有负数，所以target的范围要扩大，为 [-|sum(nums)|, +|sum(nums)|]
    // f(i, t) = f(i-1, t-nums[i]) + f(i-1, t+nums[i])
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();

        int[][] memo = new int[2][2 * sum + 1];

        // 将负数整体平移到非负数域，比如sum=5，则target取值范围为[-5,5]，整体平移到[0,10]
        // 平移前的值+sum(+5)即可得到平移后的值，平移后的值-sum(-5)即可得到平移之前的值
        for(int t = 0; t < 2 * sum + 1; t++) {  // 注意0的情况，如果target为0，+0、-0算两种情况
            if(nums[0] == -(t-sum)) {
                memo[0][t] += 1;
            }
            if(nums[0] == +(t-sum)) {
                memo[0][t] += 1;
            }
        }

        for(int i = 1; i < nums.length; i++) {
            for(int t = 0; t < 2 * sum + 1; t++) {
                memo[i % 2][t] = 0;
                if(Math.abs((t - sum) - nums[i]) <= sum) {
                    memo[i % 2][t] += memo[(i - 1) % 2][((t - sum) - nums[i]) + sum];
                }
                if(Math.abs((t - sum) + nums[i]) <= sum) {
                    memo[i % 2][t] += memo[(i - 1) % 2][((t - sum) + nums[i]) + sum];
                }
            }
        }

        // nums中的数字之和 还没有 Math.abs(target) 大，则nums中的数字不可能组成表达式结果为target
        if(Math.abs(target) > sum) {
            return 0;
        }
        return memo[(nums.length-1)%2][target+sum];
    }

    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0,0,0,0,1};
        int target = 1;
        Solution solution = new Solution();
        System.out.println(solution.findTargetSumWays(nums, target));
    }
}
