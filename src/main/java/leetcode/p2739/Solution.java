package leetcode.p2739;

public class Solution {
    public int distanceTraveled(int mainTank, int additionalTank) {
        int ret = 0;
        int last = mainTank;
        while (last >= 0) {
            ret++;
            last -= 5;
            if(last >=0 && additionalTank > 0) {
                last += 1;
                additionalTank--;
            }
        }

        return (ret * 5 + last) * 10;
    }

    public static void main(String[] args) {
        int mainTank = 8;
        int additionalTank = 2;
        Solution solution = new Solution();
        System.out.println(solution.distanceTraveled(mainTank, additionalTank));
    }
}
