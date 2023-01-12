package leetcode.p283;

public class Solution {
    public void moveZeroes(int[] nums) {
        // nums中 [0, k)的元素均为非0元素，k永远指向最前面的0
        int k = 0;

        // 遍历到第i个元素后，保证[0...i]中所有非0元素都按照顺序排列在[0, k)中，同时[k...i]都为0
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                if(i != k) {
                    swap(nums, k, i);
                    k++;
                } else {
                    k++;
                }
            }
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
