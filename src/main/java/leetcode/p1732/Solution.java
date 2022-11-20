package leetcode.p1732;

public class Solution {
    public int largestAltitude(int[] gain) {
        // 求前缀和，并且找到最大值
        int rst = 0;    // 前缀和第一个元素是0，从海拔0开始
        int[] prefixSum = new int[gain.length + 1];
        for(int i = 0; i < gain.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + gain[i];
            rst = Math.max(rst, prefixSum[i + 1]);
        }
        return rst;
    }

    public int largestAltitude2(int[] gain) {
        // 求前缀和，并且找到最大值
        int rst = 0;    // 前缀和第一个元素是0，从海拔0开始
        int sum = 0;    // 因为只需要找到最大值，所以不需要前缀和的数组，只需要记录每次求和的值，然后比较大小即可
        for(int i = 0; i < gain.length; i++) {
            sum = sum + gain[i];
            rst = Math.max(rst, sum);
        }
        return rst;
    }
}
