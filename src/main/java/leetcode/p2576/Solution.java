package leetcode.p2576;

import java.util.Arrays;

public class Solution {
    /**
     * 双指针
     * 由于长度为 n 的数组最多只会产生 2/n 对匹配，因此对数组从小到大排序以后，我们将数组一分为二，左侧元素只会与右侧元素匹配。
     * */
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);

        int ret = 0;

        int m = nums.length / 2;

        int l = 0;
        int r = m;

        // [0, m-1] 和 [m, n] 之间的元素配对，找右侧中最小的满足条件的数值，不满足的跳过
        while (l < m && r < nums.length) {
            if(nums[l] * 2 <= nums[r]) {
                ret += 2;
                l++;
            }
            r++;
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {42,83,48,10,24,55,9,100,10,17,17,99,51,32,16,98,99,31,28,68,71,14,64,29,15,40};
        Solution solution = new Solution();
        System.out.println(solution.maxNumOfMarkedIndices(nums));
    }
}
