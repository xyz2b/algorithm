package leetcode.p86;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return null;

        ListNode lesserHead = new ListNode();
        ListNode greaterHead = new ListNode();

        ListNode lesser = lesserHead;
        ListNode greater = greaterHead;

        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                lesser.next = cur;
                lesser = lesser.next;
            } else {
                greater.next = cur;
                greater = greater.next;
            }

            cur = cur.next;
        }

        lesser.next = greaterHead.next;
        greater.next = null;

        return lesserHead.next;
    }

    public static void main(String[] args) {
        ListNode head = LinkedList.createLinkedList(new int[] {2});
        int x = 2;
        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.partition(head, x)));
    }
}
