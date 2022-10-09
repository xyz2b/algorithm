package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 最大堆，堆中的每个节点的值都大于其所有的子节点的值（完全二叉树）
public class MaxHeap {
    private List<Integer> list;

    public MaxHeap() {
        list = new ArrayList<>();
    }

    public MaxHeap(int capacity) {
        list = new ArrayList<>(capacity);
    }

    public MaxHeap(List<Integer> list) {
        this.list = new ArrayList<>(list);

        // 找到最后一个非叶子结点，即最底层最后一个叶子节点的父节点
        int x = parent(getSize() - 1);
        for(int i = x; i >= 0; i--) {
            // 下沉
            shiftDown(i);
        }
    }

    // 取出堆中最大元素并替换成元素e
    public int replace(int e) {
        int rst = findMax();
        list.set(0, e);
        shiftDown(0);
        return rst;
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int findMax() {
        return list.get(0);
    }

    public void add(int e) {
        // 直接添加到数组末尾
        list.add(e);
        // 执行上浮操作
        shiftUp(getSize() - 1);
    }

    // 与父节点比较，如果大于父节点就交换，一直交换到根节点或者小于父节点为止
    private void shiftUp(int x) {
        // 根节点没有父节点，不需要执行上浮操作
        while (x > 0 && list.get(parent(x)) < list.get(x)) {
            swap(parent(x), x);
            x = parent(x);
        }
    }

    public int poll() {
        int rst = findMax();
        // 将最后一个节点和根节点交换
        swap(0, getSize() - 1);
        // 删除交换后的最后一个节点，即最大值
        list.remove(getSize() - 1);
        // 执行下沉操作
        shiftDown(0);
        return rst;
    }

    private void shiftDown(int x) {
        while (leftChild(x) < getSize()) {
            // list.get(max)是左右孩子中最大值
            int max = leftChild(x);
            if(rightChild(x) < getSize() && list.get(leftChild(x)) < list.get(rightChild(x))) {
                max = rightChild(x);
            }

            // 如果根节点小于左右孩子最大值，则进行交换
            if(list.get(x) < list.get(max)) {
                swap(x, max);
                x = max;
            } else {
                break;
            }
        }
    }

    private void swap(int x, int y) {
        int tmp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, tmp);
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
        int n = 100000;
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(random.nextInt(Integer.MAX_VALUE));
        }

        MaxHeap maxHeap = new MaxHeap(list);
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = maxHeap.poll();
        }

        for(int i = 1; i < n; i++) {
            if(arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("test Heap completed");
    }
}
