package leetcode.p3132;

import java.util.Arrays;

public class Solution {
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // i 是 nums1 中的索引
        // 结果数组中第一个元素一定是 nums1 0/1/2 索引其中一个元素，因为nums2和nums1就差两个元素，并且有序
        // 遍历这三个索引，判断剩下的nums2数组中的数和nums1中剩余的数的差值，是否都和 nums1中这三个索引的值与nums2中第一个索引的值 的差值相等
        for(int i : new int[]{2,1,0}) { // 要最小的结果，所以从2开始遍历，因为排过序，所以2索引的数值最大，因而差值最小
            // nums1和nums2中第一个元素的差值
            int diff = nums2[0] - nums1[i];

            // 从第二个元素开始判断
            int left = i + 1;
            int right = 1;
            while (left < m && right < n) {
                if(nums2[right] - nums1[left] == diff) {    // 相等就递增nums2的索引
                    right++;
                }
                left++;
            }

            if(right == n) {
                return diff;
            }
        }
        return 0;
    }
}
