package leetcode.p1089;

import java.util.*;

public class Solution2 {
    public void duplicateZeros(int[] arr) {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i ++) {
            if (!queue.isEmpty()) {
                int n = queue.poll();
                if (n == 0) {
                    queue.offer(arr[i]);
                    arr[i] = 0;

                    i++;
                    if (i >= arr.length) {
                        break;
                    }
                    queue.offer(arr[i]);
                    arr[i] = 0;
                } else {
                    queue.offer(arr[i]);
                    arr[i] = n;
                }
                continue;
            }

            if (arr[i] == 0) {
                i++;
                if (i >= arr.length) {
                    break;
                }
                queue.offer(arr[i]);
                arr[i] = 0;
            }

        }
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
        Solution2 solution = new Solution2();
        int[] data = {1,0,2,3,0,4,5,0};
        int[] data1 = {1, 2, 3};
        solution.duplicateZeros(data1);
        System.out.println(solution.toString(data1));
    }
}
