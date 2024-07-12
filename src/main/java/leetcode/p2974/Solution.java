package leetcode.p2974;

import java.util.Arrays;

public class Solution {
    public int[] numberGame(int[] nums) {
        // 排序后，相邻的两个元素交换位置
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i+=2) {
            int temp = nums[i];
            nums[i] = nums[i+1];
            nums[i+1] = temp;
        }

        return nums;
    }
}
