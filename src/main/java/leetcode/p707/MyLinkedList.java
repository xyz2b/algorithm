package leetcode.p707;

public class MyLinkedList {
    class Node {
        private int val;
        private Node next;
        private Node prev;

        public Node(int val) {
            this.val = val;
            this.next = prev = null;
        }

        public Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head;
    private Node tail;

    private int size;

    public MyLinkedList() {
        head = tail = null;
        size = 0;
    }

    public int get(int index) {
        if(index > size - 1 || index < 0) {
            return -1;
        }

        // 找到待查找节点
        Node cur = head;
        int curIndex = 0;
        while (curIndex != index) {
            cur = cur.next;
            curIndex++;
        }

        return cur != null ? cur.val : -1;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if(index > size) {
            return;
        }

        // 链表为空
        if(head == null) {
            head = tail = new Node(val);
            size++;
            return;
        }

        // 在末尾插入
        if(index == size) {
            Node node = new Node(val);
            node.prev = tail;
            node.prev.next = node;
            tail = node;

            size++;
            return;
        }

        // 在开头插入
        if(index <= 0) {
            Node node = new Node(val);
            node.next = head;
            node.next.prev = node;
            head = node;

            size++;
            return;
        }

        // 找到待插入位置的节点，当前节点应该放到插入节点的后面
        Node cur = head;
        int curIndex = 0;
        while (curIndex != index) {
            cur = cur.next;
            curIndex++;
        }

        // 正常插入
        Node node = new Node(val);
        node.prev = cur.prev;
        node.next = cur;

        cur.prev = node;
        node.prev.next = node;
        size++;
    }

    public void deleteAtIndex(int index) {
        if(index > size - 1 || index < 0) {
            return;
        }

        // 找到待删除节点
        Node cur = head;
        int curIndex = 0;
        while (curIndex != index) {
            cur = cur.next;
            curIndex++;
        }

        if(cur == head) {
            head = head.next;
        } else {    // 不是头结点，则表明cur的prev不为空
            cur.prev.next = cur.next;
        }

        if(cur == tail) {
            tail = tail.prev;
        } else {    // 不是尾节点，则表明cur的next不为空
            cur.next.prev = cur.prev;
        }

        cur.next = cur.prev = null;
        size--;
    }

    @Override
    public String toString() {
        Node cur = head;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        while (cur != null) {
            stringBuilder.append(cur.val);
            stringBuilder.append(", ");
            cur = cur.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(7);
        System.out.println(linkedList);
        linkedList.addAtHead(2);
        System.out.println(linkedList);

        linkedList.addAtHead(1);
        System.out.println(linkedList);

        linkedList.addAtIndex(3, 0);
        System.out.println(linkedList);

        linkedList.deleteAtIndex(2);
        System.out.println(linkedList);

        linkedList.addAtHead(6);
        System.out.println(linkedList);

        linkedList.addAtTail(4);
        System.out.println(linkedList);

        System.out.println(linkedList.get(4));
        linkedList.addAtHead(4);
        System.out.println(linkedList);

        linkedList.addAtIndex(5, 0);
        System.out.println(linkedList);

        linkedList.addAtHead(6);
        System.out.println(linkedList);


    }
}



