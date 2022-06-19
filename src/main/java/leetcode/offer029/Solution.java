package leetcode.offer029;

class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};

class Solution {
    public Node insert(Node head, int insertVal) {
        // 空的情况，直接新建一个指向自己的Node
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }

        Node cur = head;

        // 需要插入的元素的位置，插入到这个节点之后
        Node insertNode = null;
        do {
            // 正常判断在一个有序链表中插入一个值，就是插入到 比当前值小（或等于）的节点 和 比当前值大（或等于）的节点 之间
            if (cur.val <= insertVal && cur.next.val >= insertVal) {
                insertNode = cur;
                break;
            }

            // 因为链表是单调的，cur.val > cur.next.val时，cur就是当前链表中的最大值，cur.next就是当前链表中的最小值，找到了最大值和最小值的分界点
            // 如果此时插入的正好是比最大值还大（或等于）的值，就是插入到cur之后；如果此时插入的正好是比最小值还小（或等于）的值，也是插入到cur之后即可
            if (cur.val > cur.next.val) {
                if (cur.val <= insertVal || cur.next.val >= insertVal) {
                    insertNode = cur;
                    break;
                }
            }

            cur = cur.next;
        } while (cur != head);

        if (insertNode != null) {
            insertNode.next = new Node(insertVal, insertNode.next);
        } else {    // 如果只有一个Node，通过上面的检查之后是得不到insertNode的，此时只需要在这一个Node后面插入即可
            head.next = new Node(insertVal, head.next);
        }

        return head;
    }
}

