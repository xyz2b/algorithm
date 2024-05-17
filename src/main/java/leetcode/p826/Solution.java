package leetcode.p826;

import java.util.*;

public class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[][] dp = new int[difficulty.length][2];
        for(int i = 0; i < difficulty.length; i++) {
            int d = difficulty[i];
            int p = profit[i];
            dp[i] = new int[] {d, p};
        }

        // 前一个小于后一个为正 倒序，前一个大于后一个为正 正序
        Arrays.sort(dp, (int[] o1, int[] o2) -> o1[0] - o2[0]);

//        // 小于等于当前工作难度的工作报酬最多为多少
//        for(int i = 1; i < difficulty.length; i++) {
//            if(dp[i][1] < dp[i-1][1]) {
//                dp[i][1] = dp[i-1][1];
//            }
//        }
//
//        // 小于等于它工作能力的工作报酬最大的那个
//        TreeMap<Integer, Integer> tm = new TreeMap<>();
//        for(int i = 0; i < difficulty.length; i++) {
//            tm.put(dp[i][0], dp[i][1]);
//        }

        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(dp[0][0], dp[0][1]);
        for(int i = 1; i < difficulty.length; i++) {
            // 小于等于当前工作难度的工作报酬最多为多少
            dp[i][1] = Math.max(dp[i][1], dp[i - 1][1]);
            tm.put(dp[i][0], Math.max(dp[i][1], dp[i - 1][1]));
        }

        int ret = 0;
        for (int w : worker) {
            // 小于等于它工作能力的工作报酬最大的那个
            Map.Entry<Integer, Integer> v = tm.floorEntry(w);
            if (v != null) {
                ret += v.getValue();
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] difficulty = new int[] {68,35,52,47,86};
        int[] profit = new int[] {67,17,1,81,3};
        int[] worker = new int[] {92,10,85,84,82};

        Solution solution = new Solution();
        System.out.println(solution.maxProfitAssignment(difficulty, profit, worker));
    }
}
