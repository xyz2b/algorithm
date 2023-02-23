package leetcode.p454;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 将两数之和 放入 查找表（查找的内容是两数之和）
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int rst = 0;

        Map<Integer, Integer> twoSumFreq = new HashMap<>();
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                int twoSum = nums1[i] + nums2[j];
                twoSumFreq.put(twoSum, twoSumFreq.getOrDefault(twoSum, 0) + 1);
            }
        }

        for(int i = 0; i < nums3.length; i++) {
            for(int j = 0; j < nums4.length; j++) {
                int sum = 0 - (nums3[i] + nums4[j]);
                if(twoSumFreq.containsKey(sum)) {
                    rst += twoSumFreq.get(sum);
                }
            }
        }

        return rst;
    }
}
