package leetcode.p23;

import java.util.PriorityQueue;
import java.util.Queue;

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
    // 优先队列，最小堆
    public ListNode mergeKLists(ListNode[] lists) {

        Queue<ListNode> queue = new PriorityQueue<>((ListNode node1, ListNode node2) -> {
            return node1.val - node2.val;
        });

        // 将多个链表的头都加入到队列中，注意过滤掉NULL的链表
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }

        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        while (!queue.isEmpty()) {
            // 拿出来的都是最小的node
            ListNode node = queue.poll();
            // 如果从队列中拿出的node.next不为空，继续加入队列
            if(node.next != null) {
                queue.add(node.next);
                node.next = null; // 注意要断开当前node的next
            }
            // 将当前node加入到结果集
            cur.next = node;
            cur = cur.next;
        }

        ListNode rst = dummyHead.next;
        dummyHead.next = null;
        return rst;
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
