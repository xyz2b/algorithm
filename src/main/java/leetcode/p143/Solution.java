package leetcode.p143;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    // 如何快速找到中间的节点，通过一次遍历（奇数偶数个元素不同）
    // 找到中间节点之后，将右边链表反转一下，然后左边取一个节点，右边去一个节点拼接起来即可
    public void reorderList(ListNode head) {
        if(head == null) return;

        // 左边的链表
        ListNode leftList = head;

        // 寻找整个链表的中间节点
        // 遍历的子链表范围[head, cur]
        // middle始终指向子链表的中间节点
        ListNode middle = head;
        ListNode cur = head;

        // 中间节点
        // 偶数: curIndex / 2
        // 奇数: curIndex / 2
        // 1个节点（子链表索引范围[0,0]），curIndex为0，中间间节点，索引为0，就是自己
        // 2个节点（子链表索引范围[0,1]），curIndex为1，中间节点，索引为0
        // 3个节点（子链表索引范围[0,2]），curIndex为2，中间节点，索引为1
        // 4个节点（子链表索引范围[0,3]），curIndex为3，中间节点，索引为1
        // 5个节点（子链表索引范围[0,4]），curIndex为4，中间节点，索引为2
        // 6个节点（子链表索引范围[0,5]），curIndex为5，中间节点，索引为2
        // 7个节点（子链表索引范围[0,6]），curIndex为6，中间节点，索引为3
        // 8个节点（子链表索引范围[0,7]），curIndex为7，中间节点，索引为3
        int curIndex = 0;
        while (cur != null) {
            // 根据上面规律得出，rightIndex为偶数时，middle就会往后挪一位
            if(curIndex != 0 && curIndex % 2 == 0) {
                middle = middle.next;
            }

            cur = cur.next;
            curIndex++;
        }

        // middle.next为右边的链表
        ListNode rightList = middle.next;
        middle.next = null;


        // 反转右边的链表
        rightList = reverseList(rightList);

        ListNode left = leftList;
        ListNode right = rightList;
        ListNode dummyHead = new ListNode();
        ListNode rstCur = dummyHead;
        // 左右两边的链表各取一个节点拼接
        while (left != null || right != null) {
            if(left != null) {
                rstCur.next = left;
                left = left.next;
                rstCur = rstCur.next;
            }

            if(right != null) {
                rstCur.next = right;
                right = right.next;
                rstCur = rstCur.next;
            }
        }
        dummyHead.next = null;
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
        ListNode head = LinkedList.createLinkedList(new int[] {});
        Solution solution = new Solution();
        solution.reorderList(head);
        System.out.println(LinkedList.linkedListToString(head));
    }
}
