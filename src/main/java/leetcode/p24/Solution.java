package leetcode.p24;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode pre = dummyHead;

        while (pre.next != null && pre.next.next != null) {
            ListNode node1 = pre.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;

            pre.next = node2;
            node2.next = node1;
            node1.next = next;

            pre = node1;
        }

        ListNode rst = dummyHead.next;
        dummyHead.next = null;
        return rst;
    }

    public static void main(String[] args) {
        ListNode head = LinkedList.createLinkedList(new int[] {1});
        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.swapPairs(head)));
    }
}
