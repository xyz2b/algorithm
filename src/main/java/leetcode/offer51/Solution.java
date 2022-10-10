package leetcode.offer51;

import java.util.Arrays;

public class Solution {
    // 归并排序的思想
    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        return mergeSort(nums, 0, nums.length - 1, temp);
    }

    private int mergeSort(int[] nums, int l, int r, int[] temp) {
        if(l >= r) {
            return 0;
        }

        int rst = 0;

        int mid = (r - l) / 2 + l;

        rst += mergeSort(nums, l, mid, temp);
        rst += mergeSort(nums, mid + 1, r, temp);

        if(nums[mid] > nums[mid + 1]) {
            rst += merge(nums, l, mid, r, temp);
        }

        return rst;
    }

    private int merge(int[] nums, int l, int mid, int r, int[] temp) {
        System.arraycopy(nums, l, temp, l, r - l + 1);

        int rst = 0;
        int j = l, k = mid + 1;
        for(int i = l; i <= r; i++) {
            if(j > mid) {
                nums[i] = temp[k];
                k++;
            } else if (k > r) {
                nums[i] = temp[j];
                j++;
            } else if(temp[j] <= temp[k]) {
                nums[i] = temp[j];
                j++;
            } else {    // tmp[j - l] > tmp[k - l]，只有左边大于右边才有逆序对，同时 左边当前遍历元素一直到mid位置的元素 都是 右边目前遍历元素的 逆序对
                rst += mid - j + 1;
                nums[i] = temp[k];
                k++;
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] data = new int[] {7,5,6,4};
        Solution solution = new Solution();
        System.out.println(solution.reversePairs(data));
    }
}
