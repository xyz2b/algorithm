package bubblesort;

import quicksort.QuickSort;

public class BubbleSort {
    public void sort(int[] data) {
        for(int i = 0; i < data.length - 1; i++) {
            // data[data.length - i, n]已排序
            // 通过冒泡在 arr[data.length - i - 1]位置放上合适的元素
            for(int j = 0; j < data.length - i - 1; j++) {
                if(data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                }
            }
        }
    }

    public void sort2(int[] data) {
        for(int i = 0; i < data.length - 1; i++) {
            // 如果该轮循环没有交换任何元素，表示数组前面已经是有序了(data[0, data.length - i - 1])，直接退出外层循环即可
            boolean isSwaped = false;
            // data[data.length - i, n]已排序
            // 通过冒泡在 arr[data.length - i - 1]位置放上合适的元素
            for(int j = 0; j < data.length - i - 1; j++) {
                if(data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                    isSwaped = true;
                }
            }
            if(!isSwaped) {
                break;
            }
        }
    }

    public void sort3(int[] data) {
        // i是代表已经排好序的元素个数，已经排好序的位置，内层循环就不需要考虑了
        for(int i = 0; i < data.length - 1; ) {
            // 最后一个交换的元素位置，如果它为0，说明数组中没有元素发生交换，已经有序了
            // 如果经历一次内层循环，后面有不止一个元素已经是排好序了，那么下次内层循环就不需要考虑了，所以将i直接置为后面已经排好序的元素个数即可
            int lastSwapIndex = 0;
            // data[data.length - i, n]已排序
            // 通过冒泡在 arr[data.length - i - 1]位置放上合适的元素
            for(int j = 0; j < data.length - i - 1; j++) {
                if(data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                    lastSwapIndex = j + 1;
                }
            }
//            if(lastSwapIndex == 0) {
//                break;
//            }
            // lastSwapIndex == 0，i == data.length，循环判断条件会直接失败退出，所以不需要上面的判断了
            i = data.length - lastSwapIndex;
        }
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
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort2(data);
        System.out.println(bubbleSort.toString(data));
    }
}
