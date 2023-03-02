package leetcode.p206;

public final class LinkedList {
    public static ListNode createLinkedList(int[] arr) {
        if(arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);

        ListNode cur = head;
        for(int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }

        return head;
    }

    public static String linkedListToString(ListNode head) {
        StringBuilder sb = new StringBuilder();

        ListNode cur = head;
        while (cur != null) {
            sb.append(cur.val);
            sb.append("->");
            cur = cur.next;
        }
        sb.append("null");

        return sb.toString();
    }
}
