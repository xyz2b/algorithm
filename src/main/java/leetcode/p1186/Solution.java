package leetcode.p1186;


/**
 * 本题是典型的动态规划应用题，我们可以将问题拆分成多个子问题，即求解以 arr[i] 结尾的最多删除一次的非空子数组的最大和。我们以 dp[i][k] 表示以 arr[i] 结尾，删除 k 次的非空子数组的最大和（删除前的末尾元素为 arr[i]，就视为以 arr[i] 结尾）。初始时 dp[0][0]=arr[0]，dp[0][1]＝0（以 arr[0] 结尾，删除一次的非空子数组不存在，因此 dp[0][1] 不会计入结果）。当 i>0 时，转移方程如下：
 * 	dp[i][0]=max(dp[i−1][0],0)+arr[i]
 * 	dp[i][1]=max(dp[i−1][1]+arr[i],dp[i−1][0])
 *
 * 第一个转移方程表示在不删除的情况下，以 arr[i] 为结尾的非空子数组的最大和 dp[i][0] 与 dp[i－1][0] 有关，当 dp[i−1][0]>0 时，直接将 arr[i] 与 i−1 时的最大非空子数组连接时，取得最大和，否则只选 arr[i] 时，取得最大和。
 * 第二个转移方程表示在删除一次的情况下，以 arr[i] 为结尾的非空子数组有两种情况：
 * 	不删除 arr[i]，那么选择 arr[i] 与 dp[i−1][1] 对应的子数组（已执行一次删除）。
 * 	删除 arr[i]，那么选择 dp[i−1][0] 对应的非空子数组（未执行一次删除，但是等同于删除了 arr[i]）。
 * dp[i][1] 取以上两种情况的最大和的最大值。
 * 注意到 dp[i][∗] 的值只与 dp[i−1][∗] 有关，因此我们可以只使用两个整数来节省空间。
 * */
public class Solution {
    public int maximumSum(int[] arr) {
        int ret = arr[0];
        int dp0 = arr[0], dp1 = 0;
        for(int i = 1; i < arr.length; i++) {
            dp1 = Math.max(dp1 + arr[i], dp0);
            dp0 = Math.max(dp0, 0) + arr[i];

            ret = Math.max(ret, Math.max(dp0, dp1));
        }
        return ret;
    }
}
