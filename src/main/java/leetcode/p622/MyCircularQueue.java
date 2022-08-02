package leetcode.p622;

class MyCircularQueue {
    private int[] queue;
    private int head;
    private int tail;
    private int capacity;
    private int size;

    public MyCircularQueue(int k) {
        queue = new int[k];
        this.capacity = k;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public int size() {
        return size;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        queue[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        if(tail == 0) {
            return queue[capacity - 1];
        } else {
            return queue[tail - 1];
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
