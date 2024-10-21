package leetcode.p910;

import java.util.Arrays;

class Solution {
    // 贪心的思路就是让小的数尽量大，大的数尽量小。所以可以枚举分界点，分界点之前的数都+k，分界点之后的数都-k，枚举的过程中维护最大和最小值计算答案
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        int ret = nums[n - 1] - nums[0];
        for(int i = n - 1; i > 0; i--) {
            // 分界点i，i之前的都+k，i之后的都减k(包含i)
            if(nums[i] - k > nums[0] + k) { // 分界点 i 上 - k，大于+k最小值nums[0] + k
                // -k的最大值就是 nums[n - 1] - k（nums[i] - k是肯定比nums[n - 1] - k要小的），+k的最小值就是nums[0] + k。
                // 但是在分界点 (i-1) 上 + k 可能要大于nums[n - 1] - k，所以整个数组的最大值就是 max(nums[n - 1] - k, nums[i - 1] + k)
                ret = Math.min(ret, Math.max(nums[n - 1] - k, nums[i - 1] + k) - (nums[0] + k));
            } else {    // 分界点 i 上 - k，小于等于 +k最小值nums[0] + k
                // +k的最小值就是nums[0] + k，但是在分界点 i 上 -k 比这个最小值还小，所以整个数组的最小值就是nums[i] - k
                // 最大值和上述一样
                ret = Math.min(ret, Math.max(nums[n - 1] - k, nums[i - 1] + k) - (nums[i] - k));
            }
        }

        return ret;
    }
}