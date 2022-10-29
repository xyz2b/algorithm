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

    public void quickSort2Ways(int[] data) {
        Random random = new Random();
        quickSort2Ways(data, 0, data.length - 1, random);
    }

    public void quickSort2Ways(int[] data, int l, int r, Random random) {
        if (l >= r) {
            return;
        }

        // random select one pivot
        int i = random.nextInt(r - l + 1) + l;
        swap(data, i, l);

        int partition = partition2(data, l , r);
        quickSort2Ways(data, l, partition - 1, random);
        quickSort2Ways(data, partition + 1, r, random);
    }

    private int partition2(int[] data, int l, int r) {
        int i = l + 1, j = r;
        while (true) {
            // 从左往右找到第一个大于等于标定点的元素
            while (i <= j && data[i] < data[l]) {   // [l + 1, i - 1] <= data[l]
                i++;
            }
            // 从右往左找到第一个小于等于标定点的元素
            while (j >= i && data[j] > data[l]) {   // [j + 1, r] >= data[l]
                j--;
            }

            // 如果左边的索引已经大于右边的索引，退出
            if(i >= j) break;

            // 交换 左边第一个大于等于标定点的元素 和 右边第一个小于等于标定点的元素
            swap(data, i, j);
            i++;
            j--;
        }

        // move l to j
        swap(data, l, j);

        return j;
    }

    public void quickSort3Ways(int[] data) {
        Random random = new Random();
        quickSort3Ways(data, 0, data.length - 1, random);
    }

    public void quickSort3Ways(int[] data, int l, int r, Random random) {
        if (l >= r) {
            return;
        }

        // random select one pivot
        int i = random.nextInt(r - l + 1) + l;
        swap(data, i, l);

        int partition = partition3(data, l , r);
        quickSort3Ways(data, l, partition - 1, random);
        quickSort3Ways(data, partition + 1, r, random);
    }

    private int partition3(int[] data, int l, int r) {
        int i = l + 1, lt = l, gt = r + 1;
        while (i < gt) {
            if (data[i] < data[l]) {    // [l + 1, lt] < data[l]
                swap(data, i, lt + 1);
                lt++;
                i++;
            } else if (data[i] > data[l]) { // [gt, r] > data[l]
                swap(data, i, gt - 1);
                gt--;
            } else { // data[i] == data[l]  // [lt + 1, i - 1] = data[l]
                i++;
            }
        }

        // move l to lt
        swap(data, l, lt);

        return lt;
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
        quickSort.quickSort3Ways(data);
        System.out.println(quickSort.toString(data));
    }
}
