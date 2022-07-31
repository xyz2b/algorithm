package leetcode.p757;

import java.util.Arrays;


// 贪心算法
public class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // a = intervals[i]
        // a[0]升序，a[1]降序
        Arrays.sort(intervals, (a1, a2) -> a1[0] == a2[0] ? a2[1] - a1[1] : a1[0] - a2[0]);

        int n = intervals.length;
        // 初始两个元素，取的两个元素是左边界和左边界+1
        int cur = intervals[n - 1][0];  // 左边界
        int next = intervals[n - 1][0] + 1; // 左边界+1
        int ans = 2;

        // 从后往前遍历
        // 当前区间的左边界一定小于等于上一个遍历区间的左边界，当前区间的右边界一定大于等于上一个遍历区间的右边界
        // 如果区间左边界相同，当前区间一定包含前一个区间（当期区间的右边界一定大于等于前一个区间的右边界）
        // 一直取区间的左边界元素作为集合元素
        for(int i = n - 2; i >= 0; i--) {
            // intervals[i]当前遍历区间 包含 前一个遍历区间，那么取前一个区间内的最左侧的两个元素一定在当前区间内（当前区间右边界大于等于前一个区间的右边界，包含）
            if (intervals[i][1] >= next) {
                continue;
            } else if (intervals[i][1] < cur) { // 当前遍历区间 和 前一个遍历区间 完全没有重叠，那么就需要从当前区间内取两个元素（当前区间右边界小于前一个区间的左边界，没有交集）
                cur = intervals[i][0];
                next = intervals[i][0] + 1;
                ans = ans + 2;
            } else {    // (cur <= intervals[i][1] < next)当前遍历区间 和 前一个遍历区间 有元素交集，那么就需要从当前区间取无交集的左边界的一个元素
                next = cur;
                cur = intervals[i][0];
                ans += 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] {{1,10},{200,205},{200,300},{300,10000}};
        Solution solution = new Solution();
        solution.intersectionSizeTwo(intervals);
    }
}
