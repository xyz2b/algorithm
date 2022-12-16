package leetcode.p1785;

public class Solution {
    public int minElements(int[] nums, int limit, int goal) {
        // 先计算当前元素总和看和goal差距多少，然后能够加上几个limit的值(正负都可以)，如果加上之后还不等于，就还需要加上一个abs小于limit的值
        long diff = goal;
        for(int i = 0; i < nums.length; i++) {
            diff -= nums[i];
        }

        diff = Math.abs(diff);

        return diff % limit == 0 ? (int) (diff / limit) : (int) (diff / limit + 1);
    }
}
