package dynamicprogramming.p416;

import java.util.Arrays;

public class Solution {
    // 从nums中取出一部分数的和等于sum(nums)/2
    public boolean canPartition(int[] nums) {
        if(nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) {
            return false;
        }
        return partition(nums, nums.length - 1, sum / 2);
    }

    // 考虑nums[0...index]中取出某些数的和等于sum
    private boolean partition(int[] nums, int index, int sum) {
        if(sum == 0) {
            return true;
        }

        if(index < 0 ||  sum < 0) {
            return false;
        }

        // f(index, sum) = f(index-1,sum) || f(index-1, sum-nums[index])
        // 丢弃index                                          考虑index
        return partition(nums, index - 1, sum) || partition(nums, index - 1, sum - nums[index]);
    }

    // 记忆化搜索
    // 从nums中取出一部分数的和等于sum(nums)/2
    private int[][] memo;
    public boolean canPartition2(int[] nums) {
        if(nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) {
            return false;
        }
        int n = nums.length;

        memo = new int[n][sum/2 + 1];
        // -1表示没有计算过这种情况
        // 1表示可以
        // 0表示不可以
        for(int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return partition2(nums, n - 1, sum / 2);
    }

    // 考虑nums[0...index]中取出某些数的和等于sum
    private boolean partition2(int[] nums, int index, int sum) {
        if(sum == 0) {
            return true;
        }

        if(index < 0 ||  sum < 0) {
            return false;
        }

        if(memo[index][sum] == -1) {
            return memo[index][sum] == 1;
        }

        // f(index, sum) = f(index-1,sum) || f(index-1, sum-nums[index])
        // 丢弃index                                          考虑index
        memo[index][sum] = (partition2(nums, index - 1, sum) || partition2(nums, index - 1, sum - nums[index])) ? 1 : 0;

        return memo[index][sum] == 1;
    }

    // 动态规划
    public boolean canPartition3(int[] nums) {
        if(nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) {
            return false;
        }
        int n = nums.length;

        sum = sum / 2;
        boolean[][] memo = new boolean[n][sum + 1];

        for(int s = 0; s <= sum; s++) {
            memo[0][s] = (nums[0] == s);
        }

        for(int index = 1; index < n; index++) {
            for(int s = 0; s <= sum; s++) {
                memo[index][s] = memo[index-1][s];

                if(s >= nums[index]) {
                    memo[index][s] = memo[index][s] || memo[index - 1][s-nums[index]];
                }
            }
        }

        return memo[n-1][sum];
    }


    // 动态规划 内存优化
    public boolean canPartition4(int[] nums) {
        if(nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) {
            return false;
        }
        int n = nums.length;

        sum = sum / 2;
        boolean[] memo = new boolean[sum + 1];

        for(int s = 0; s <= sum; s++) {
            memo[s] = (nums[0] == s);
        }

        for(int index = 1; index < n; index++) {
            // s >= nums[index] 提前终止
            for(int s = sum; s >= nums[index]; s--) {
                memo[s] = memo[s] || memo[s-nums[index]];
            }
        }

        return memo[sum];
    }

    public static void main(String[] args) {
        int[] nums = {1,1};
        Solution solution = new Solution();
        System.out.println(solution.canPartition3(nums));
    }
}
