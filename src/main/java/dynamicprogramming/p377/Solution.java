package dynamicprogramming.p377;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 类似于爬楼梯
    // 楼梯的长度为target,每次爬楼梯可选的层数从nums数组中挑选，问有几种爬法？
    /**
     * [1,2,3] 4
     * 1.爬0级台阶，就一种方法，即不爬
     * 2.爬1级台阶，就一种方法，从0级台阶再爬1级，一种方法
     * 3.爬2级台阶，从1级再爬1级（爬一级台阶有一种方法，2计算过了），从0级再爬2级（爬零级台阶有一种方法，1计算过了），2种方法
     * 4.爬3级台阶，从1级台阶再爬2级（爬两级台阶有两种方法，3计算过了），从2级台阶再爬1级（爬一级台阶有一种方法，2计算过了），3种方法
     * 5.爬4级台阶，从1级再爬3级（爬三级台阶有3种方法，4计算过了），从2级台阶再爬2级（爬两级台阶有两种方法，3计算过了），从3级台阶再爬1级（爬一级台阶有一种方法，2计算过了），7种方法
     * */
    public int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];

        dp[0] = 1;
        for(int i = 1; i <= target; i++) {
            for(int n : nums) {
                if(i - n >=0) {
                    dp[i] += dp[i - n];
                }
            }
        }

        return dp[target];
    }
}
