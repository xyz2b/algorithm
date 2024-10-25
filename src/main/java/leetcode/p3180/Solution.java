package leetcode.p3180;

import java.util.Arrays;

class Solution {
    // 记 rewardValues 的最大值为 m，因为最后一次操作前的总奖励一定小于等于 m - 1，所以可以获得的最大总奖励小于等于 2m - 1。假设上一次操作选择的奖励值为 x1，那么执行操作后的总奖励 x >= x1，根据题意，后面任一操作选择的奖励值 x2 一定都大于 x，从而有 x2 > x1，因此执行的操作是按照奖励值单调递增的
    // 根据以上推断，首先将 rewardValues 从小到大排序，使用 dp[k] 表示总奖励 k 是否可获得，初始时 dp[0] = 1，表示不执行任何操纵可获得总奖励0。然后我们对 rewardValues 进行遍历，令当前值为 x，那么对于 k [x, 2x-1]，将dp[k] 更新为 dp[k-x]，表示先前的操作可以获得总奖励 k - x，那么加上 x 后，就可以获取总奖励 k。最后返回 dp 中可以获得的最大总奖励
    // k [x, 2x-1]：之前的奖励不要了，就从当前奖励 x 开始操作。当前奖励为 x，那么之后能获得的奖励一定小于等于 2x - 1，所以 k 属于 [x, 2x-1]
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        // 最大值
        int m = rewardValues[rewardValues.length - 1];

        int[] dp = new int[2 * m];
        dp[0] = 1;
        for(int x : rewardValues) {
            for(int k = 2 * x - 1; k >= x; k--) {
                if(dp[k - x] == 1) {
                    dp[k] = 1;
                }
            }
        }

        int ret = 0;
        for(int i = 0; i < dp.length; i++) {
            if(dp[i] == 1) {
                ret = i;
            }
        }

        return ret;
    }
}
