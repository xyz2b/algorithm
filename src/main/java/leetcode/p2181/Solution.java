package leetcode.p2181;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode mergeNodes(ListNode head) {
        // 之前遇到的零节点
        ListNode zeroNode = head;
        ListNode cur = head.next;
        int sum = 0;
        while (cur != null) {
            if(cur.val == 0) {
                // 把两个零之间的数值加和，设置到第一个零节点的val，然后将第一个零节点的next设置为第二个零节点（需要考虑最后一个零节点，最后零节点的前一个零节点next应该为null）
                // 除了开头之后遇到零，就把之前遇到的零节点(zeroNode)的val设置为sum，然后将之前零节点的next指向当前零节点cur（需要考虑最后一个零的情况），然后更新之前遇到的零节点为当前遇到的零节点
                zeroNode.val = sum;
                if(cur.next != null) {  // 不是最后一个零
                    zeroNode.next = cur;
                } else {    // cur.next == null，如果遇到最后一个零，它的next必然是null
                    zeroNode.next = null;
                }
                zeroNode = cur;
                sum = 0;
            } else {
                sum += cur.val;
            }

            cur = cur.next;
        }

        return head;
    }
}
