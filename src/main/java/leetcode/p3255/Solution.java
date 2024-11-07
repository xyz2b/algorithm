package leetcode.p3255;

import java.util.Arrays;

public class Solution {
    public int[] resultsArray(int[] nums, int k) {
        // cnt 统计当前遍历元素之前包含当前元素 连续且上升 的元素数目
        // 如果 i==0 或者 nums[i] == nums[i-1], cnt++
        // 如果 nums[i] != nums[i-1], cnt = 1
        // 遍历过程中，如果 cnt >= k，则当前连续且上升的最大元素为 nums[i]

        int n = nums.length;
        int[] ret = new int[n - k + 1];
        Arrays.fill(ret, -1);
        int cnt = 0;
        for(int i = 0; i < nums.length; i++) {
            if(i == 0) {
                cnt++;
            } else if (nums[i] == nums[i - 1] + 1) {
                cnt++;
            } else {
                cnt = 1;
            }

            if(cnt >= k) {
                ret[i - k + 1] = nums[i];
            }
        }
        return ret;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3,2,5};
        int k = 3;
        Solution solution = new Solution();
        System.out.println(solution.resultsArray(nums, k).length);
    }
}
