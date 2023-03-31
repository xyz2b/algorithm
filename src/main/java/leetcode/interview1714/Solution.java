package leetcode.interview1714;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Solution {
    // 最大堆
    public int[] smallestK(int[] arr, int k) {
        int[] rst = new int[k];
        if (k == 0) return rst;

        Queue<Integer> queue = new PriorityQueue<>((Integer i1, Integer i2) -> {
            return i2 - i1;
        });

        for (int i = 0; i < arr.length; i++) {
            if (queue.size() == k) {
                if (arr[i] < queue.peek()) {
                    queue.poll();
                    queue.add(arr[i]);
                }
            } else {
                queue.add(arr[i]);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            rst[index] = queue.poll();
            index++;
        }

        return rst;
    }

    private Random random = new Random();
    // 快排
    public int[] smallestK2(int[] arr, int k) {
        if(k == 0) return new int[0];
        smallestK(arr, 0, arr.length - 1, k);
        return Arrays.copyOf(arr, k);
    }

    private void smallestK(int[] arr, int l, int r, int k) {
        // 随机选取标定点
        int index = random.nextInt(r - l + 1) + l;
        swap(arr, index, l);

        int p = partition2(arr, l, r);
        if(p == k - 1) {
            return;
        } else if (p < k - 1) {
            smallestK(arr, p + 1, r, k);
            return;
        } else { // p > k - 1
            smallestK(arr, l, p - 1, k);
            return;
        }
    }

    private int partition2(int[] arr, int l, int r) {
        // [l+1, i) < arr[l]
        int i = l + 1;
        // (j, r] >= arr[l]
        int j = r;

        while (true) {
            while (i <= j && arr[i] < arr[l]) {
                i++;
            }
            while (i <= j && arr[j] > arr[l]) {
                j--;
            }

            if(i >= j) break;

            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, l, j);
        return j;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}