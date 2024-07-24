package leetcode.p2766;

import java.util.*;

class Solution {
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for(int i = 0; i < moveFrom.length; i++) {
            int from = moveFrom[i];
            int to = moveTo[i];

            set.remove(from);
            set.add(to);
        }

        List<Integer> ret = new ArrayList<>(set);
        Collections.sort(ret);
        return ret;
    }
}