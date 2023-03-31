package leetcode.test;

import leetcode.p206.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Solution {
    // 最小堆
    public int findKthLargest(int[] nums, int k) {
        // 默认是最小堆
        Queue<Integer> queue = new PriorityQueue<>();

        for(int i = 0; i < nums.length; i++) {
            if(queue.size() == k) {
                int num = queue.peek();
                if(nums[i] > num) {
                    queue.poll();
                    queue.add(nums[i]);
                }
            } else {
                queue.add(nums[i]);
            }
        }

        return queue.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        Solution solution = new Solution();
        System.out.println(solution.findKthLargest(nums, k));
    }
}
