package leetcode.p83;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;

        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null) {
            if(cur.val == pre.val) {
                ListNode delNode = cur;
                pre.next = delNode.next;
                cur = delNode.next;
                delNode.next = null;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = LinkedList.createLinkedList(new int[] {1,1,2,3,3});
        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.deleteDuplicates(head)));
    }
}
