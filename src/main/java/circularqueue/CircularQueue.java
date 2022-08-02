package circularqueue;


public class CircularQueue<E> {
    private E[] queue;
    private int head;
    private int tail;
    private int capacity;
    private int size;

    public CircularQueue(int capacity) {
        queue = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public int size() {
        return size;
    }

    public boolean enQueue(E value) {
        if (isFull()) {
            return false;
        }
        queue[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    public E deQueue() {
        if(isEmpty()) {
            return null;
        }
        E e = queue[head];
        head = (head + 1) % capacity;
        size--;
        return e;
    }

    public E Front() {
        if (isEmpty()) {
            return null;
        }
        return queue[head];
    }

    public E Rear() {
        if (isEmpty()) {
            return null;
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

    public static void main(String[] args) {
        CircularQueue<Integer> circularQueue = new CircularQueue<>(4);
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.isEmpty());

        circularQueue.enQueue(1);
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        circularQueue.enQueue(2);
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        circularQueue.enQueue(3);
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        circularQueue.enQueue(4);
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());

        System.out.println(circularQueue.enQueue(5));
        System.out.println(circularQueue.isFull());
        System.out.println(circularQueue.isEmpty());

        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());

        System.out.println(circularQueue.enQueue(5));
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());

        System.out.println(circularQueue.enQueue(6));
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.enQueue(7));
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.enQueue(8));
        System.out.println(circularQueue.enQueue(9));
        System.out.println(circularQueue.enQueue(10));
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.Rear());
    }
}
