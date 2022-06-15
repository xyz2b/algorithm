package leetcode.p719;

import java.util.Arrays;

public class Solution {

    /**
     *
     * 1.利用二分搜索法在数组中找到第k小的数值
     *      在这个问题中，数组是所有数对差值的数 [0, 1, 2, 3, 4, 5, ..., (nums[len - 1] - nums[0])]，假想出来的数组，步长为1，从0开始到数值对差值最大值
     *                  就是在上面数组中找到第k小的数值，即数对差值第k小的数值
     *  即利用利用二分搜索法在所有数对差数组中找到数对差为第k小的元素（在数据差距离数组里 [0..nums[len - 1] - nums[0]] 里一定存在第 k 小的距离值，这个数组就是普通整型数组）
     * 2.利用滑动窗口找到两个元素差值「距离」小于等于某个值的所有数对的个数
     *
     * */
    public int smallestDistancePair(int[] nums, int k) {
        int length = nums.length;
        // 先排个序
        Arrays.sort(nums);

        // 这里并没有将所有差值对都计算出来，存到数组里，内存不允许。而是假象了一个数组，这个数组中记录了所有数值对的差值（可能也有不存在的差值），差值从0开始，步长为1，一直到最大差值
        // left是数值对差值数组中最小的值，right是数值对差值数组中最大的值
        int left = 0, right = nums[length - 1] - nums[0];
        // 当 left 与 right 重合时，即找到答案
        while (left < right) {
            // 数对差值数组的，最大元素为nums[length - 1] - nums[0]， 最小元素为0
            // 下面计算出一个中间的 差值 元素
            int midNum = left + (right - left) / 2;
            // 利用滑动窗口机制，在nums数组中，搜寻数值对差值小于等于midNum的数值对数量
            int count = countLessEquals(nums, midNum);

            // 如果小于等于 midNum 的个数严格小于 k 个，说明 midNum 太小了，需要向更大的元素方向搜索
            // 所以下一轮搜索区间为 midNum + 1.. right
            if (count < k) {
                left = midNum + 1;
            } else { // 下一轮搜索区间为 0.. midNum
                right = midNum;
            }
        }

        return left;
    }

    // 利用滑动窗口机制，在nums数组中，搜寻数值对差值小于等于threshold的数值对数量
    private int countLessEquals(int[] nums, int threshold) {
        int count = 0;
        // left, right 为滑动窗口的左右边界
        // 如果 一个整数区间 里，「最小数」和「最大数」的差值为 k，那么这个整数区间里的 所有 数对的差值都小于等于 k。
        // nums已经有序，[left, right]中的 数对差值的 最大值 就是nums[right] - nums[left]，其他在[left, right]中的数对差值都小于等于nums[right] - nums[left]
        // 所以如果nums[right] - nums[left]已经大于target（需要小于等于target的数对），说明窗口需要滑动了，left需要自增1
        for (int left = 0, right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > threshold) {  // 窗口右边界和左边界的差值超出target，移动left
                left++;
            }

            count += right - left;
        }
        return count;
    }
}
