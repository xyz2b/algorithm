package leetcode.p1619;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Solution {
    // 排序，然后算出需要去掉的最大值和最小值的数量，然后从排序后的数组中开头和末尾跳过这些数量的元素
    public double trimMean(int[] arr) {
        Arrays.sort(arr);

        int length = arr.length;

        int deleteNums = (int)Math.round(length * 0.05);

        int rst = 0;

        for(int i = deleteNums; i < length - deleteNums; i++) {
            rst += arr[i];
        }

        BigDecimal bd = new BigDecimal((double) rst / (length - (2 * deleteNums)));
        bd = bd.setScale(5, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,8,4,10,0,7,1,3,7,8,8,3,4,1,6,2,1,1,8,0,9,8,0,3,9,10,3,10,1,10,7,3,2,1,4,9,10,7,6,4,0,8,5,1,2,1,6,2,5,0,7,10,9,10,3,7,10,5,8,5,7,6,7,6,10,9,5,10,5,5,7,2,10,7,7,8,2,0,1,1};
        Solution solution = new Solution();
        System.out.println(solution.trimMean(arr));
    }
}
