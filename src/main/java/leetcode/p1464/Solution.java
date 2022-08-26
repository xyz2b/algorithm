package leetcode.p1464;

import java.util.Arrays;

public class Solution {
    // 排个序，数组中两个最大的元素就在数组最后两个元素，数组中最大的两个元素乘积自然是最大的（nums数组中元素最小为1，减去1也不会小于0）
    // O(NlogN) 排序
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);

        return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
    }

    // O(N)遍历
    public int maxProduct1(int[] nums) {
        // 数组中的最大值
        int max = 0;
        // 数组中的第二大值
        int secondMax = 0;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > max) {
                // 如果当前遍历的值比最大值还大，将当前最大值降为第二大值，然后将当前遍历元素作为最大值
                secondMax = max;
                max = nums[i];
            } else {
                if(nums[i] > secondMax) {
                    secondMax = nums[i];
                }
            }
        }

        return (max - 1) * (secondMax - 1);
    }
}
