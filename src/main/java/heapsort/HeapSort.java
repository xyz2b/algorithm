package heapsort;

import heap.MaxHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeapSort {

    public void sort(int[] data) {
        // heapify
        // 找到最后一个非叶子结点，即最底层最后一个叶子节点的父节点，对这个节点往前一直到根节点的所有节点执行shiftDown操作
        int x = parent(data.length - 1);
        for(int i = x; i >= 0; i--) {
            // 下沉
            shiftDown(data, i, data.length);
        }

        // sort
        for(int i = data.length - 1; i >= 0; i--) {
            // 将最大值放到数组末尾，将数组末尾的值交换到堆的根节点上
            swap(data, 0, i);
            // 对交换后的根节点执行shiftDown，数据范围为[0, i)
            shiftDown(data, 0, i);
        }
    }

    // 对data[0, n)所形成的的最大堆中索引为x的元素，执行shiftDown
    private void shiftDown(int[] data, int x, int n) {
        while (leftChild(x) < n) {
            // list.get(max)是左右孩子中最大值
            int max = leftChild(x);
            if(rightChild(x) < n && data[leftChild(x)] < data[rightChild(x)]) {
                max = rightChild(x);
            }

            // 如果根节点小于左右孩子最大值，则进行交换
            if(data[x] < data[max]) {
                swap(data, x, max);
                x = max;
            } else {
                break;
            }
        }
    }

    private void swap(int[] data, int x, int y) {
        int tmp = data[x];
        data[x] = data[y];
        data[y] = tmp;
    }

    private int leftChild(int x) {
        return 2 * x + 1;
    }

    private int rightChild(int x) {
        return 2 * x + 2;
    }

    private int parent(int x) {
        return (x - 1) / 2;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Random random = new Random();
        int[] list = new int[n];
        for(int i = 0; i < n; i++) {
            list[i] = random.nextInt(Integer.MAX_VALUE);
        }

        HeapSort heapSort = new HeapSort();
        heapSort.sort(list);

        for(int i = 1; i < n; i++) {
            if(list[i - 1] > list[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("test Heap completed");
    }
}
