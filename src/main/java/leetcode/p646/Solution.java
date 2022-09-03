package leetcode.p646;

import java.util.Arrays;

public class Solution {
    // 贪心
    // 要挑选最长数对链的第一个数对时，最优的选择是挑选第二个数字最小的，这样能给挑选后续的数对留下更多的空间。
    // 挑完第一个数对后，要挑第二个数对时，也是按照相同的思路，是在剩下的数对中，第一个数字满足题意的条件下，挑选第二个数字最小的。
    // 按照这样的思路，可以先将输入按照第二个数字排序，然后不停地判断第一个数字是否能满足大于前一个数对的第二个数字即可。
    public int findLongestChain(int[][] pairs) {
        int rst = 0;
        // 表示前一个数对的第二个数字
        int curr = Integer.MIN_VALUE;

        Arrays.sort(pairs, (x, y) -> {
            return x[1] - y[1];
        });

        for(int[] p : pairs) {
            if(curr < p[0]) {   // 当前遍历的数对的第第一个数字大于前一个数对的第二数字，满足要求，更新curr和结果集
                curr = p[1];
                rst++;
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findLongestChain(new int[][]{new int[]{1,5}, new int[]{1,3}, new int[]{6,7}, new int[]{4, 5}}));
    }
}
