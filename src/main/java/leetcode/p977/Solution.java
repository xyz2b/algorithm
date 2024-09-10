package leetcode.p977;

public class Solution {
    public int[] sortedSquares(int[] nums) {
        // 将数组分为两半，一半是正数，一般是负数，其实两边都是排好序的
        // 将两边的数据进行merge即可（归并排序中的merge步骤）
        // 负数从最后一个往前，正数从第一个往后

        int[] ret = new int[nums.length];

        int index = 0;
        // 先找到第一个非负数的位置
        while (index < nums.length && nums[index] < 0) {
            index++;
        }

        // merge
        int negStartIndex = index - 1;      // 如果为-1表示没有负数
        int noNegStartIndex = index;        // 如果为nums.length表示没非负数
        int retIndex = 0;
        while (negStartIndex >= 0 || noNegStartIndex < nums.length) {
            if(negStartIndex < 0) { // 负数一边没有元素了
                ret[retIndex] = nums[noNegStartIndex] * nums[noNegStartIndex];
                noNegStartIndex++;
            } else if(noNegStartIndex >= nums.length) {    // 非负数一边没有元素了
                ret[retIndex] = nums[negStartIndex] * nums[negStartIndex];
                negStartIndex--;
            } else {
                if(Math.abs(nums[negStartIndex]) < nums[noNegStartIndex]) {
                    ret[retIndex] = nums[negStartIndex] * nums[negStartIndex];
                    negStartIndex--;
                } else {
                    ret[retIndex] = nums[noNegStartIndex] * nums[noNegStartIndex];
                    noNegStartIndex++;
                }
            }
            retIndex++;
        }
        return ret;
    }
}
