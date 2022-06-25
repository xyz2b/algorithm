package leetcode.offer091;

import java.util.Arrays;

class Solution {
    public int minCost(int[][] costs) {
        // 颜色的数量
        int colorNums = costs[0].length;
        // 房子的数量
        int houseNums = costs.length;

        // 每个房子选三种颜色的最低成本（包括它之前的房子选每个颜色的最低成本和）
        int[] dp = new int[colorNums];

        // 0号房选每个颜色的最低成本
        for (int i = 0; i < colorNums; i++) {
            dp[i] = costs[0][i];
        }
        // 1号房选每个颜色的最低成本（加上0号房的选每个颜色的最低成本），如果1号房选了红色，那么0号房只能选蓝色或绿色（因为后面的房子只要和前面的房子颜色不同即可）。此时0+1号房选每个颜色的最低成本和已经计算出来了
        // 2号房选每个颜色的最低成本（加上0+1号房选每个颜色的最低成本和），如果2号房选了红色，那么1号房只能选蓝色或绿色（因为后面的房子只要和前面的房子颜色不同即可）。此时0+1+2号房选每个颜色的最低成本和已经计算出来了
        // 依次类推


        for(int i = 1; i < houseNums; i++) {
            // 存放第i个房子选各种颜色的最低成本（加上第i-1个房子选各种颜色的最低成本），如果i号房选了红色，那么i-1号房只能选蓝色或绿色（因为后面的房子只要和前面的房子颜色不同即可）。此时0..i-1号房选每个颜色的最低成本和已经计算出来了
            int[] newDp = new int[colorNums];

            for (int j = 0; j < colorNums; j++) {
                newDp[j] = costs[i][j] + Math.min(dp[(j+1) % colorNums], dp[(j+2) % colorNums]);
            }

            dp = newDp;
        }

        Arrays.sort(dp);

        return dp[0];
    }
}
