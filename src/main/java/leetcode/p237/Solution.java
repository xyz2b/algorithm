package leetcode.p237;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    // 将待删除节点后面所有节点的值向前平移一次，然后删除最后一个节点(尾结点)即可
    public void deleteNode(ListNode node) {
        ListNode cur = node;
        // 因为要删除最后一个节点，所以需要知道最后一个节点的前一个节点，所以这里循环只到cur.next.next == null时，cur就是待删除尾结点的前一个节点，cur.next就是需要删除的那个尾结点
        while (cur.next.next != null) {
            ListNode next = cur.next;
            cur.val = next.val;
            cur = next;
        }

        cur.val = cur.next.val;
        cur.next = null;
    }
}
