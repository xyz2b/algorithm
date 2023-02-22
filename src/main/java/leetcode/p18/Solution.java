package leetcode.p18;

import java.util.*;

public class Solution {
    // threeSum再套一层循环，同时需要去重
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> rstSet = new HashSet<>();

        for(int k = 0; k < nums.length; k++) {
            long threeSum = target - nums[k];
            for(int i = k + 1; i < nums.length; i++) {
                long twoSum = threeSum - nums[i];
                // twoSum
                Map<Long, Integer> numToIndex = new HashMap<Long, Integer>();
                for(int j = i + 1; j < nums.length; j++) {
                    long t = twoSum - nums[j];
                    if(numToIndex.containsKey(t)) {
                        List<Integer> r = new ArrayList<>(Arrays.asList(nums[k], nums[i], nums[j], (int) t));
                        r.sort(Comparator.naturalOrder());
                        rstSet.add(r);
                    } else {
                        numToIndex.put((long)nums[j], j);
                    }
                }
            }
        }

        return new ArrayList<List<Integer>>(rstSet);
    }

    // 双指针，threeSum再加一重循环
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> rst = new ArrayList<>();

        // 排序
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue; // 去重

            for(int j = i + 1; j < nums.length; j++) {
                if(j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重

                // 双指针
                int l = j + 1, r = nums.length - 1;
                while (l < r) {
                    long sum = (long) nums[i] + nums[j] + nums[l] + nums[r];
                    if(sum > target) {
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else { // sum == target
                        rst.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[l], nums[r])));
                        while (l < r && nums[l] == nums[l + 1]) l++;    // 去重
                        while (l < r && nums[r] == nums[r - 1]) r--;    // 去重
                        l++;
                        r--;
                    }
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        int target = -294967296;
        Solution solution = new Solution();
        System.out.println(solution.fourSum2(nums, target));
    }
}
