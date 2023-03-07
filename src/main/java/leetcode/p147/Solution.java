package leetcode.p147;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

import java.util.Stack;

class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE, head);
        Stack<ListNode> stack = new Stack<>();

        ListNode cur = dummyHead;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            if(node.val == dummyHead.val) {
                break;
            }
            ListNode pre = stack.peek();
            ListNode next = node.next;
            while (next != null) {
                if(node.val < next.val) {
                    pre.next = next;
                    node.next = next.next;
                    next.next = node;

                    pre = next;
                    next = node.next;
                } else {
                    break;
                }
            }
        }

        ListNode rst = dummyHead.next;
        dummyHead.next = null;
        return reverseList(rst);
    }


    public ListNode reverseList(ListNode head) {
        if(head == null) return null;

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;
    }

    // 链表中某个节点找待插入的位置，需要从链表头开始遍历
    public ListNode insertionSortList2(ListNode head) {
        if(head == null) return null;

        ListNode dummyHead = new ListNode(Integer.MIN_VALUE, head);

        // 已经排序过的最后一个节点
        ListNode lastSorted = dummyHead.next;
        // 待排序的节点，需要从链表头开始查找它的插入位置
        // 如果它的值大于已经排序的最后一个节点不需要再从头开始寻找插入位置了，它应插入的位置就是当前位置，保持不动
        ListNode cur = dummyHead.next.next;

        while (cur != null) {
            if(cur.val < lastSorted.val) {
                ListNode pre = dummyHead;
                while (pre.next.val < cur.val) {
                    pre = pre.next;
                }   // 此时 pre.next.val >= cur.val
                // 此时 lastSorted 始终在 cur 之前，即 lastSorted 就是 cur 的前一个节点
                // 断开cur前一个节点和cur的连接，将cur.next连接到cur前一个节点后面
                lastSorted.next = cur.next;
                // 将 cur 插入到pre之后，pre.next之前
                cur.next = pre.next;
                pre.next = cur;
            } else {
                lastSorted = lastSorted.next;
            }
            cur = lastSorted.next;
        }

        ListNode rst = dummyHead.next;
        dummyHead.next = null;
        return rst;
    }
}
