package leetcode.p2740;

import java.util.Arrays;

public class Solution {
    public int findValueOfPartition(int[] nums) {
        // 判断哪两个元素差值最小
        Arrays.sort(nums);

        int ret = Integer.MAX_VALUE;
        for(int i = 1; i < nums.length; i++) {
            ret = Math.min(ret, nums[i] - nums[i-1]);
        }

        return ret;
    }
}
