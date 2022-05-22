package selectionsort;

import insertionsort.InsertionSort;

public class SelectionSort {
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        int mim = l;
        for (int i = l + 1; i <= r; i++) {
            if(nums[i] < nums[mim]) {
                mim = i;
            }
        }

        swap(nums, l, mim);

        sort(nums, l + 1, r);
    }

    private void swap(int[] nums, int src, int dst) {
        int temp = nums[src];
        nums[src] = nums[dst];
        nums[dst] = temp;
    }

    public String toString(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(data[i]).append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int[] data = {4, 453, 532, 3412312, 5364, 7, 31};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(data);
        System.out.println(selectionSort.toString(data));
    }
}
