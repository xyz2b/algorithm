package leetcode.p75;

public class Solution {
    // 由于数组中只有三种取值，排序可以使用计数排序
    public void sortColors(int[] nums) {
        int[] bucket = new int[3];

        // 统计每种取值的数量
        for(int i = 0; i < nums.length; i++) {
            bucket[nums[i]]++;
        }

        // 按照每种取值的数量，将其摆放到原数组中
        int index = 0;
        for(int i = 0; i < bucket.length; i++) {
            for(int c = 0; c < bucket[i]; c++) {
                nums[index++] = i;
            }
        }
    }
}
