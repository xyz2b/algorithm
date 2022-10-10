package mergesort;

import java.util.Arrays;

public class MergeSort {
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
        mergeSort.sort(data);
        System.out.println(mergeSort.toString(data));
    }
}
