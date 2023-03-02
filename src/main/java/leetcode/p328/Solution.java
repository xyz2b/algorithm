package leetcode.p328;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    // 同p86
    public ListNode oddEvenList(ListNode head) {
        ListNode oddListHead = new ListNode();
        ListNode evenListHead = new ListNode();

        ListNode odd = oddListHead;
        ListNode even = evenListHead;

        ListNode cur = head;
        int index = 1;
        while (cur != null) {
            if(index % 2 == 0) { // even
                even.next = cur;
                even = even.next;
            } else { // odd
                odd.next = cur;
                odd = odd.next;
            }

            cur = cur.next;
            index++;
        }

        odd.next = evenListHead.next;
        even.next = null;

        return oddListHead.next;
    }

    public static void main(String[] args) {
        ListNode head = LinkedList.createLinkedList(new int[] {1,2,3,4,5});
        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.oddEvenList(head)));
    }
}
