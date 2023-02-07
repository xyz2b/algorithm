package leetcode.p209;

// 滑动窗口
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = -1; // nums[l, r]为滑动窗口，初始时窗口内没有任务元素

        int res = nums.length + 1;

        int sum = 0;

        while (l < nums.length) {
            if(r + 1 < nums.length && sum < target) {   // 小于target，移动r // 如果r已经到了数组边界，就不能再移动了
                r++;
                sum += nums[r];
            } else {    // 大于target，移动l // 如果r已经到数组边界了，此时只能移动l了
                sum -= nums[l];
                l++;
            }

            if(sum >= target) {
                res = Math.min(res, r - l + 1);
            }
        }

        return res == nums.length + 1 ? 0 : res;
    }

    public static void main(String[] args) {
        int target = 11;
        int[] nums = new int[]{1,1,1,1,1,1,1,1};
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(target, nums));
    }
}
