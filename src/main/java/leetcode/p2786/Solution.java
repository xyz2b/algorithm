package leetcode.p2786;

public class Solution {
    public long maxScore(int[] nums, int x) {
        // 当前状态只有从两种状态转移而来
        // 上一个是偶数得到的最大值
        // 上一个事奇数得到的最大值
        // 所以dp只需要设置2个元素的数组即可，表示奇(1) 偶(0)
        long res = nums[0];
        // 因为题目限定了一开始位置必须是从0开始，所以这里dp初始化不能为0。而是需要一个非常小负数。如果是初始化为0，那么后面路径的可能不是从0开始了
        long[] dp = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        dp[nums[0] % 2] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int p = nums[i] % 2;

            // 上一个和当前奇偶一样的 dp[p]
            // 上一个和当前奇偶不一样的 dp[1-p]
            long cur = Math.max(dp[p] + nums[i], dp[1-p] + nums[i] - x);

            res = Math.max(cur, res);

            dp[p] = Math.max(cur, dp[p]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {8,50,65,85,8,73,55,50,29,95,5,68,52,79};
        Solution solution = new Solution();
        System.out.println(solution.maxScore(nums, 74));
    }
}
