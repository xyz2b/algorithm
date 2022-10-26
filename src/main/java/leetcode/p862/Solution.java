package leetcode.p862;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int shortestSubarray(int[] nums, int k) {
        // 计算前缀和
        /**
         * 记数组 nums 的前缀和数组为 preSumArr。对于边界情况，preSumArr[0]=0。
         * 而从数组 nums 下标 i 开始长为 m 的子数组的和就可以根据 preSumArr[i+m]−preSumArr[i] 快速计算得到。
         * */
        int[] preSumArr = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            preSumArr[i + 1] = nums[i] + preSumArr[i];
        }

        int rst = nums.length + 1;

        // 单调双端队列
        // 遍历 preSumArr 数组，访问过的前缀和先暂存在某种集合 q 中。
        // 根据前缀和数组的性质，后访问到的某个前缀和 preSumArr[j] 减去之前访问到的某个前缀和 preSumArr[i]（j>i）即为 nums 中某段子数组的和。
        Deque<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i <= nums.length; i++) {
            long curSum = preSumArr[i];
            // 每次访问到某个前缀和 preSumArr[j] 时，可以用它尝试减去集合 q 中所有已经访问过的前缀和。
            // 当某个 q 中的前缀和 preSumArr[i]，第一次出现 preSumArr[j]−preSumArr[i]≥k 时，这个下标 i 就找到了以它为起点的最短子数组的长度 j−i。
            // 此时，可以将它从 q 中移除，后续即使还有以它为起点的满足条件的子数组，长度也会大于当前的长度。
            while (!queue.isEmpty() && curSum - preSumArr[queue.peekFirst()] >= k) {
                rst = Math.min(rst, i - queue.pollFirst());
            }
            // 当一个前缀和 preSumArr[j] 试减完 q 中的元素时，需要将它也放入 q 中。
            // 将它放入 q 前， q 中可能存在比 preSumArr[j] 大的元素，而这些元素和 preSumArr[j] 一样，
            // 只能作为再后续访问到的某个前缀和 preSumArr[h] 的减数。而作为减数时，更大的值只会让不等式 preSumArr[h]−preSumArr[i]≥k 更难满足。
            // 即使都满足，后访问到的值也可以带来更短的长度。
            // 因此，在把 preSumArr[j] 放入 q 时，需要将 q 中大于等于 preSumArr[j] 的值也都移除。

            // 接下来考虑 q 的性质。我们会往 q 中增加和删除元素。每次增加一个元素 curSum 前，先根据不等式删除一部分元素（也可能不删），
            // 然后再删除 q 中所有大于等于 curSum 的元素，这样每次加进去的元素都会是 q 中的唯一最大值，使得 q 中的元素是按照添加顺序严格单调递增的，我们知道单调队列是满足这样的性质的。
            while (!queue.isEmpty() && preSumArr[queue.peekLast()] >= curSum) {
                queue.pollLast();
            }

            queue.offerLast(i);
        }
        return rst < nums.length + 1 ? rst : -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-28,81,-20,28,-29};
        int k = 89;
        Solution solution = new Solution();
        System.out.println(solution.shortestSubarray(nums, k));
    }
}
