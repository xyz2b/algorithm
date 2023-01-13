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

    // 三路快排
    public void sortColors2(int[] nums) {
        // [0, zero] == 0;
        int zero = -1;

        // [two, n - 1] == 2;
        int two = nums.length;

        // [zero + 1, i - 1] == 1
        int i = 0;
        while(i < two) {
            if(nums[i] == 0) {
                zero++;
                swap(nums, i, zero);
                i++;
            } else if (nums[i] == 2) {
                two--;
                swap(nums, i, two);
            } else {    // nums[i] == 1;
                i++;
            }
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
