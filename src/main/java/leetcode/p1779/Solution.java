package leetcode.p1779;

public class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int rst = -1;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < points.length; i++) {
            int px = points[i][0];
            int py = points[i][1];

            if(px == x || py == y) {
                int d = Math.abs(x - px) + Math.abs(y - py);
                if(d < min) {
                    rst = i;
                    min = d;
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int x = 3, y = 4;
//        int[][] points = new int[][] {{1,2},{3,1},{2,4},{2,3},{4,4}};
        int[][] points = new int[][] {{2,3}};
        Solution solution = new Solution();
        System.out.println(solution.nearestValidPoint(x, y, points));
    }
}
