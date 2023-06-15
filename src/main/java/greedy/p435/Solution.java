package greedy.p435;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// 给定一组区间，问最多保留多少个区间，可以让这些区间之间互相不重叠
// 先按照起始点进行排序，方便判断不重叠
public class Solution {
    // 动态规划实现
    // 比较像最长上升子序列。对于每一个区间，都可以回头看它前面的所有区间，来看这个区间是不是能够跟在它前面的区间后面，
    //  如果可以跟在后面的话，那么它前面那个区间的状态数，也就是说它前面那个区间的最大不重叠的区间数再加一即可
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (int[] interval1, int[] interval2) -> interval1[0] - interval2[0]);

        int n = intervals.length;

        // memo[i]表示使用intervals[0...i]的区间能构成的最长不重叠区间序列
        int memo[] = new int[n];
        Arrays.fill(memo, 1); // 初始化时，只保留自己这个区间，肯定互不重叠

        for(int i = 1; i < n; i++) {
            for(int j = i - 1; j >= 0; j--) {
                // intervals[i][0] >= intervals[j][1] 说明 第i个区间和第j区间不重叠（第i个区间的左边界大于等于第j个区间的右边界）
                memo[i] = Math.max(memo[i], (intervals[i][0] >= intervals[j][1]) ? memo[j] + 1 : 1);
            }
        }

        return n - Arrays.stream(memo).max().getAsInt();
    }

    // 注意：每次选择中，每个区间的结尾很重要
    // 结尾越小，留给了后面越大的空间
    // 后面越有可能容纳更多区间
    // 贪心算法：
    // 按照区间的结尾排序，每次选择结尾最早的，且和前一个区间不重叠的区间
    public int eraseOverlapIntervals2(int[][] intervals) {
        if(intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (int[] interval1, int[] interval2) -> interval1[1] - interval2[1]);

        int n = intervals.length;
        int rst = 1;    // 初始化时第一个区间已经在结果集中了
        int curIntervalIndex = 1;
        int preIntervalIndex = 0;
        while (curIntervalIndex < n) {
            if(intervals[curIntervalIndex][0] >= intervals[preIntervalIndex][1]) { // 区间不重叠
                rst++;
                preIntervalIndex = curIntervalIndex;
                curIntervalIndex++;
            } else {
                curIntervalIndex++;
            }
        }

        return n - rst;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2}, {1,2}, {1,2}, {1,2}};
        Solution solution = new Solution();
        System.out.println(solution.eraseOverlapIntervals2(intervals));
    }
}
