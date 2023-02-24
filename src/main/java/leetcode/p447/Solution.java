package leetcode.p447;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        if(points.length < 3) return 0;

        int rst = 0;

        for(int i = 0; i < points.length; i++) {
            // key为pointI到pointJ之间的距离，value为该距离出现了几次
            Map<Integer, Integer> distances = new HashMap<>();
            for(int j = 0; j < points.length; j++) {
                if(i != j) {
                    int[] pointI = points[i];
                    int[] pointJ = points[j];

                    int distance = (pointI[0] - pointJ[0]) * (pointI[0] - pointJ[0]) + (pointI[1] - pointJ[1]) * (pointI[1] - pointJ[1]);

                    distances.put(distance, distances.getOrDefault(distance, 0) + 1);
                }
            }

            // 统计出总共有多少种组合
            for(Map.Entry<Integer, Integer> entry : distances.entrySet()) {
                int value = entry.getValue();

                // pointI至少到两个点的距离相等才有解
                if(value >= 2) {
                    // pointI到value个点的距离相等，则解得组合总共有value*(value-1)个
                    // 类如，[[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]，[1,0]点到两个点的距离相等，则组合有2种
                    // 类如，[[1,0],[0,0],[2,0]] 、 [[1,0],[2,0],[0,0]] 、 [[1,0],[0,0],[1,1]] 、 [[1,0],[1,1],[0,0]] 、 [[1,0],[2,0],[1,1]] 、 [[1,0],[1,1],[2,0]]
                    //      [1,0]点到三个点的距离相等，则组合有6种
                    rst += value * (value - 1);
                }
            }
        }

        return rst;
    }
}
