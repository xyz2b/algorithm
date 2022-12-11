package leetcode.p1827;

public class Solution {
    public int minOperations(int[] nums) {
        // 找到第一个非严格递增的数，他要比前面一个数大1，然后后面的数依次递增1
        int rst = 0;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] <= nums[i-1]) {
                rst += nums[i-1] - nums[i] + 1;
                nums[i] = nums[i-1] + 1;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] nums = {8};
        Solution solution = new Solution();
        System.out.println(solution.minOperations(nums));
    }
}
