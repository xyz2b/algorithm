package leetcode.p817;

import java.util.HashSet;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


public class Solution {
    public int numComponents(ListNode head, int[] nums) {
        // 统计nums中的数字，不需要管nums的顺序，只需要管它中间有哪些数字
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        ListNode cur = head;
        int rst = 0;
        // list中的元素是否在nums数组中
        boolean contained = false;
        while (cur != null) {
            int val = cur.val;
            if (set.contains(val)) {   // list中的元素在nums中
                if(!contained) {    // list当前遍历元素的前一个元素是不在nums中，而当前元素是在list中，说明当前遍历元素是一个组件的开始，计数+1
                    rst++;
                }
                contained = true;
            } else {    // list中的元素不在nums中
                contained = false;
            }

            cur = cur.next;
        }

        return rst;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3, null))));
        int[] nums = new int[] {0,3,1};
        Solution solution = new Solution();
        System.out.println(solution.numComponents(head, nums));
    }
}
