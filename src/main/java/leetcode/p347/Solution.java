package leetcode.p347;

import java.util.*;

public class Solution {
    // 前k大元素，最小堆
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        // 统计频率
        for(int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 优先队列，最小堆，前k大元素
        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k, (Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) -> {
            int count1 = entry1.getValue();
            int count2 = entry2.getValue();
            return count1 - count2;
        });

        for(Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if(pq.size() == k) {   // 优先队列中已经有k个元素了，就需要挤掉一个元素
                Map.Entry<Integer, Integer> minEntry = pq.peek();

                if(entry.getValue() > minEntry.getValue()) {    // 如果当前元素比堆顶元素大(比最小的大)，就把堆顶元素删除，将当前元素加入堆
                    pq.poll();
                    pq.add(entry);
                }
            } else {
                pq.add(entry);
            }
        }

        int[] rst = new int[pq.size()];
        int i = 0;
        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            rst[i] = entry.getKey();
            i++;
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1};
        int k = 2;
        Solution solution = new Solution();
        int[] rst = solution.topKFrequent(nums, k);
        for(int i = 0; i < rst.length; i++) {
            System.out.println(rst[i]);
        }
    }
}
