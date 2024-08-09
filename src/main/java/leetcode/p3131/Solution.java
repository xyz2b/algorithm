package leetcode.p3131;

import java.util.*;

public class Solution {
    public int addedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int ret = 0;
        // 排序之后找到第一个不相等的数值，计算差值即为答案，如果都相等就是0
        for(int i = 0; i < nums1.length; i++) {
            ret = nums2[i] - nums1[i];
            if(ret != 0) {
                break;
            }
        }

        return ret;
    }
}
