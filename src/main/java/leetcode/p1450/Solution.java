package leetcode.p1450;

public class Solution {
    // 判断queryTime是否在startTime和endTime相同索引的元素之间
    // 即判断 startTime[i] <= queryTime <= endTime[i]
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int rst = 0;
        for(int i = 0; i < startTime.length; i++) {
            if(startTime[i] <= queryTime && queryTime <= endTime[i]) {
                rst++;
            }
        }
        return rst;
    }
}
