package leetcode.p2462;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    // 最小堆
    public long totalCost(int[] costs, int k, int candidates) {
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        // 左边candidates个元素入队之后左边的索引边界[0, lIndex]，以最左边为基准，因为左边索引比右边索引要小，所以值相等的情况，还是选左边的元素
        int lIndex = candidates - 1;
        for(int i = 0; i < candidates && i < costs.length; i++) {
            queue.offer(new int[] {i, costs[i]});
        }

        // 右边candidates个元素入队之后右边的索引边界[rIndex, costs.length-1]，要确保左右入队的没有重复元素
        int rIndex = Math.max(costs.length - candidates, candidates);
        for(int j = 0; j < candidates && costs.length - 1 - j >= candidates; j++) {
            queue.offer(new int[] {costs.length - 1 - j, costs[costs.length - 1 - j]});
        }

        long ret = 0;

        for(int i = 0; i < k; i++) {
            if(queue.isEmpty()) {
                break;
            }

            int[] e = queue.poll();

            int index = e[0];
            int value = e[1];

            ret += value;
            if(index <= lIndex) {   // 最小值落在左边（左边索引比右边索引要小，所以值相等的情况，还是选左边的元素）
                if(lIndex + 1 < rIndex) {   // 确保没有重复元素进入queue
                    queue.offer(new int[] {lIndex + 1, costs[lIndex + 1]});
                    lIndex++;
                }
            } else if(index >= rIndex) {    // 最小值落在右边
                if(rIndex - 1 > lIndex) {   // 确保没有重复元素进入queue
                    queue.offer(new int[] {rIndex - 1, costs[rIndex - 1]});
                    rIndex--;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] cost = new int[] {31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58};
        int k = 11;
        int candidates = 2;
        Solution solution = new Solution();
        System.out.println(solution.totalCost(cost, k, candidates));
    }
}
