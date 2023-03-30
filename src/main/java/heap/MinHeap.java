package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 最小堆，堆中的每个节点的值都小于其所有的子节点的值（完全二叉树）
public class MinHeap{

    private List<Integer> data;

    public MinHeap() {
        data = new ArrayList<>(15);
    }

    public MinHeap(int size) {
        data = new ArrayList<>(size);
    }

    public MinHeap(List<Integer> data) {
        this.data = new ArrayList<>(data);
        heapify();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    private void heapify() {
        // 找到倒数第二层最后一个元素，即最后一层最后一个元素的父元素
        int index = parent(data.size() - 1);
        // 从上面的节点一直到根节点，都做shiftDown操作
        for(int i = index; i >= 0; i--) {
            shiftDown(i);
        }
    }

    public void add(int num) {
        // 添加到最后一个元素之后，然后执行shiftUp
        data.add(num);
        shiftUp(data.size() - 1);
    }

    public int remove() {
        // 将最后一个元素交换到根节点来，然后对根节点进行shiftDown操作即可，最后一个元素是要删除的元素
        swap(0, data.size() - 1);
        int rst = data.get(data.size() - 1);
        data.remove(data.size() - 1);
        shiftDown(0);
        return rst;
    }

    // 上浮
    private void shiftUp(int index) {
        int curIndex = index;
        // 根节点没有父节点，不需要执行上浮
        while (curIndex > 0) {
            int curNum = data.get(curIndex);

            int parentIndex = parent(curIndex);
            int parentNum = data.get(parentIndex);

            if(curNum < parentNum) {
                swap(curIndex, parentIndex);
                curIndex = parentIndex;
            } else {    // 比父节点大，就停止上浮
                break;
            }
        }
    }

    // 下沉
    private void shiftDown(int index) {
        int curIndex = index;
        while (leftChild(curIndex) < data.size()) {
            int curNum = data.get(curIndex);

            // 找到最小元素的索引
            int minIndex = leftChild(curIndex);
            if(rightChild(curIndex) < data.size() && data.get(rightChild(curIndex)) < data.get(leftChild(curIndex))) {
                minIndex = rightChild(curIndex);
            }

            if(data.get(minIndex) < curNum) {
                swap(curIndex, minIndex);
                curIndex = minIndex;
            } else {    // 比左右孩子中最小的还小，停止下沉
                break;
            }
        }
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int x, int y) {
        int temp = data.get(x);
        data.set(x, data.get(y));
        data.set(y, temp);
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>(Arrays.asList(10,23,4,1,3));
        MinHeap minHeap = new MinHeap(data);
        System.out.println(minHeap.size());

        minHeap.add(0);
        minHeap.add(34);
        minHeap.add(-10);
        System.out.println(minHeap.size());

        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.remove());
        }
        System.out.println(minHeap.size());
    }
}
