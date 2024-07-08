package leetcode.p724;

public class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }

        int left = 0;
        int right = sum;
        for(int i = 0; i < nums.length; i++) {
            if(left == right - nums[i]) {
                return i;
            }

            left += nums[i];
            right -= nums[i];
        }

        return -1;
    }
}
