package leetcode.p203;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    // 加个虚拟头节点，方便处理删除头节点
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;

        ListNode dummyHead = new ListNode(0, head);

        ListNode cur = dummyHead;
        while (cur.next != null) {
            if(cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = cur.next.next;
                delNode.next = null;
            } else {
                cur = cur.next;
            }
        }

        ListNode rst = dummyHead.next;
        dummyHead.next = null;

        return rst;
    }

    public static void main(String[] args) {
        ListNode head = LinkedList.createLinkedList(new int[] {7,7,7,7});
        int val = 7;
        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.removeElements(head, val)));
    }
}
