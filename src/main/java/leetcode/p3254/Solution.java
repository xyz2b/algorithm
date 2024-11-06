package leetcode.p3254;

import java.util.Arrays;

public class Solution {
    public int[] resultsArray(int[] nums, int k) {
        // 由于子数组如果满足连续上升，此时相邻元素的差值一定为 1，此时我们在遍历数组的同时，
        // 用一个计数器 cnt 统计以当前索引 i 为结尾时连续上升的元素个数，初始化 cnt=0，此时：
        //  如果满足 i=0 或者 nums[i]−nums[i−1]=1 时，此时 cnt=cnt+1，即在 nums[i−1] 末尾可以追加元素 nums[i] 仍然满足连续上升；
        //  如果 nums[i]−nums[i−1]!=1，此时 cnt 重置为1，即在 nums[i−1] 末尾无法追加 nums[i]；
        // 如果遍历过程中，cnt >= k，就满足题意，此时连续上升的最大值就是 nums[i]
        int n = nums.length;
        int[] ret = new int[n - k + 1];
        Arrays.fill(ret, -1);
        int cnt = 0;
        for(int i = 0; i < nums.length; i++) {
            cnt = (i == 0 || nums[i] - nums[i-1] != 1 ? 1 : cnt + 1);
            if(cnt >= k) {
                ret[i - k + 1] = nums[i];
            }
        }
        return ret;
    }
}
