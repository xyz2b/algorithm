package leetcode.p215;

import java.util.PriorityQueue;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        for(int i = k; i < nums.length; i++) {
            if(!pq.isEmpty() && pq.peek() < nums[i]) {
                // 如果是自实现的堆，可以使用replace方法，将堆的根节点(最小值)替换成nums[i]的值
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        return pq.peek();
    }
}
