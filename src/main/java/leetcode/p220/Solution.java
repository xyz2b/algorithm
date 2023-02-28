package leetcode.p220;

import java.util.TreeSet;

public class Solution {
    // 滑动窗口(固定大小) + 查找表(使用二分搜索树顺序性，可以快速找到一个数值的ceil和floor，判断前驱和后继与该数值的距离是否小于等于valueDiff即可)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> record = new TreeSet<>();

        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer floor = record.floor(num); // 前驱
            Integer ceil = record.ceiling(num); // 后继
            if(floor != null) {
                if(Math.abs(floor - num) <= valueDiff) {
                    return true;
                }
            }
            if (ceil != null) {
                if(Math.abs(ceil - num) <= valueDiff) {
                    return true;
                }
            }

            record.add(nums[i]);

            // 固定窗口，大小为indexDiff，满了，就将最左边的值删除
            if(record.size() == indexDiff + 1) {
                record.remove(nums[i - indexDiff]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {3,6,0,4};
        int k = 2, t = 2;
        Solution solution = new Solution();
        System.out.println(solution.containsNearbyAlmostDuplicate(nums, k, t));
    }
}
