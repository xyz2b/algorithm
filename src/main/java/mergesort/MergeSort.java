package mergesort;

import java.util.Arrays;

public class MergeSort {
    public void sort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    private void sort(int[] data, int l, int r) {
        if (l >= r) {
            return;
        }

        int medium = (r - l) / 2 + l;
        sort(data, l, medium);
        sort(data, medium + 1, r);
        merge(data, l, r, medium);
    }

    private void merge(int[] data, int l, int r, int medium) {
        int[] temp = Arrays.copyOfRange(data, l, r + 1);

        int j = l;
        int k = medium + 1;
        for (int i = l; i <= r; i++) {
            if (j > medium) {
                data[i] = temp[k - l];
                k++;
            } else if (k > r) {
                data[i] = temp[j - l];
                j++;
            } else if (temp[j - l] < temp[k - l]) {
                data[i] = temp[j - l];
                j++;
            } else {
                data[i] = temp[k - l];
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
