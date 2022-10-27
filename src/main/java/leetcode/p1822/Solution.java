package leetcode.p1822;

import java.util.Arrays;

public class Solution {
    public int arraySign(int[] nums) {
        // 1.如果nums中有0，乘积结果就是0
        // 2.如果nums中有奇数个负数，乘积结果就是负
        // 3.如果nums中有偶数个负数，乘积结果就是正

        // 从小到大排序，将负数和0，放在最前面，正数可以不考虑
//        Arrays.sort(nums);

        int negativeCount = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                return 0;
            } else if(nums[i] < 0) {
                negativeCount++;
            } else {

            }
        }

        return negativeCount % 2 == 0 ? 1 : -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-1,1,-1,1,-1};
        Solution solution = new Solution();
        System.out.println(solution.arraySign(nums));
    }
}
