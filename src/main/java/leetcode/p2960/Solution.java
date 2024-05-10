package leetcode.p2960;

public class Solution {
    public int countTestedDevices(int[] batteryPercentages) {
        int ret = 0;
        int less = 0;

        for(int i = 0; i < batteryPercentages.length; i++) {
            int p = batteryPercentages[i];
            if(p - less > 0) {
                ret++;
                less++;
            }
        }

        return ret;
    }
}