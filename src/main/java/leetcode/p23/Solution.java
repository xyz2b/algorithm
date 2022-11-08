package leetcode.p23;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// 最小堆解决merge K问题（合并k个有序数组）
// 其实在归并排序法中，我们之前都是一次merge两个数组，其实也可以使用该方法一次merge k个数组
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        }

        ListNode head = null;
        ListNode tail = null;

        // 使用最小堆
        // Java中的优先队列默认为最小堆
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((x, y) -> x.val - y.val);

        // lists中所有的链表第一个节点放入最小堆(不为空的节点)
        for(int i = 0; i < lists.length; i++) {
            if(lists[i] != null) {
                priorityQueue.offer(lists[i]);
            }
        }

        while (!priorityQueue.isEmpty()) {
            // k个node中的取出最小node
            ListNode listNode = priorityQueue.poll();
            if(listNode.next != null) { // 如果取出的node next不为空，即表明它所在的链表还有节点，就将其下一个节点放入堆中
                priorityQueue.offer(listNode.next);
                listNode.next = null;   // 注意别忘了这一步
            }
            // 将取出的最小node插入结果集的tail处
            if(head == null) {
                head = tail = listNode;
            } else {
                tail.next = listNode;
                tail = listNode;
            }

        }

        return head;
    }

    public static void main(String[] args) {
        ListNode root1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode root2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode root3 = new ListNode(2, new ListNode(6));
        ListNode[] lists = new ListNode[3];
        lists[0] = root1;
        lists[1] = root2;
        lists[2] = root3;

        Solution solution = new Solution();
        ListNode root = solution.mergeKLists(lists);

        ListNode cur = root;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
