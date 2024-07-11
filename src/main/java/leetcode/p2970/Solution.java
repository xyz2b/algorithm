package leetcode.p2970;

public class Solution {
    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isIncreasing(nums, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean isIncreasing(int[] nums, int l, int r) {
        for(int i = 1; i < nums.length; i++) {
            if(i >= l && i <= r+1) {        // [0, l-1]严格递增，[r+1, nums.length-1]严格递增，[l, r]略过
                continue;
            }

            if(nums[i] <= nums[i-1]) {
                return false;
            }
        }

        if((l-1 >= 0 && r + 1 <= nums.length - 1 && nums[l-1] >= nums[r+1])) {
            return false;
        }

        return true;
    }
}
