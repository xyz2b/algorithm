package leetcode.p1051;

import java.util.Arrays;
import java.util.Random;

// 排序问题
class Solution {
    // 快排
    public int heightChecker(int[] heights) {
        int[] sortTemp = Arrays.copyOf(heights, heights.length);

        // 先排序（快排）
        quickSort(sortTemp);

        int result = 0;
        // 再对比
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != sortTemp[i]) {
                result++;
            }
        }

        return result;
    }

    private void quickSort(int[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private void quickSort(int[] data, int l, int r) {
        if (l >= r) {
            return;
        }

        int index = new Random().nextInt(r - l + 1) + l;
        swap(data, l, index);

        int p = partition(data, l, r);
        quickSort(data, l, p - 1);
        quickSort(data, p + 1, r);
    }

    private int partition(int[] data, int l, int r) {
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (data[i] < data[l]) {
                swap(data, i, j + 1);
                j++;
            }
        }
        swap(data, l, j);
        return j;
    }


    private void swap(int[] data, int src, int dst) {
        int temp = data[src];
        data[src] = data[dst];
        data[dst] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = {1, 1, 4, 2, 1, 3};
        System.out.println(solution.heightChecker(heights));
    }
}
