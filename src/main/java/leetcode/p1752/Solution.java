package leetcode.p1752;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    // 找到分界点(第一次逆序的位置)，分界点两边的数字应该为数组中的最大值以及最小值
    public boolean check(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();

        int splitIndex = 0;
        int reverse = 0;
        for(int i = 1; i <= nums.length; i++) {
            if(nums[i % nums.length] < nums[i-1]) {
                splitIndex = i;
                reverse++;
            }
        }
        if(reverse > 1) {
            return false;
        }
        // (splitIndex - 1 + nums.length) % nums.length : 0-1 -> nums.length-1
        return nums[splitIndex % nums.length] == min && nums[(splitIndex - 1 + nums.length) % nums.length] == max;
    }

    public boolean check2(int[] nums) {
        // x记录第一个相邻逆序对中，元素值较小的那个元素索引
        int n = nums.length, x = 0;
        for (int i = 1; i < n; ++i) {
            // 找到第一个相邻逆序对的位置，记录下较小的元素的位置
            if (nums[i] < nums[i - 1]) {
                x = i;
                break;
            }
        }
        // 经历上面的数组遍历，没发现逆序对，即数组本身就有序
        if (x == 0) {
            return true;
        }
        // 从第一个相邻逆序对较小元素位置开始，往后看有没有逆序对了，如果有就表明不满足题意
        for (int i = x + 1; i < n; ++i) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        // 因为上面的遍历只看到了nums[n-2]和nums[n-1]是不是相邻逆序对，还有nums[n-1]和nums[0]是不是逆序对没有看
        // 如果nums[0] >= nums[n - 1]就表明这个不是逆序对，从而表明从第一个相邻逆序对较小元素位置开始往后没有逆序对了，满足题意
        return nums[0] >= nums[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[] {6,4,7,1,3};
        Solution solution = new Solution();
        System.out.println(solution.check(nums));
    }
}
