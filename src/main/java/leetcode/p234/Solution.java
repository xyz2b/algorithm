package leetcode.p234;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    // 同p143
    // 找中间节点
    // 然后反转右边链表
    // 然后一一对比两边链表
    // 注意：如果是奇数个节点，左边链表会比右边链表多一个中间节点，这个中间节点不需要考虑，只需要对比左右两边链表前面偶数个节点接口
    public boolean isPalindrome(ListNode head) {
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
        // 左右两边的链表各取一个节点进行对比
        // 要么是奇数个节点
        // 要么是偶数个节点
        // 其实只需要对比左右链表前面偶数个节点即可，左边子链表可能是偶数个节点，也可能是奇数个节点，如果是奇数最后一个节点就不需要关心
        while (left != null && right != null) {
            if(left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
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
}
