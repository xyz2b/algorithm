package leetcode.p1089;

import java.util.Arrays;

public class Solution {
    // copy一个临时数组，作为原数组的备份
    // 然后去遍历临时数组，再把临时数组里的数据copy到原数组中，这时需要一个指针指向临时数据，一个指针指向原数组，临时数组的指针是一格一格变化的，原数组指针如果遇到零就要多走一格，不然也是一格格变化的
    // 如果遇到零，就需要copy两个0到原数组中，注意判断原数组到达边界的条件
    public void duplicateZeros(int[] arr) {
        int[] temp = Arrays.copyOf(arr, arr.length);

        for (int i = 0, j = 0; j < arr.length; i++, j++) {
            if (temp[i] == 0) {
                arr[j] = 0;
                j++;
                if (j >= arr.length) {
                    break;
                }
                arr[j] = 0;
            } else {
                arr[j] = temp[i];
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
        Solution solution = new Solution();
        int[] data = {1,0,2,3,0,4,5,0};
        int[] data1 = {1, 2, 3};
        solution.duplicateZeros(data);
        System.out.println(solution.toString(data));
    }
}
