package leetcode.p206;

public class Solution {
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
        ListNode head = LinkedList.createLinkedList(new int[] {1,2,3,4,5});
        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.reverseList(head)));
    }

}
