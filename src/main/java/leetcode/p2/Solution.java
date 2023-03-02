package leetcode.p2;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rstHead = new ListNode();
        ListNode rst = rstHead;

        ListNode cur1 = l1;
        ListNode cur2 = l2;

        int carry = 0;

        while (true) {
            if(cur1 != null && cur2 != null) {
                int sum = cur1.val + cur2.val + carry;
                int quotient = sum / 10;
                int remainder = sum % 10;

                rst.next = new ListNode(remainder);
                rst = rst.next;

                carry = quotient;

                cur1 = cur1.next;
                cur2 = cur2.next;
            } else if (cur1 != null) {
                int sum = cur1.val + carry;
                int quotient = sum / 10;
                int remainder = sum % 10;

                rst.next = new ListNode(remainder);
                rst = rst.next;

                carry = quotient;

                cur1 = cur1.next;
            } else if (cur2 != null) {
                int sum = cur2.val + carry;
                int quotient = sum / 10;
                int remainder = sum % 10;

                rst.next = new ListNode(remainder);
                rst = rst.next;

                carry = quotient;

                cur2 = cur2.next;
            } else {
                break;
            }
        }

        if(carry != 0) {
            rst.next = new ListNode(carry);
        }

        return rstHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkedList.createLinkedList(new int[] {9,9,9,9,9,9,9});
        ListNode l2 = LinkedList.createLinkedList(new int[] {9,9,9,9});

        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.addTwoNumbers(l1, l2)));
    }
}
