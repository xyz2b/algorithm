package leetcode.p873;

import java.util.HashMap;

class Solution {
    // 动态规划
    // dp[j][i] 它的索引表示数列的最后两位数在arr的索引为j和i(j<i)的值，它的值表示以arr索引为j和i的值为最后两位数的数列的长度
    // arr的k索引对应的值 如果是以arr索引为j和i的值为最后两位数的数列 的倒数第三位数，则要满足arr[i] - arr[j] = arr[k]
    // 以arr索引为j和i的值为最后两位数的数列的长度dp[j][i] 是 以arr索引为k和j的值为最后两位数的数列的长度dp[k][i] + 1，前提是满足 arr[i] - arr[j] = arr[k]
    // 如果不存在k，即k<0，即不存在以索引j和索引i对应的值为最后两位数的数列
    // 如果k=0，则dp[k][j] = 0，这样也能组成数列，但是数列最小长度为3，如果仅仅是dp[j][i] = dp[k][j] + 1，这种情况就会出错，因为这种情况得到的数列长度为2，所以要dp[j][i] = Math.max(dp[k][j] + 1, 3);
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;

        // 将arr数组中的值与索引的对应关系记录到map中
        // 因为arr单调递增，所以不用担心key重复
        HashMap<Integer, Integer> data = new HashMap<>();
        for(int i = 0; i < n; i++) {
            data.put(arr[i], i);
        }

        int[][] dp = new int[n][n];

        int ans = 0;
        // 遍历arr数组
        for(int i = 0; i < n; i++) {
            // 如果arr[j]的两倍不大于arr[i]
            // 因为arr是单调递增，j索引前面的索引k对应的值一定小于j索引对应的值，arr[k] < arr[j]
            // 所以arr[k] + arr[j] 一定小于 arr[i]
            for(int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                int k = data.getOrDefault(arr[i] - arr[j], -1);

                if(k >= 0){
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }

                ans = Math.max(ans, dp[j][i]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1,3,7,11,12,14,18};
        System.out.println(solution.lenLongestFibSubseq(arr));

    }
}
