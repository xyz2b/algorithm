package leetcode.lcp61;

public class Solution {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int ret = 0;
        int last = 0;

        for(int i = 0; i < temperatureA.length - 1; i++) {
            int nextI = i + 1;
            int pA = temperatureA[nextI] > temperatureA[i] ? 1 : (temperatureA[nextI] == temperatureA[i] ? 0 : -1);
            int pB = temperatureB[nextI] > temperatureB[i] ? 1 : (temperatureB[nextI] == temperatureB[i] ? 0 : -1);

            if(pA == pB) {
                last++;
                ret = Math.max(ret, last);
            } else {
                last = 0;
            }
        }
        return ret;
    }
}
