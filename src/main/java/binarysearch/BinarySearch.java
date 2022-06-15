package binarysearch;

import quicksort.QuickSort;

import java.util.Arrays;

public class BinarySearch {
    public int search(int[] nums, int v) {
        return search(nums, v, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int l, int r) {
        if (l > r) {
            return -1;
        }

        int medium = (r - l) / 2 + l;

        if (nums[medium] == target) {
            return medium;
        }

        if (nums[medium] > target) {
            return search(nums, target, l, medium - 1);
        } else { // nums[medium] < target
            return search(nums, target, medium + 1, r);
        }
    }

    // 在nums数组中搜索第k小的元素
    // 正常来说没有使用这种算法来计算的，数组排完序之后，第k小的元素就是数组索引为k-1的值
    // 这里的算法只是作为其他问题算法的子算法的样例
    public int searchK(int[] nums, int k) {
        // 前提：1.数组有序，2.在left和right中一定存在第k小的元素
        int left = 0, right = nums.length - 1;

        // 当 left 与 right 重合时得到答案
        while (left < right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];
            // 计算 nums数组 中有多少个小于等于midNum的元素
            int count = countLessEquals(nums, midNum);

            // 如果小于等于 midNum 的个数严格小于 k 个，说明 midNum 太小了，需要向更大的元素方向搜索（数组是有序的）
            // 所以下一轮搜索区间为 [mid + 1, right]
            if (count < k) {
                left = mid + 1;
            } else { // 下一轮搜索区间为 [0, left]
                right = mid;
            }
        }

        return nums[left];
    }

    private int countLessEquals(int[] nums, int threshold) {
        int count = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] <= threshold) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] data = {4, 453, 532, 3412312, 5364, 7, 31};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(data);
        System.out.println(quickSort.toString(data));

        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.search(data, 5364));

        System.out.println(binarySearch.searchK(data, 7));
    }
}
