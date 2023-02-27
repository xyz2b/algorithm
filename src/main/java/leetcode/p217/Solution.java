package leetcode.p217;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    // 滑动窗口[0, i] + 查找表，在滑动窗口[0, i]中找到两个相同的元素即有解
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> record = new HashSet<>();

        for(int i = 0; i < nums.length; i++) {
            if(record.contains(nums[i])) {
                return true;
            }
            record.add(nums[i]);
        }

        return false;
    }
}
