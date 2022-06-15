package leetcode.p719;

import java.util.Random;

public class Solution2 {
    public int smallestDistancePair(int[] nums, int k) {
        int length = nums.length;

        int n = (int) ((((double)length - 1) + 1) * (((double)length - 1)/2));

        int[] rst = new int[n];

        int index = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                rst[index] = Math.abs(nums[j] - nums[i]);
                index++;
            }
        }

        return quickSort(rst, k);
    }

    public int quickSort(int[] data, int k) {
        int index = quickSort(data, 0, data.length - 1, k - 1);
        return data[index];
    }

    private int quickSort(int[] data, int l, int r, int k) {
        // random select one pivot
        int i = new Random().nextInt(r - l + 1) + l;
        swap(data, i, l);

        int partition = partition(data, l , r);
        if (partition == k) {
            return k;
        } else if (partition < k) {
            return quickSort(data, partition + 1, r, k);
        } else {
            return quickSort(data, l, partition - 1, k);
        }
    }

    private int partition(int[] data, int l, int r) {
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (data[i] < data[l]) {
                // swap j+1 and i
                swap(data, i, j+1);

                // move j to j+1
                j++;
            }
        }

        // move l to j
        swap(data, l, j);

        return j;
    }

    private void swap(int[] data, int src, int dst) {
        int temp = data[src];
        data[src] = data[dst];
        data[dst] = temp;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] data = {62, 100, 4};
        int k = 2;
        System.out.println(solution.smallestDistancePair(data, k));
    }
}
