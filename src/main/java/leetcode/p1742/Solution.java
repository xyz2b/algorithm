package leetcode.p1742;

import java.util.Arrays;

public class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        // highLimit <= 100000，所以最大的每位数字的和就是5*9（99999）
        int[] boxes = new int[46];

        for(int i = lowLimit; i <= highLimit; i++) {
            boxes[count(i)]++;
        }

        return Arrays.stream(boxes).max().getAsInt();
    }

    private int count(int num) {
        int count = 0;

         do {
            int k = num % 10;
            count += k;
            num = num / 10;
        } while (num != 0);
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countBalls(19, 28));
    }
}
