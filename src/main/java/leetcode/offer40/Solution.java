package leetcode.offer40;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Solution {
    // 快排
    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr.length == 0) {
            return arr;
        }

        selectK(arr, 0, arr.length - 1, k);

        return Arrays.copyOf(arr, k);
    }

    private int selectK(int[] arr, int l, int r, int k) {
        if(l >= r) {
            return arr[l];
        }

        int i = new Random().nextInt(r - l + 1) + l;
        swap(arr, l, i);

        int p = partition(arr, l, r);
        if(p == k - 1) {
            return arr[p];
        } else if (k - 1 < p) {
            return selectK(arr, l, p - 1, k);
        } else {
            return selectK(arr, p + 1, r, k);
        }
    }

    private void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    private int partition(int[] arr, int l, int r) {
        int k = l;
        for(int i = l + 1; i <= r; i++) {
            if(arr[i] < arr[l]) {
                swap(arr, i, k + 1);
                k++;
            }
        }
        swap(arr, k ,l);
        return k;
    }

    // 最大堆
    public int[] getLeastNumbers1(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);

        for(int i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }

        for(int i = k; i < arr.length; i++) {
            if(!pq.isEmpty() && arr[i] < pq.peek()) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }

        int[] rst = new int[k];
        for(int i = 0; i < rst.length; i++) {
            rst[i] = pq.poll();
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] data = new int[] {0,0,0,2,0,5};
        int k = 0;
        Solution solution = new Solution();
        solution.getLeastNumbers1(data, k);
    }
}
