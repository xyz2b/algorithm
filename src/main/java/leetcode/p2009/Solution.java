package leetcode.p2009;

import java.util.*;

public class Solution {
    /*
    假设最后连续的数组的最小值为 left，则最大值 right=left+n−1。
    原数组 nums 中，如果有位于 [left,right] 中的，如果只出现一次，我们可以对其进行保留；
    多次出现时，我们则需要对其进行操作；不在这个区间的数字，我们也需要对其进行操作，
    将它们变成其他数字来对这个区间进行补足。因此，我们需要统计原数组 nums 中，
    位于区间 [left,right] 内不同的数字个数 k，而 n−k 就是我们需要进行的操作数。
    * */
    // 去重+排序+滑动窗口
    public int minOperations(int[] nums) {
        int n = nums.length;
        // 去重
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }

        // 排序
        List<Integer> sortedUniqNums = new ArrayList<>(set);
        Collections.sort(sortedUniqNums);

        int res = n;
        int j = 0;
        // 以 sortedUniqNums 中每个元素作为 left，计算出位于区间 [left,right] 内不同的数字个数 k，求出所有可能性下最小的 n−k 即可
        // 滑动窗口左端点i的值作为 left，然后向右扩展右端点j，窗口的长度即为 k=(j-i+1)
        for(int i = 0; i < sortedUniqNums.size(); i++) {
            int left = sortedUniqNums.get(i);
            int right = left + n - 1;
            while (j < sortedUniqNums.size() && sortedUniqNums.get(j) <= right) {
                res = Math.min(res, n - (j - i + 1));
                j++;
            }
        }

        return res;
    }
}
