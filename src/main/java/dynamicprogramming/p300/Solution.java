package dynamicprogramming.p300;

import java.util.Arrays;

/**
 * longest-increasing-subsequence(最长上升子序列)
 *
 * LIS(i)表示以i个数字为结尾的最长上升子序列的长度
 * LIS(i)表示[0...i]的范围内，选择数字nums[i]可以获得的最长上升子序列的长度（必须选择nums[i]，之前的问题都是考虑[0...i]范围的内的数字，对于边界i可以选可以不选，但是对于当前问题边界i必须选）
 *
 * LIS(i) = max(1 + LIS(j) if nums[i] > nums[j])
 *         j < i
 *
 * nums   [10 9 2 5 3 7 101 18]
 * LIS(i) [1  1 1 1 1 1  1  1] (初始时，每个数字的最长上升子序列就是自己，长度为1)
 * LIS(i) [1  1 1 2 2 3  4  4]
 * 10前面没有数字，所以上升子序列就是它自己
 * 9前面10比它大，所以也构不成上升子序列
 * 2前面9和10都比它大，所以也构不成上升子序列
 * 5前面9和10比它大，但是2比它小，所以2、5可以构成上升子序列，即把5放到2后面就可以构成一个新的上升子序列，这个新的上升子序列的长度应该是以2结尾构成的最长上升子序列的长度+5自身，所以以5结尾构成的最长上升子序列的长度为2
 * ...
 * 101前面的数字都比它小，把101放到10后面可以构成一个新的上升子序列，长度为2；...；把101放到7后面可以构成一个新的上升子序列，长度为4。最长的上升子序列的长度为4
 * ...
 * */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;

        // lis[i] 表示以 nums[i] 为结尾的最长上升子序列的长度
        int[] lis = new int[nums.length];
        Arrays.fill(lis, 1);

        for(int i = 1; i < nums.length; i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(nums[i] > nums[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        return Arrays.stream(lis).max().getAsInt();
    }
}
