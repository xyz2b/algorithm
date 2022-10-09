package quicksort;

import java.util.Random;

public class QuickSort {

    public void quickSort(int[] data) {
        Random random = new Random();
        quickSort(data, 0, data.length - 1, random);
    }

    public void quickSort(int[] data, int l, int r, Random random) {
        if (l >= r) {
            return;
        }

        // random select one pivot
        int i = random.nextInt(r - l + 1) + l;
        swap(data, i, l);

        int partition = partition(data, l , r);
        quickSort(data, l, partition - 1, random);
        quickSort(data, partition + 1, r, random);
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
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(data);
        System.out.println(quickSort.toString(data));
    }
}
