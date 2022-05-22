package binarysearch;

import quicksort.QuickSort;

public class BinarySearch {
    public int search(int[] nums, int v) {
        return search(nums, v, 0, nums.length - 1);
    }

    public int search(int[] nums, int target, int l, int r) {
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

    public static void main(String[] args) {
        int[] data = {4, 453, 532, 3412312, 5364, 7, 31};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(data);
        System.out.println(quickSort.toString(data));

        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.search(data, 5364));
    }
}
