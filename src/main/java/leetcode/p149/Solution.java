package leetcode.p149;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 在一条直线上的意思就是满足同一个等式，y = Cx + D，所以(C,D)元组就可以表示一条直线
    // 查找表，查找表的key为point1和point2所在直线方程的(C,D)元组(字符串形式)，value为和point1在直线(C,D)上的其他点的数量
    /**
     * 知道两个点
     * 就可以根据这两个点求出直线方程
     * y=Cx+D
     * 把两个点的坐标带进去就是两元一次方程
     *
     * C=(y2-y1)/(x2-x1)
     * D=y1-C*x1
     *
     * (C,D)一样的就在一条线上
     *
     * 需要注意两种特殊情况
     * 如果平行于x轴(y相同)，C=0.0/-0.0，D=y1=y2
     * 如果平行于y轴(x相同)，C=Infinity/-Infinity，D=NaN
     *
     * 遍历每个点
     * 找到和该点在不同直线(C,D)上的点的数量
     * 再统计最找到最多点的直线
     * */
    public int maxPoints(int[][] points) {
        if(points.length == 0) return 0;
        if(points.length == 1) return 1;

        int rst = 0;

        for(int i = 0; i < points.length; i++) {
            int[] point1 = points[i];
            int x1 = point1[0];
            int y1 = point1[1];
            // key为point1和point2所在直线方程的(C,D)元组(字符串形式)，value为和point1在直线(C,D)上的其他点的数量
            // 每个点都有这样的一张查找表
            Map<String, Integer> freq = new HashMap<>();
            // 遍历其他的点，看和point1在不同直线上的点的数量
            for(int j = i + 1; j < points.length; j++) {
                    int[] point2 = points[j];
                    int x2 = point2[0];
                    int y2 = point2[1];
                    /**
                     * 如果平行于x轴(y相同)，C=0.0/-0.0，D=y1=y2
                     * 如果平行于y轴(x相同)，C=Infinity/-Infinity，D=NaN
                     * */
                    // +0.0是避免-0.0的出现
                    double C = (double) (y2 - y1) / (double) (x2 - x1) + 0.0;
                    // 避免-Infinity的出现（Infinity与-Infinity是相同的）
                    if(C == Double.NEGATIVE_INFINITY) {
                        C = Double.POSITIVE_INFINITY;
                    }
                    double D = y1 - C * x1;

                    StringBuilder sb = new StringBuilder("");
                    sb.append('#');
                    sb.append(C);
                    sb.append(D);
                    String key = sb.toString();

                    freq.put(key, freq.getOrDefault(key, 0) + 1);
            }

            for(Map.Entry<String, Integer> entry : freq.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                // 自己跟自己肯定是在一条直线上的，所以要加上自己
                rst = Math.max(rst, value + 1);
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int[][] points = {{0,1},{0,0},{0,4},{0,-2},{0,-1},{0,3},{0,-4}};
        Solution solution = new Solution();
        System.out.println(solution.maxPoints(points));
    }
}
