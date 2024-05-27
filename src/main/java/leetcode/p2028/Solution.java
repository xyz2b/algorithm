package leetcode.p2028;

import java.util.Arrays;

public class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int less = (mean * (m + n)) - Arrays.stream(rolls).sum();

        // n 个数之和为 less

        // n个1都比less大，n个6都比less小
        if(n * 1 > less || n * 6 < less) {
            return new int[] {};
        }

        // 商，就是基数
        int quotient = less / n;
        // 余数，余数就是需要 多少个 基数+1
        int remainder = less % n;

        int[] ret = new int[n];
        for(int i = 0; i < n; i++) {
            if(i < remainder) {
                ret[i] = quotient + 1;
            } else {
                ret[i] = quotient;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] rolls = {1,2,3,4};
        int mean = 6;
        int n = 4;
        Solution solution = new Solution();
        int[] ret = solution.missingRolls(rolls, mean, n);
        for(int i : ret) {
            System.out.println(i);
        }
    }
}
