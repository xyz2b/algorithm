package leetcode.p88;

import java.util.Arrays;

public class Solution {
    // 归并排序，归并的过程
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }

        if(m == 0) {
            for(int i = 0; i < n; i++) {
                nums1[i] = nums1[i];
            }
            return;
        }

        // nums1存储的是要合并的源数组，同时也是需要返回的结果，所以复制一份它的内容
        int[] temp = Arrays.copyOf(nums1, m);

        // index1指向temp数组，index2指向nums2数组
        // 每次将两个数组中值较小的元素放入返回数组nums1对应位置上
        int index1 = 0, index2 = 0;
        for(int i = 0; i < m + n; i++) {
            if(index1 >= m) {
                nums1[i] = nums2[index2];
                index2++;
            } else if(index2 >= n) {
                nums1[i] = temp[index1];
                index1++;
            } else if(temp[index1] > nums2[index2]) {
                nums1[i] = nums2[index2];
                index2++;
            } else {    // temp[index1] <= nums2[index2]
                nums1[i] = temp[index1];
                index1++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = new int[] {2,5,6};
        int n = 3;
        Solution solution = new Solution();
        solution.merge(nums1, m, nums2, n);
    }
}
