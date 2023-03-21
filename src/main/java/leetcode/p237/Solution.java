package leetcode.p237;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    // 将待删除节点后面一个节点的值复制到待删除节点的val中，然后删除待删除节点后一个节点即可
    public void deleteNode(ListNode node) {
        if(node == null) {
            return;
        }

        if(node.next == null) {
            node = null;
            return;
        }

        node.val = node.next.val;
        ListNode delNode = node.next;
        node.next = delNode.next;
        delNode.next = null;

        return;
    }
}
