package leetcode.p61;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    // 找到第一个节点应该插入的位置
    // 找到和left指针(初始为虚拟头指针)差距为n的节点，然后在此设立一个指针，叫right指针(right指针下一个节点就是第一个节点应该插入的位置)
    // 然后共同往后移动left指针和right指针，当right指针指向节点的下一个节点为null时，即遍历到了链表尾部
    // left指针的下一个节点到链表尾节点，应该统一插入到链表头去，顺序不变，完成旋转
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        if(k == 0) return head;
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE, head);

        // 计算节点数，然后使用移动次数对链表节点数求余得出真正的次数，避免多次循环遍历链表
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        // 最终应该往后挪的次数，去掉循环
        int n = k % count;
        // 如果不需要往后挪，就直接返回源链表即可
        if(n == 0) return head;

        ListNode left = dummyHead;
        ListNode right = dummyHead;
        for(int i = 0; i < n; i++) {
            right = right.next;
            if(right == null) {
                right = head;
            }
        }

        // 遍历到链表结尾
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }

        // 将left.next 到 right 的子链表 连接到 链表头，即插入到dummyHead之后
        ListNode subListHead = left.next;
        ListNode subListTail = right;
        left.next = null;
        subListTail.next = dummyHead.next;
        dummyHead.next = subListHead;

        ListNode rst = dummyHead.next;
        dummyHead.next = null;

        return rst;
    }
}
