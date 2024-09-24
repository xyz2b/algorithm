package leetcode.p1014;

public class Solution {
    // values[i] + values[j] + i - j == (values[i] + i) + (values[j] - j)
    // 遍历values中的每个元素j
    // 当遍历其中一个j的时候，(values[j] - j)是固定不变的
    // 此时只需要找到[0, j-1]中，(values[i] + i)最大值即可
    // 可以在边遍历j的同时，找到(values[i] + i)最大值
    public int maxScoreSightseeingPair(int[] values) {
        // (values[i] + i) 初始值
        int mx = values[0] + 0;
        int ret = 0;
        for(int j = 1; j < values.length; j++) {
            ret = Math.max(ret, mx + values[j] - j);
            mx = Math.max(mx, values[j] + j);
        }
        return ret;
    }
}
