package lru;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private int capacity;

    class Node {
        public int key;
        public int value;

        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            prev = next = null;
        }
    }
    private Node head;
    private Node tail;
    private int pairNum;

    private Map<Integer, Node> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<Integer, Node>(capacity);
        head = tail = null;
    }

    public int get(int key) {
        Node n = cache.get(key);
        if (n == null) {
            return -1;
        }

        // move node to list head
        moveNodeToListHead(n);
        return n.value;
    }

    public void put(int key, int value) {
        // if key is existed
        Node n = cache.get(key);
        if (n != null) {
            // update value
            n.value = value;

            // move node to list head
            moveNodeToListHead(n);
            return;
        }

        // if lru cache is full, del list tail node, and insert new key-value
        if (pairNum == capacity) {
            int tailKey = tail.key;

            // del list tail node
            deleteNodeFromList(tail);

            cache.remove(tailKey);
            pairNum--;
        }

        // if key is not existed, and lru cache is not full, insert new key-value
        Node newNode = new Node(key, value);
        // insert new node to list head
        insertNodeToListHead(newNode);
        cache.put(key, newNode);
        pairNum++;
    }

    private void moveNodeToListHead(Node n) {
        if (n == head) {
            return;
        }

        // del n from list
        deleteNodeFromList(n);

        // insert n to list head
        insertNodeToListHead(n);
    }

    private void insertNodeToListHead(Node n) {
        if (head == null) { // if head == null, tail also null, so head == tail == null, list is empty
            head = tail = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
    }

    private void deleteNodeFromList(Node n) {
        if (n != head) {
            n.prev.next = n.next;
        } else { // if del node is head, n == head, update head
            head = n.next;
        }

        if (n != tail) {
            n.next.prev = n.prev;
        } else { // if del node is tail, n == tail, update tail
            tail = n.prev;
        }
        n.prev = n.next = null;
    }
}
