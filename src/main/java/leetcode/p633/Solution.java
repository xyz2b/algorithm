package leetcode.p633;

public class Solution {
    public boolean judgeSquareSum(int c) {
        for (long a  = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);

            if(b == (int) b) { // b 是整数
                return true;
            }
        }
        return false;
    }
}
