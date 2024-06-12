package leetcode.p2806;

public class Solution {
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        if(purchaseAmount % 10 == 0) {
            return 100 - purchaseAmount;
        }
        int ceil = ((purchaseAmount + 10) / 10) * 10;
        int floor = (((purchaseAmount + 10) / 10) - 1) * 10;

        int dif = Math.abs(ceil - purchaseAmount) - Math.abs(floor - purchaseAmount);
        if(dif > 0) {
            return 100 - floor;
        } else {
            return 100 - ceil;
        }
    }
}
