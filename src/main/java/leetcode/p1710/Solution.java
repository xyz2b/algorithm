package leetcode.p1710;

import java.util.Arrays;

public class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // 针对每个箱子可以装载的单元数量进行排序，从大到小，尽量选择大的单元数量
        Arrays.sort(boxTypes, (x, y) -> y[1] - x[1]);

        int rst = 0;
        for(int i = 0; i < boxTypes.length; i++) {
            int boxNums = boxTypes[i][0];
            int boxCellNums = boxTypes[i][1];

            if(truckSize - boxNums >= 0) {  // 如果卡车还能装下这个类型的所有盒子，就直接装上
                truckSize -= boxNums;
                rst += boxNums * boxCellNums;
            } else {    // 如果卡车装不下这个类型的所有盒子，那么就装卡车剩余容量个
                rst += truckSize * boxCellNums;
                break;
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[][] boxTypes = new int[][]{{5,10},{2,5},{4,7},{3,9}};
        int truckSize = 10;
        Solution solution = new Solution();
        System.out.println(solution.maximumUnits(boxTypes, truckSize));
    }
}
