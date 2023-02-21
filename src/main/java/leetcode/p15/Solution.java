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


    public static void main(String[] args) {
        int[] nums = {0,0,1};
        Solution solution = new Solution();
        System.out.println(solution.threeSum(nums));
    }
}
