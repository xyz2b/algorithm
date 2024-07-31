package leetcode.p3111;

import java.util.Arrays;

public class Solution {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        // x1 x2的差距要在w以内，那每次都取最大值w，然后再遍历下一个可能得矩形开始点x1，再加w，循环往复，每次得到的都是一个可能的矩形
        // y不影响结果，y2可以取x1 x2区间内的点中y的最大值，总能覆盖到区间内的所有的点

        // 按照x进行排序
        Arrays.sort(points, (int[] p1, int[] p2) -> p1[0] - p2[0]);

        int nextX = points[0][0];
        int ret = 1;
        for(int i = 1; i < points.length; i++) {
            if(nextX + w < points[i][0]) {
                nextX = points[i][0];
                ret++;
            }
        }
        return ret;
    }
}
