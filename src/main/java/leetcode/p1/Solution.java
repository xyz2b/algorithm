package leetcode.p1;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            int r = target - nums[i];
            if(freq.containsKey(r)) {
                return new int[] {freq.get(r), i};
            }
            freq.put(nums[i], i);
        }

        return new int[0];
    }

}