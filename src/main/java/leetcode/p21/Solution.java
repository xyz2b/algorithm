package leetcode.p21;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode rstCur = dummyHead;

        ListNode cur1 = list1;
        ListNode cur2 = list2;

        while (cur1 != null || cur2 != null) {
            int cur1Value = cur1 != null ? cur1.val : Integer.MAX_VALUE;
            int cur2Value = cur2 != null ? cur2.val : Integer.MAX_VALUE;

            if(cur1Value <= cur2Value) {
                rstCur.next = cur1;
                rstCur = cur1;

                if(cur1 != null) cur1 = cur1.next;
            } else {
                rstCur.next = cur2;
                rstCur = cur2;

                if(cur2 != null) cur2 = cur2.next;
            }
        }

        ListNode rst = dummyHead.next;
        dummyHead.next = null;
        return rst;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkedList.createLinkedList(new int[] {1,2,4});
        ListNode l2 = LinkedList.createLinkedList(new int[] {1,3,4});

        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.mergeTwoLists(l1, l2)));
    }
}
