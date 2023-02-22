package leetcode.p16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int rst = 0;

        int dif = Integer.MAX_VALUE;

        // 排序之后使用双指针
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i - 1]) continue; // 去重

            // 双指针
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if(sum > 0) {
                    if(Math.abs(sum - target) < dif) {
                        rst = sum;
                        dif = Math.abs(sum - target);
                    }
                    // 去重
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    r--;
                } else if (sum < 0) {
                    if(Math.abs(sum - target) < dif) {
                        rst = sum;
                        dif = Math.abs(sum - target);
                    }
                    // 去重
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    l++;
                } else { // sum == 0
                    return target;
                }
            }
        }
        return rst;
    }
}

