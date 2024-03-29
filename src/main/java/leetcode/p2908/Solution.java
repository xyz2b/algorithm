package leetcode.p2908;

import java.util.Map;

class Solution {
    public int minimumSum(int[] nums) {
        int ret = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[j] > nums[i] && nums[j] > nums[k]) {
                        if (nums[i] + nums[j] + nums[k] < ret) {
                            ret = nums[i] + nums[j] + nums[k];
                        }
                    }
                }
            }
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    // 前缀数组的最小值
    // 后缀数组的最小值
    // 第i个元素，比i前面的元素最小值都大，比i后面的元素最小值都大，就是山形
    public int minimumSum2(int[] nums) {
        int ret = Integer.MAX_VALUE;

        int[] left = new int[nums.length];
        left[0] = nums[0];
        // 从左向右遍历，求前缀数组中的最小值（第i元素之前的数组元素中的最小值）
        for(int i = 1; i < nums.length; i++) {
            left[i] = Math.min(nums[i-1], left[i-1]);
        }

        int right = nums[nums.length - 1];
        for(int i = nums.length - 2;  i >= 0; i--) {
            // 第i个元素 比 前i个元素的最小值大，然后比后面元素的最小值大，就组成了三元组
            if(left[i] < nums[i] && nums[i] > right) {
                ret = Math.min(ret, left[i] + nums[i] + right);
            }

            // 从右往左遍历，计算第i个元素之后的数组元素中的最小值
            right = Math.min(nums[i], right);
        }


        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    public int minimumSum3(int[] nums) {
        int ret = Integer.MAX_VALUE;

        // 求前缀数组最小值，第i元素(包含)之前的所有元素中的最小值
        int[] left = new int[nums.length];
        left[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            left[i] = Math.min(nums[i], left[i-1]);
        }

        // 求后缀数组最小值，第i个元素(包含)之后的所有元素中的最小值
        int[] right = new int[nums.length];
        right[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length - 2; i >= 0; i--) {
            right[i] = Math.min(nums[i], right[i+1]);
        }

        // 第i个元素，比i前面的元素最小值都大，比i后面的元素最小值都大，就是山形
        for(int i = 1; i < nums.length - 1; i++) {
            if(nums[i] > left[i-1] && nums[i] > right[i+1]) {
                ret = Math.min(ret, left[i-1] + nums[i] + right[i+1]);
            }
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{8,6,1,5,3};
        System.out.println(solution.minimumSum3(nums));
    }
}