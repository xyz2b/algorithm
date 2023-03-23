package leetcode.p61;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    // 找到第一个节点应该插入的位置
    // 找到和left指针(初始为虚拟头指针)差距为n的节点，然后在此设立一个指针，叫right指针(right指针下一个节点就是第一个节点应该插入的位置)
    // 然后共同往后移动left指针和right指针，当right指针指向节点的下一个节点为null时，即遍历到了链表尾部
    // 然后将从right到left.next的节点一个个按顺序插入到链表头部去，但是链表方向是从left->right，无法顺利完成此插入过程。此时查看规律，可以发现
    // 将left指针的下一个节点一直到链表的尾节点，作为一个子链表，插入到链表头去，可以得到相同的结果，完成旋转
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        if(k == 0) return head;
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE, head);

        // 计算节点数，然后使用移动次数对链表节点数求余得出真正的次数，避免多次循环遍历链表
        // 如果直接按照k进行循环，可能会多次循环遍历整个链表，比如链表节点数为3，k为30，就要遍历30次整个链表，但是最终结果是链表节点并没有发现改变，浪费性能
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
