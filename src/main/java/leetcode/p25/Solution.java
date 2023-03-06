package leetcode.p25;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;
        ListNode dummyHead = new ListNode(-1, head);

        // 指向需要反转链表头结点的前一个结点
        ListNode cur = dummyHead;
        while (cur.next != null) {
            // 需要反转链表头结点的前一个结点
            ListNode pre = cur;
            // 经过反转之后链表的尾结点 就是 反转之前链表的头结点
            ListNode afterReverseTail = cur.next;
            // 找到需要反转的子链表的尾结点，如果剩下的节点不够k个，就直接退出while循环
            int flag = 0;
            for(int i = 0; i < k; i++) {
                if(cur.next != null) {
                    cur = cur.next;
                } else {
                    flag = 1;
                    break;
                }
            }
            if(flag == 1) {
                break;
            }
            // 需要反转的子链表尾结点的后一个节点，即下一个需要反转的子链表的头结点
            ListNode nextListHead = cur.next;
            // 断开子链表与下一个子链表的连接，然后直接反转该子链表即可
            cur.next = null;
            // 将反转之后的子链表，重新链到原链表的后面
            pre.next = reverseList(pre.next);
            // 将下一个需要反转的子链表 重新链到 当前反转之后的子链表的后面
            afterReverseTail.next = nextListHead;
            // 更新遍历指针，因为cur指向需要反转链表头结点的前一个结点，所以更新为当前反转之后的子链表的尾结点即可，它就是下一个待反转链表头节点的前一个节点
            cur = afterReverseTail;
        }

        ListNode rst = dummyHead.next;
        dummyHead.next = null;
        return rst;
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

    public static void main(String[] args) {
        ListNode head = LinkedList.createLinkedList(new int[] {1,2});
        int k = 2;
        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.reverseKGroup(head, k)));
    }
}
