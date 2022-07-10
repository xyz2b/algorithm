package leetcode.p1217;

public class Solution {
    // 所有奇数位置的筹码都可以通过移动2格（cost=0）到达位置1
    // 所有偶数位置的筹码都可以通过移动2格（cost=0）到达位置2
    // 所以只需要比较奇数位置筹码和偶数位置筹码，哪个少即可
    public int minCostToMoveChips(int[] position) {
        int evenNum = 0;
        int oddNum = 0;

        for (int i = 0; i < position.length; i++) {
            if (position[i] % 2 == 0) {
                evenNum++;
            } else {
                oddNum++;
            }
        }

        return Math.min(evenNum, oddNum);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] position = new int[] {1,1};
        System.out.println(solution.minCostToMoveChips(position));
    }
}
