package leetcode.p445;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

import java.util.List;
import java.util.Stack;

public class Solution {
    // 1.反转两个链表(p206)，然后再进行两数和(p2)，然后再反转结果
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1r = reverseList(l1);
        ListNode l2r = reverseList(l2);
        return reverseList(addTwoNumbersOptimizeCode(l1r, l2r));
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

    public ListNode addTwoNumbersOptimizeCode(ListNode l1, ListNode l2) {
        ListNode rstHead = new ListNode();
        ListNode rst = rstHead;

        ListNode cur1 = l1;
        ListNode cur2 = l2;

        int carry = 0;
        while (cur1 != null || cur2 != null) {
            int x = cur1 != null ? cur1.val : 0;
            int y = cur2 != null ? cur2.val : 0;
            int sum = x + y + carry;
            int quotient = sum / 10;    // 商
            int remainder = sum % 10;   // 余数

            // 正向输出，将结果添加到链表尾
            rst.next = new ListNode(remainder);
            rst = rst.next;

            carry = quotient;   // 进位

            if (cur1 != null) cur1 = cur1.next;
            if (cur2 != null) cur2 = cur2.next;
        }

        if(carry != 0) {
            rst.next = new ListNode(carry);
        }

        return rstHead.next;
    }


    // 不动输入的链表，即不能反转输入的链表，使用栈
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode rst = null;

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int x = s1.isEmpty() ? 0 : s1.pop();
            int y = s2.isEmpty() ? 0 : s2.pop();
            int sum = x + y + carry;
            int quotient = sum / 10;    // 商
            int remainder = sum % 10;   // 余数

            // 正向输出，将结果添加到链表尾
            // 反向输出，将结果添加到链表头
            ListNode cur = new ListNode(remainder);
            cur.next = rst;
            rst = cur;

            carry = quotient;
        }

        return rst;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkedList.createLinkedList(new int[] {7,2,4,3});
        ListNode l2 = LinkedList.createLinkedList(new int[] {5,6,4});

        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.addTwoNumbers2(l1, l2)));
    }
}
