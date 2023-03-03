package leetcode.p82;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;

        ListNode dummyHead = new ListNode(Integer.MAX_VALUE, head);

        int duplicateNum = Integer.MAX_VALUE;
        ListNode pre = dummyHead, cur = dummyHead.next;
        while (cur.next != null) {
            if(cur.next.val == cur.val) {   // 删除除重复元素除去第一个之后的元素
                // 删除 cur.next
                duplicateNum = cur.val;
                ListNode delNode = cur.next;
                cur.next = cur.next.next;
                delNode.next = null;
            } else if (cur.val == duplicateNum) {   // 删除重复元素的第一个元素
                // 删除 cur
                pre.next = cur.next;
                ListNode delNode = cur;
                cur = cur.next;
                delNode.next = null;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        // 最后只剩一个元素是重复元素的情况
        if(cur.val == duplicateNum) {
            // 删除 cur
            pre.next = cur.next;
            ListNode delNode = cur;
            cur = cur.next;
            delNode.next = null;
        }

        ListNode rst = dummyHead.next;
        dummyHead.next = null;
        return rst;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null) return null;

        ListNode dummyHead = new ListNode(Integer.MAX_VALUE, head);

        int duplicateNum = Integer.MAX_VALUE;
        ListNode cur = dummyHead;
        while (cur.next.next != null) {
            if(cur.next.val == cur.next.next.val) {   // 删除除重复元素除去第一个之后的元素
                // 删除 cur.next.next
                duplicateNum = cur.next.val;
                ListNode delNode = cur.next.next;
                cur.next.next = cur.next.next.next;
                delNode.next = null;
            } else if (cur.next.val == duplicateNum) {   // 删除重复元素的第一个元素
                // 删除 cur.next
                ListNode delNode = cur.next;
                cur.next = cur.next.next;
                delNode.next = null;
            } else {
                cur = cur.next;
            }
        }

        // 最后只剩一个元素是重复元素的情况
        if(cur.next.val == duplicateNum) {
            // 删除 cur
            ListNode delNode = cur.next;
            cur.next = cur.next.next;
            delNode.next = null;
        }

        ListNode rst = dummyHead.next;
        dummyHead.next = null;
        return rst;
    }

    public static void main(String[] args) {
        ListNode head = LinkedList.createLinkedList(new int[] {1,2,3,3,4,4,5});
        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.deleteDuplicates2(head)));
    }
}
