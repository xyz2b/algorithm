package leetcode.p16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // 排序+双指针
    public int threeSumClosest(int[] nums, int target) {
        int rst = 0;

        // 三数之和 和 target的距离，找距离最接近的
        int dif = Integer.MAX_VALUE;

        // 排序之后使用双指针
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue; // 去重

            // 双指针
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if(sum > target) {
                    if(Math.abs(sum - target) < dif) {
                        rst = sum;
                        dif = Math.abs(sum - target);
                    }
                    // 去重
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    r--;
                } else if (sum < target) {
                    if(Math.abs(sum - target) < dif) {
                        rst = sum;
                        dif = Math.abs(sum - target);
                    }
                    // 去重
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    l++;
                } else { // sum == target
                    return target;
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        int target = 1;
        Solution solution = new Solution();
        System.out.println(solution.threeSumClosest(nums, target));
    }
}

