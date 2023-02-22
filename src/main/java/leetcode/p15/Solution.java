package leetcode.p15;

import java.util.*;

public class Solution {
    // twoSum再套一层循环，同时需要去重
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> rstSet = new HashSet<>();

        for(int i = 0; i < nums.length; i++) {
            int twoSum = 0 - nums[i];
            // twoSum
            Map<Integer, Integer> numToIndex = new HashMap<Integer, Integer>();
            for(int j = i + 1; j < nums.length; j++) {
                int target = twoSum - nums[j];
                if(numToIndex.containsKey(target)) {
                    List<Integer> r = new ArrayList<>(Arrays.asList(nums[i], nums[j], target));
                    r.sort(Comparator.naturalOrder());
                    rstSet.add(r);
                } else {
                    numToIndex.put(nums[j], j);
                }
            }
        }

        return new ArrayList<List<Integer>>(rstSet);
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();

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
                    r--;
                } else if (sum < 0) {
                    l++;
                } else { // sum == 0
                    rst.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    // 去重
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1};
        Solution solution = new Solution();
        System.out.println(solution.threeSum(nums));
    }
}
