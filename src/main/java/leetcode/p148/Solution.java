package leetcode.p148;

import leetcode.p206.ListNode;
import leetcode.p206.LinkedList;

import java.util.List;

public class Solution {
    // 自底向上的归并排序
    public ListNode sortList(ListNode head) {
        if(head == null) return null;

        ListNode dummyNode = new ListNode(Integer.MIN_VALUE, head);

        int sz = 1;
        while (true) {
            ListNode cur = dummyNode;
            // 每条子链表sz个node(最后一个子链表可能会少于sz个节点)，可以分成多少个子链表
            int listCountOfNodeCountIsSz = 0;
            while (cur.next != null) {              // 一次将两条sz个连续节点的链表合并
                // 指向需要合并的第一条链表头结点的前一个结点
                ListNode l1PreNode = cur;
                ListNode l1HeadNode = cur.next;

                // 是否遍历到了原始链表的尾部
                boolean isOrigenListTail = false;
                // 遍历出来需要合并的第一条list，找到其尾结点
                for(int i = 0; i < sz; i++) {
                    cur = cur.next;
                    if(cur == null) {
                        isOrigenListTail = true;
                        break;
                    }

                }
                listCountOfNodeCountIsSz++;

                if(isOrigenListTail) {
                    break;
                }
                // 指向需要合并的第二条链表头结点的前一个结点
                ListNode l1TailNode = cur;

                ListNode l2preNode = cur;
                ListNode l2HeadNode = cur.next;

                // 遍历出来需要合并的第二条list，找到其尾结点
                for(int i = 0; i < sz; i++) {
                    if(cur.next == null)
                        break;
                    cur = cur.next;
                }
                listCountOfNodeCountIsSz++;

                ListNode l2TailNode = cur;

                // 下一次需要merge的两条链表的首节点
                ListNode nextListHeadNode = l2TailNode.next;

                // 断开连接，获得两条待合并的有序子链表
                l1TailNode.next = null;
                l2TailNode.next = null;

                // merge
                ListNode mergeRstCur = l1PreNode;

                ListNode mergeCur1 = l1HeadNode;
                ListNode mergeCur2 = l2HeadNode;

                // 合并两个字链表之后的新的链表尾结点
                ListNode mergeListTailNode = null;
                while (mergeCur1 != null || mergeCur2 != null) {
                    int cur1Value = mergeCur1 != null ? mergeCur1.val : Integer.MAX_VALUE;
                    int cur2Value = mergeCur2 != null ? mergeCur2.val : Integer.MAX_VALUE;

                    if(cur1Value <= cur2Value) {
                        mergeRstCur.next = mergeCur1;
                        mergeRstCur = mergeCur1;

                        if(mergeCur1.next == null && mergeCur2 == null) {
                            mergeListTailNode = mergeCur1;
                        }

                        if(mergeCur1 != null) mergeCur1 = mergeCur1.next;
                    } else {
                        mergeRstCur.next = mergeCur2;
                        mergeRstCur = mergeCur2;

                        if(mergeCur2.next == null) {
                            mergeListTailNode = mergeCur2;
                        }

                        if(mergeCur2 != null) mergeCur2 = mergeCur2.next;
                    }
                }

                // cur定位到当前合并完生成的新的有序链表的最后一个节点，同时需要将当前合并完的新有序链表重新接到后续待合并的链表的前面
                cur = mergeListTailNode;
                cur.next = nextListHeadNode;
            }
            sz += sz;
            if(listCountOfNodeCountIsSz == 1) { // 就分出一条子链表，就退出了，说明sz长度已经大于等于整个链表的节点数了，不需要再进行merge了
                break;
            }
        }

        ListNode rst = dummyNode.next;
        dummyNode.next = null;

        return rst;
    }

    public static void main(String[] args) {
        ListNode head = LinkedList.createLinkedList(new int[] {-1,5,3,4,0});
        Solution solution = new Solution();
        System.out.println(LinkedList.linkedListToString(solution.sortList(head)));
    }
}
