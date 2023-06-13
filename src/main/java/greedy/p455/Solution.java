package greedy.p455;

import java.util.Arrays;

// 贪心算法的难点在于如何确定该题可以使用贪心算法
// 贪心算法永远牵扯到每一次都要取最大值或者最小值，所以和排序是分不开的
public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int si = s.length - 1;
        int gi = g.length - 1;
        int rst = 0;
        while (si >= 0 && gi >=0) {     // i < 0 表示没小朋友了，j < 0 表示没有饼干了
            if(s[si] >= g[gi]) {    // 当前最大的一块饼干可以满足最贪心的小朋友，就给他
                rst++;
                gi--;
                si--;
            } else {    // 当前最大的一块饼干无法满足最贪心的小朋友，那对不起，不给他了
                gi--;
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] g = {1,2,3};
        int[] s = {3};
        Solution solution = new Solution();
        System.out.println(solution.findContentChildren(g, s));
    }
}
