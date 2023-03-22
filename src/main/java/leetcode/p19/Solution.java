package leetcode.p19;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    // 双指针
    // 找到和left指针(初始为虚拟头指针)差距为n的节点，然后在此设立一个指针，叫right指针
    // 然后共同往后移动left指针和right指针，当right指针指向节点的下一个节点为null时，即遍历到了链表尾部，left指针此时指向链表倒数第n个节点的前一个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n < 1) throw new IllegalArgumentException("n must greater than zero");

        ListNode dummyHead = new ListNode(-1, head);

        ListNode left = dummyHead;
        ListNode right = dummyHead;

        for(int i = 0; i < n; i++) {
            right = right.next;
            if(right == null) {
                throw new IllegalArgumentException("n must lesser than list node nums");
            }
        }

        while (right.next != null) {
            left = left.next;
            right = right.next;
        }

        // 删除left.next
        ListNode delNode = left.next;
        left.next = delNode.next;
        delNode.next = null;

        ListNode rst = dummyHead.next;
        dummyHead.next = null;

        return rst;
    }
}
