package leetcode.p532;

import java.util.Arrays;

public class Solution {
    /**
     * 1            1       3       3      5      6             初始状态
     * left       right
     *第一次循环
     * 1            1       3       3      5      6             left == left+1，移动left
     *         left(right)
     *
     * 1            1       3       3      5      6             1和1的距离为0，小于2，移动right
     *           left      right
     *第二次循环
     * 1            1       3       3      5      6             right == right+1，移动right
     *           left             right
     *
     * 1            1       3       3      5      6             3和1的距离为2，等于2，计数+1，移动left和right
     *                    left           right
     *第三次循环
     * 1            1       3       3      5      6             left == left+1，移动left
     *                            left   right
     *
     * 1            1       3       3      5      6             5和3的距离为2，等于2，计数+1，移动left和right
     *                                   left   right
     *第四次循环
     * 1            1       3       3      5      6             6和5的距离为1，小于2，计数+1，移动right
     *                                   left         right
     *第五次循环
     * right超界，循环结束
     * */

    /**
     * 1            1       1       1      1      1             初始状态
     * left       right
     *第一次循环
     * 跳过left重复元素
     * 1            1       1       1      1      1             left == left+1，移动left
     *         left(right)
     *
     * 1            1       1       1      1      1             left == left+1，移动left
     *           right     left
     *
     * 1            1       1       1      1      1             left == left+1，移动left
     *           right             left
     *
     * 1            1       1       1      1      1             left == left+1，移动left
     *           right                    left
     *
     * 1            1       1       1      1      1             left == left+1，移动left，left移动之后，left+1超界，停止跳过left重复元素
     *           right                           left
     * 跳过right重复元素
     * 1            1       1       1      1      1             right == right+1，移动right
     *                    right                  left
     *
     * 1            1       1       1      1      1             right == right+1，移动right
     *                             right         left
     *
     * 1            1       1       1      1      1             right == right+1，移动right
     *                                    right  left
     *
     * 1            1       1       1      1      1            right == right+1，移动right,right移动之后，right+1超界，停止跳过right重复元素
     *                                        right(left)
     *
     * 1            1       1       1      1      1            1和1的距离为0，等于0，计数+1，移动left和right
     *                                          left   right
     *
     *第二次循环
     * right超界，循环结束
     * */
    // 类似滑动窗口
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int rst = 0;
        // 窗口：左边界 left，右边界 right
        for (int left = 0, right = 1; right < nums.length;) {
            // 先把相同的值都跳过，找到最后一个不相同的
            while (left + 1< nums.length && nums[left] == nums[left + 1]) {
                left = left + 1;
            }

            // 先把相同的值都跳过，找到最后一个不相同的
            while (right + 1 < nums.length && nums[right] == nums[right + 1]) {
                right = right + 1;
            }

            // 经过上面两个步骤之后，可能left和right相等，但他俩相等的情况是数组中前面有一段元素都相等（如[1, 1, 1, 3, 4, 5]），此时就相当于数组前面这一段只有一个不同的数对（这个数对也需要判断是否满足条件），就直接进行下面判断即可

            // 如果数对差值正好是k，那就要移动左右窗口，因为left..right窗口内的数对差值是小于等于nums[right] - nums[left]
            // 上面已经把相同的数值跳过了，所以这里left..right窗口内的数对差值是小于nums[right] - nums[left]，如果仅是移动left，那么差值会变小，仅是移动right，那么差值会变大，都不可能等于
            // 所以同时移动left和right
            if (nums[right] - nums[left] == k) {
                rst++;

                right++;
                left++;
            } else if (nums[right] - nums[left] < k) {  // 说明当前窗口内的数对差值都是小于k的，所以需要移动right，这样才可能有数对差值大于等于k的
                right++;
            } else {    // 说明当前窗口数对最大差值是大于k的，窗口里面可能有差值等于k的数对，所以移动left，不动right
                left++;
            }

            // 如果left经过移动之后，等于right，那么就将right往前移动一格
            if (left == right) {
                right++;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 1, 3, 3, 5, 6};
        int k = 2;
        System.out.println(solution.findPairs(nums, k));
    }
}
