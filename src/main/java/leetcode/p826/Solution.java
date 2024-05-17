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

    // 排序 + 双指针
    /**
     * 我们首先对工人按照能力大小排序，对工作按照难度排序。
     * 我们使用「双指针」的方法，一个指针指向工人数组，一个指向任务数组，从低难度的任务开始遍历。
     *      对于每个工人，我们继续遍历任务，直到难度大于其能力，并把可以完成任务中的最大利润更新到结果中。
     * 最后返回所有工人能得到的利润总和。
     * */
    public int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
        // 按工作难度进行排序
        int[][] dp = new int[difficulty.length][2];
        for(int i = 0; i < difficulty.length; i++) {
            int d = difficulty[i];
            int p = profit[i];
            dp[i] = new int[] {d, p};
        }

        // 前一个小于后一个为正 倒序，前一个大于后一个为正 正序
        Arrays.sort(dp, (int[] o1, int[] o2) -> o1[0] - o2[0]);
        Arrays.sort(worker);

        int ret = 0;
        int best = 0;
        int d = 0;
        // 双指针，一个指向排序后的worker数组，一个指向排序后的dp数组
        for(int w = 0; w < worker.length; w++) {
            while (d < difficulty.length && dp[d][0] <= worker[w]) {
                best = Math.max(best, dp[d][1]);
                d++;
            }
            ret += best;
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
