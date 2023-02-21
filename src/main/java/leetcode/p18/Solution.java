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

    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        Solution solution = new Solution();
        System.out.println(solution.fourSum(nums, target));
    }
}
