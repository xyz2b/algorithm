package leetcode.p2644;

import java.util.Arrays;

class Solution {
    public int maxDivScore(int[] nums, int[] divisors) {
        Arrays.sort(divisors);
        int ret = divisors[0];
        int dMax = 0;

        for(int d = 0; d < divisors.length; d++) {
            if(d != 0 && divisors[d] == divisors[d-1]) {
                continue;
            }
            int dn = 0;
            for(int n : nums) {
                if(n % divisors[d] == 0) {
                    dn++;
                }
            }

            if(dn > dMax) {
                dMax = dn;
                ret = divisors[d];
            }
        }
        return ret;
    }
}