package circulardeque;

public class CircularDeque<E> {
    private E[] queue;
    // head指向队首元素
    private int head;
    // tail指向队尾元素的后一个位置
    private int tail;
    // 容量
    private int capacity;
    // 目前队列中的元素数量
    private int size;

    public CircularDeque(int capacity) {
        queue = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public int size() {
        return size;
    }

    // 从队首入队（移动head指针，往前移动，注意是循环的）
    public boolean insertFront(E value) {
        if (isFull()) {
            return false;
        }
        if(head == 0) {
            head = capacity - 1;
        } else {
            head = (head - 1) % capacity;
        }
        // head指向队首元素（插入时先计算新的head的位置，再写入元素）
        queue[head] = value;
        size++;
        return true;
    }

    // 从队尾入队（移动tail指针，往后移动，注意是循环的）
    public boolean insertLast(E value) {
        if (isFull()) {
            return false;
        }
        // tail指向队尾元素的后一个位置（插入时先写入元素，再计算新的tail的位置）
        queue[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    // 从队首出队（移动hea指针，往后移动，注意是循环的）
    public E deleteFront() {
        if(isEmpty()) {
            return null;
        }
        // head指向队首元素（删除时先获取待删除的元素，再计算新的head的位置）
        E e = queue[head];
        head = (head + 1) % capacity;
        size--;
        return e;
    }

    // 从队尾出队（移动tail指针，往前移动，注意是循环的）
    public E deleteLast() {
        if(isEmpty()) {
            return null;
        }
        if(tail == 0) {
            tail = capacity - 1;
        } else {
            tail = (tail - 1) % capacity;
        }
        // tail指向队尾元素的后一个位置（删除时先计算新的tail的位置，再获取待删除的元素）
        E e = queue[tail];
        size--;
        return e;
    }

    public E getFront() {
        if (isEmpty()) {
            return null;
        }
        return queue[head];
    }

    public E getRear() {
        if (isEmpty()) {
            return null;
        }
        // 如果tail是0，则其前一个位置是队尾元素，即数组中最后一个元素
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

    public static void main(String[] args) {
        CircularDeque<Integer> circularDeque = new CircularDeque<Integer>(3);
        System.out.println(circularDeque.insertLast(1));
        System.out.println(circularDeque.insertLast(2));
        System.out.println(circularDeque.insertFront(3));
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getRear());
        System.out.println(circularDeque.getFront());
        System.out.println(circularDeque.isFull());
        System.out.println(circularDeque.deleteLast());
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getFront());
    }
}
