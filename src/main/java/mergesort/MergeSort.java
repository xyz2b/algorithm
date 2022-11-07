package mergesort;

import java.util.Arrays;

public class MergeSort {
    // 自顶向下的归并排序，一直接拆分数组，到最后拆分出来的数组元素只有一个就不用排序了，然后进行merge
    public void sort(int[] data) {
        int[] temp = new int[data.length];
        sort(data, 0, data.length - 1, temp);
    }

    private void sort(int[] data, int l, int r, int[] temp) {
        if (l >= r) {
            return;
        }

        int medium = (r - l) / 2 + l;
        sort(data, l, medium, temp);
        sort(data, medium + 1, r, temp);

        // 如果data[medium] <= data[medium + 1]，说明两边数组连起来已经有序了不需要再merge了
        if(data[medium] > data[medium + 1]) {
            merge(data, l, medium, r, temp);
        }
    }

    // 自底向上的归并排序，直接从底进行merge，从元素个数为1的相邻子数组进行merge，merge完就是元素个数为2的数组内是有序的，然后到元素个数为2的相邻子数组进行merge，一致merge到元素个数为n/2的相邻子数组
    private void sort2(int[] data) {
        int[] temp = new int[data.length];
        int n = data.length;

        // sz是需要merge的数组的长度，从相邻长度为1的数组开始merge
        // 第一次merge：merge开始的两个数组的索引为0和1，下一个需要进行merge的两个数组的索引为2和3，依次类推
        // 第二次merge：merge开始的两个数组的索引为0和2，下一个需要进行merge的两个数组的索引为4和6，依次类推
        for(int sz = 1; sz < n; sz += sz) {

            // 需要merge的两个数组的区间：[i, i + sz - 1] - [i + sz, i + sz + sz - 1]
            // i + sz < n表明需要merge的两个数组中，第二个数组中还有元素
            // 但是可能不足i + sz + sz - 1索引处，这个需要注意，所以下面右区间的尾端点，使用的是i + sz + sz - 1和n-1的最小值，避免越界
            // i是需要merge的两个相邻数组的起始索引，每次递增 2sz，到下一个需要merge的两个相邻数组的起始索引
            for(int i = 0; i + sz < n; i += sz + sz) {
                // medium应该是左区间的尾端点
                merge(data, i, Math.min(i + sz + sz - 1, n - 1), i + sz - 1, temp);
            }
        }
    }

    private void merge(int[] data, int l, int r, int medium, int[] temp) {
        System.arraycopy(data, l, temp, l, r - l + 1);

        int j = l;
        int k = medium + 1;
        for (int i = l; i <= r; i++) {
            if (j > medium) {
                data[i] = temp[k];
                k++;
            } else if (k > r) {
                data[i] = temp[j];
                j++;
            } else if (temp[j] <= temp[k]) {
                data[i] = temp[j];
                j++;
            } else {
                data[i] = temp[k];
                k++;
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
        int[] data = {4, 453, 532, 3412312, 5364, 7, 31};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort2(data);
        System.out.println(mergeSort.toString(data));
    }
}
