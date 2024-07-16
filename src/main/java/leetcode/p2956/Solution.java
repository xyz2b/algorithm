package leetcode.p2956;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums1Count = new HashMap<>();
        Map<Integer, Integer> nums2Count = new HashMap<>();

        int n = nums1.length;
        int m = nums2.length;
        for(int i = 0; i < n; i++) {
             nums1Count.put(nums1[i], nums1Count.getOrDefault(nums1[i], 0) + 1);
        }

        for(int i = 0; i < m; i++) {
            nums2Count.put(nums2[i], nums2Count.getOrDefault(nums2[i], 0) + 1);
        }

        int ret1 = 0;
        for(int num : nums1Count.keySet()) {
            if(nums2Count.containsKey(num)) {
                ret1 += nums1Count.get(num);
            }
        }

        int ret2 = 0;
        for(int num : nums2Count.keySet()) {
            if(nums1Count.containsKey(num)) {
                ret2 += nums2Count.get(num);
            }
        }

        return new int[] {ret1, ret2};
    }
}
