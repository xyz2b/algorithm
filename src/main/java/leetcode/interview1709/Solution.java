package leetcode.interview1709;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {
    // 最小堆用于找到从小到大第k个数 + 集合去重
    public int getKthMagicNumber(int k) {
        int[] factors = new int[] {3, 5, 7};

        // 最小堆用于找到 从小到大的第 k 个数
        PriorityQueue<Long> queue = new PriorityQueue<>();
        // 集合用于去重，重复的元素就不往堆中存放了
        Set<Long> set = new HashSet<>();

        // 初始元素为1
        queue.offer(1L);
        set.add(1L);

        int rst = 0;
        for(int i = 0; i < k; i++) {
            // 从最小堆拿出来的数，就是从小到大排列的，从最小堆中取出k个数，那么第k个数就是从小到大第k个元素
            long cur = queue.poll();
            rst = (int) cur;
            // 计算下一波的数字（就是拿当前数字*{3,5,7}去掉重复的元素，加入堆中）
            for(long factor : factors) {
                long next = factor * cur;
                if(set.add(next)) {
                    queue.offer(next);
                }
            }
        }

        return rst;
    }
}
