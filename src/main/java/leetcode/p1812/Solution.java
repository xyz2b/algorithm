package leetcode.p1812;

public class Solution {
    // 奇数配奇数就是黑，奇数配偶数就是白，偶数配偶数是黑
    public boolean squareIsWhite(String coordinates) {
        int x = coordinates.charAt(0) - 'a' + 1;
        int y = coordinates.charAt(1) - '0';

        if(x % 2 == 0 && y % 2 ==0) {
            return false;
        } else if (x % 2 > 0 && y % 2 > 0) {
            return false;
        } else {
            return true;
        }
    }
}
